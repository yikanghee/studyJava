package com.example.demo.service;

import com.example.demo.Repository.MemberRepository;
import com.example.demo.dto.MemberRegisterRequestDto;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public MemberRegisterRequestDto registerMember(MemberRegisterRequestDto requestDto) {

    }

    public void validateDuplicated(String email) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new MemberEmail
        }
    }
}

