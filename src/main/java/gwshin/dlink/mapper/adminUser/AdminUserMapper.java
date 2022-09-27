package gwshin.dlink.mapper.adminUser;

import gwshin.dlink.domain.adminUser.AdminUser;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminUserMapper {
    AdminUser findByUserId(String userId);
}
