package news.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean {

    private final JwtTokenProvider jwtTokenProvider;


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 헤더에서 JWT토큰을 받아옴
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) request);

        // 유효한 토큰인지 확인
        if (token != null && token.startsWith("Bearer ")) {
            val jwtToken = token.substring(7);
            if (jwtTokenProvider.validationToken(jwtToken)) {
                // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옴
                Authentication authentication = jwtTokenProvider.getAuthentication(jwtToken);
                // SecurityContext안에 Autehntication 객체에 저장
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }else {
            log.debug("JWT Token does not begin with Bearer String");
        }

        chain.doFilter(request, response);
    }
}
