package RestAPI.EX.filter;

import RestAPI.EX.auth.JwtStatus;
import RestAPI.EX.auth.JwtTokenProvider;
import RestAPI.EX.auth.JwtTokenType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Value("${jwt.token.access.valid.time}")
    private long accessTokenValidTime;

    @Value("${jwt.token.access.name}")
    private String accessTokenName;

    private final JwtTokenProvider jwtTokenProvider;

    private final List<String> url = Collections.unmodifiableList(
            Arrays.asList(
                    "/ss/loginForm",
                    "/"
            )
    );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        log.info(this.getClass().getName() + ".doFilterInternal Start!");

        String accessToken = jwtTokenProvider.resolveToken(request, JwtTokenType.ACCESS_TOKEN);

        log.info("accessToken : " + accessToken);

        JwtStatus accessTokenStatus = jwtTokenProvider.validateToken(accessToken);

        log.info("accessTokenStatus : " + accessTokenStatus);

        if (accessTokenStatus == JwtStatus.ACCESS) {
            Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } else if (accessTokenStatus == JwtStatus.EXPIRED || accessTokenStatus == JwtStatus.DENIED) {

            String refreshToken = jwtTokenProvider.resolveToken(request, JwtTokenType.REFRESH_TOKEN);

            JwtStatus refreshTokenStatus = jwtTokenProvider.validateToken(refreshToken);

            log.info("refreshTokenStatus : " + refreshTokenStatus);

            if (refreshTokenStatus == JwtStatus.ACCESS) {
                String userId = jwtTokenProvider.getUserId(refreshToken);
                String userRoles = jwtTokenProvider.getUserRoles(refreshToken);

                log.info("refreshToken userId" + userId);
                log.info("refreshToken userRoles : " + userRoles);

                String reAcessToken = jwtTokenProvider.createToken(userId, userRoles, JwtTokenType.ACCESS_TOKEN);
                log.info("accessTokenName : " + accessTokenName);

                ResponseCookie cookie = ResponseCookie.from(accessTokenName, "")
                        .maxAge(0)
                        .build();

                response.setHeader("Set-Cookie", cookie.toString());

                cookie = null;

                cookie = ResponseCookie.from(accessTokenName, reAcessToken)
                        .domain("localhost")
                        .path("/")
                        .secure(true)
                        .sameSite("None")
                        .maxAge(accessTokenValidTime)
                        .httpOnly(true)
                        .build();

                response.setHeader("Set-Cookie", cookie.toString());

                Authentication authentication = jwtTokenProvider.getAuthentication(reAcessToken);

                SecurityContextHolder.getContext().setAuthentication(authentication);
            } else if (refreshTokenStatus == JwtStatus.EXPIRED) {
                log.info("Refresh Token 만료");
            } else {
                log.info("Refresh Token 오류");
            }
        }

        log.info(this.getClass().getName() + ".doFilterInternal End");

        filterChain.doFilter(request, response);
    }

    /**
     * JwtAuthenticationFilter가 체크하지 않을 URL 체크하여 호출안하기
     */
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {

        return url.stream().anyMatch(exclude -> exclude.equalsIgnoreCase(request.getServletPath()));

    }

}
