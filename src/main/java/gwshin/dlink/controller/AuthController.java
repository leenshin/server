package gwshin.dlink.controller;

import gwshin.dlink.payload.request.LoginRequest;
import gwshin.dlink.payload.response.JwtResponse;
import gwshin.dlink.security.jwt.JwtUtils;
import gwshin.dlink.security.services.AdminUserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUserId(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        AdminUserDetailsImpl userDetails = (AdminUserDetailsImpl) authentication.getPrincipal();
        List<String> roles = getRoles(userDetails);

        return ResponseEntity.ok(
                new JwtResponse(jwt, null, userDetails.getUsername(), userDetails.getEmail(), roles));
    }

    private List<String> getRoles(AdminUserDetailsImpl userDetails) {
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authRole : userDetails.getAuthorities()) {
            roles.add(authRole.getAuthority());
        }
        return roles;
    }
}
