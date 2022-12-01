package com.doshisha.blog.member.service;

import com.doshisha.blog.security.jwt.dto.TokenInfo;
import com.doshisha.blog.security.jwt.provider.JwtTokenProvider;
import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.dto.LoginForm;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.exception.MemberNotFound;
import com.doshisha.blog.member.exception.PasswordMismatch;
import com.doshisha.blog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void join(MemberForm request) {
        Member member = request.toEntity();
        memberRepository.save(member);
    }

//    public void login(LoginForm request) {
//        Member member = memberRepository.findByEmail(request.getEmail())
//                .orElseThrow(MemberNotFound::new);
//
//        if(!passwordValidator(member, request.getPassword())) {
//            throw new PasswordMismatch();
//        }
//    }

    @Transactional
    public TokenInfo login(LoginForm request) {
        // 1. Login ID/PW 를 기반으로 Authentication 객체 생성
        // 이때 authentication 는 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword());
        System.out.println("1 :" + authenticationToken);

        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(MemberNotFound::new);

        if(!passwordValidator(member, request.getPassword())) {
            throw new PasswordMismatch();
        }

        // 2. 실제 검증 (사용자 비밀번호 체크)이 이루어지는 부분
        // authenticate 매서드가 실행될 때 CustomUserDetailsService 에서 만든 loadUserByUsername 메서드가 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println("2 :" + authentication);

        // 3. 인증 정보를 기반으로 JWT 토큰 생성
        TokenInfo tokenInfo = jwtTokenProvider.generateToken(authentication);

        return tokenInfo;
    }

    public boolean passwordValidator(Member member, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, member.getPassword());
    }
}
