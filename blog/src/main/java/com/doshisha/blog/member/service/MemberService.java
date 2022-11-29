package com.doshisha.blog.member.service;

import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.dto.LoginForm;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void join(MemberForm request) {
        Member member = request.toEntity();
        memberRepository.save(member);
    }

    public void login(LoginForm request) {
        LoginForm loginForm = LoginForm.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .build();
        
        System.out.println(loginForm.getPassword());
    }
}
