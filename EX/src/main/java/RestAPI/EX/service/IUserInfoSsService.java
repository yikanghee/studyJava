package RestAPI.EX.service;

import RestAPI.EX.dto.UserInfoDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserInfoSsService extends UserDetailsService {

    int insertUserInfo(UserInfoDTO userInfoDto) throws Exception;
}
