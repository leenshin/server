package gwshin.dlink.security.services;

import gwshin.dlink.domain.adminUser.AdminUser;
import gwshin.dlink.mapper.adminUser.AdminUserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUserDetailsServiceImpl implements UserDetailsService {

    private final AdminUserMapper adminUserMapper;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        AdminUser adminUser = (AdminUser) adminUserMapper.findByUserId(userId);
        if (adminUser == null) {
            throw new UsernameNotFoundException("User Not Found With UserId=" + userId);
        }
        return AdminUserDetailsImpl.build(adminUser);
    }
}
