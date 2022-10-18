package RestAPI.EX.service.impl;

import RestAPI.EX.auth.AuthInfo;
import RestAPI.EX.dto.UserInfoDTO;
import RestAPI.EX.repository.UserInfoRepository;
import RestAPI.EX.repository.entity.UserInfoEntity;
import RestAPI.EX.service.IUserInfoSsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserInfoSsService implements IUserInfoSsService {

    private final UserInfoRepository userInfoRepository;

    @Override
    public int insertUserInfo(UserInfoDTO userInfoDto) throws UsernameNotFoundException {

        log.info(this.getClass().getName() + ".insertUserInfo Start");

        int res = 0;

        String userId = userInfoDto.getUserId();
        String userName = userInfoDto.getUserName();
        String password = userInfoDto.getPassword();
        String email = userInfoDto.getEmail();
        String addr1 = userInfoDto.getAddr1();
        String addr2 = userInfoDto.getAddr2();
        String roles = userInfoDto.getRoles();

        Optional<UserInfoEntity> rEntity = userInfoRepository.findByUserId(userId);

        if (rEntity.isPresent()) {
            res = 2;
        } else {

            // 회원가입을 위한 Entity 생성
            UserInfoEntity pEntity = UserInfoEntity.builder()
                    .userId(userId)
                    .userName(userName)
                    .password(password)
                    .email(email)
                    .addr1(addr1)
                    .addr2(addr2)
                    .roles(roles)
                    .regId(userId).regDt(new Date())
                    .chgId(userId).regDt(new Date())
                    .build();

            userInfoRepository.save(pEntity);

            rEntity = userInfoRepository.findByUserId(userId);

            if (rEntity.isPresent()) {
                res = 1;
            } else {
                res = 0;
            }
        }

        log.info(this.getClass().getName() + ".insertUserInfo End!");

        return res;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        log.info(this.getClass().getName() + ".loadUserByUsername Start!");

        UserInfoEntity rEntity = userInfoRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException(userId + "Not Found User"));

        UserInfoDTO rDTO = new ObjectMapper().convertValue(rEntity, UserInfoDTO.class);

        return new AuthInfo(rDTO);
    }
}
