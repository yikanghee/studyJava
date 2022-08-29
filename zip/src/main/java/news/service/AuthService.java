package news.service;

import lombok.RequiredArgsConstructor;
import news.config.JwtTokenProvider;
import news.config.UserDetailsImpl;
import news.domain.Member;
import news.domain.model.Role;
import news.dto.JwtRequestDto;
import news.dto.JwtResponseDto;
import news.dto.MemberSignupRequestDto;
import news.repository.MemberRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtResponseDto login(JwtRequestDto requestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword())
        );
        return createJwtToken(authentication);
    }

    private JwtResponseDto createJwtToken(Authentication authentication) {
        UserDetailsImpl principal = (UserDetailsImpl) authentication.getPrincipal();
        String token = jwtTokenProvider.generateToken(principal);
        return new JwtResponseDto(token);
    }

    public String signup(MemberSignupRequestDto requestDto) {
        boolean existMember = memberRepository.existsByUsername(requestDto.getUsername());

        if (existMember) {
            throw new IllegalStateException("이미 존재하는 아이디가 있습니다");
        }

        Member member = Member.builder()
                .username(requestDto.getUsername())
                .password(passwordEncoder.encode(requestDto.getPassword()))
                .role(Role.USER)
                .build();
        memberRepository.save(member);

        return member.getUsername();
    }

}
