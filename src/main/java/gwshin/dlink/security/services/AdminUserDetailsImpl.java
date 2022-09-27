package gwshin.dlink.security.services;

import gwshin.dlink.domain.adminUser.AdminUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class AdminUserDetailsImpl implements UserDetails {

    private final String userId;
    private final String pass;
    private final String email;
    private final Collection<? extends GrantedAuthority> authorities;

    public AdminUserDetailsImpl(String userId, String pass, String email, Collection<? extends GrantedAuthority> authorities) {
        this.userId = userId;
        this.pass = pass;
        this.email = email;
        this.authorities = authorities;
    }

    public static AdminUserDetailsImpl build(AdminUser adminUser) {
        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        for (String role : adminUser.getRoles().split(",")) {
            authoritiesList.add(new SimpleGrantedAuthority("ROLE_" + role));
        }

        return new AdminUserDetailsImpl(
                adminUser.getUserId(),
                adminUser.getPass(),
                adminUser.getEmail(),
                authoritiesList
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.pass;
    }

    @Override
    public String getUsername() {
        return this.userId;
    }

    public String getEmail() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AdminUserDetailsImpl user = (AdminUserDetailsImpl) obj;
        return Objects.equals(this.userId, user.userId);
    }
}
