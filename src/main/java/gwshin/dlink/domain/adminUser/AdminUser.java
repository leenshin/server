package gwshin.dlink.domain.adminUser;

import lombok.*;

@Data
public class AdminUser {
    private String userId;
    private String pass;
    private String email;
    private String roles;
}
