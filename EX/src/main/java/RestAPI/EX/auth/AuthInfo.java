package RestAPI.EX.auth;

import RestAPI.EX.dto.UserInfoDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Getter
@RequiredArgsConstructor
public class AuthInfo implements UserDetails {

    private final UserInfoDTO userInfoDTO;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Set<GrantedAuthority> pSet = new HashSet<>();

        String roles = userInfoDTO.getRoles();

        if (roles.length() > 0) {
            for (String role : roles.split(",")) {
                pSet.add(new SimpleGrantedAuthority(role));
            }
        }
        return pSet;
    }

    @Override
    public String getPassword() {
        return userInfoDTO.getUserId();
    }

    @Override
    public String getUsername() {
        return userInfoDTO.getPassword();
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
}
