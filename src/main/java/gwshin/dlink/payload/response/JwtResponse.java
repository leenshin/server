package gwshin.dlink.payload.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String userId;
    private String email;
    private List<String> roles;

    public JwtResponse(String accessToken, String refreshToken, String userId, String email, List<String> roles) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.userId = userId;
        this.email = email;
        this.roles = roles;
    }
}
