package RestAPI.EX.auth;

import RestAPI.EX.dto.UserInfoDTO;
import RestAPI.EX.service.IUserInfoSsService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.logging.LogLevel;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final IUserInfoSsService userInfoSsService;

    @Value("${jwt.secret.key}")
    private String secretKey;

    @Value("${jwt.token.creator}")
    private String creator;

    @Value("${jwt.token.access.valid.time}")
    private long accessTokenValidTime;

    @Value("${jwt.token.access.name}")
    private String accessTokenName;

    @Value("${jwt.token.refresh.valid.time}")
    private long refreshTokenValidTime;

    @Value("${jwt.token.refresh.name}")
    private String refreshTokenName;

    public String createToken(String userId, String roles, JwtTokenType tokenType) {

        log.info(this.getClass().getName() + ".createToken Start!");

        log.info("userId : " + userId);

        long validTime = 0;

        if (tokenType == JwtTokenType.ACCESS_TOKEN) {
            validTime = (accessTokenValidTime);
        } else if (tokenType == JwtTokenType.REFRESH_TOKEN) {
            validTime = (refreshTokenValidTime);
        }

        Claims claims = Jwts.claims()
                .setIssuer(creator)
                .setSubject(userId);

        claims.put("roles", roles);

        Date now = new Date();

        log.info(this.getClass().getName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + (validTime * 1000)))
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public Authentication getAuthentication(String token) {

        log.info(this.getClass().getName() + ".getAuthentication Start!");

        log.info("getAuthentication : " + token);

        String userId = getUserId(token);

        log.info("userId : " + userId);

        AuthInfo info = (AuthInfo) userInfoSsService.loadUserByUsername(userId);

        UserInfoDTO dto = info.getUserInfoDTO();

        if (dto == null) {
            dto = new UserInfoDTO();
        }

        String roles = dto.getRoles();

        Set<GrantedAuthority> pSet = new HashSet<>();
        if (roles.length() > 0) {
            for (String role : roles.split(",")) {
                pSet.add(new SimpleGrantedAuthority(role));
            }
        }

        log.info(this.getClass().getName() + ".getAuthentication End!");

        return new UsernamePasswordAuthenticationToken(info, "", pSet);
    }

    public String getUserId(String token) {

        log.info(this.getClass().getName() + ".getUserId Start!");

        String userId = Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJws(token).getBody().getSubject();

        log.info("userId : " + userId);

        log.info(this.getClass().getName() + ".getUserId End!");

        return userId;
    }

    public String getUserRoles(String token) {
        log.info(this.getClass().getName() + ".getUserRoles Start!");

        String roles = (String) (Jwts.parser().setSigningKey(secretKey)).parseClaimsJws(token)
                .getBody().get("roles");

        log.info("roles : " + roles);

        log.info(this.getClass().getName() + ".getUserRoles End!");

        return roles;
    }

    public String resolveToken(HttpServletRequest request, JwtTokenType tokenType) {

        log.info(this.getClass().getName() + ".resolveToken Start!");

        String tokenName = "";

        if (tokenType == JwtTokenType.ACCESS_TOKEN) {
            tokenName = accessTokenName;
        } else if (tokenType == JwtTokenType.REFRESH_TOKEN) {
            tokenName = refreshTokenName;
        }

        String token = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie key : request.getCookies()) {
                if (key.getName().equals(tokenName)) {
                    token = key.getValue();
                    break;
                }
            }
        }

        log.info(this.getClass().getName() + ".resolveToken End!");
        return token;
    }

    public JwtStatus validateToken(String token) {

        if (token.length() > 0) {

            try {
                Jws<Claims> claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token);

                if (claims.getBody().getExpiration().before(new Date())) {
                    return JwtStatus.EXPIRED;
                } else {
                    return JwtStatus.ACCESS;
                }
            } catch (ExpiredJwtException e) {
                return JwtStatus.EXPIRED;
            } catch (JwtException | IllegalArgumentException e) {
                log.info("jwtException : {}", e);
                return JwtStatus.DENIED;

            }
        } else {
            return JwtStatus.DENIED;
        }
    }
}
