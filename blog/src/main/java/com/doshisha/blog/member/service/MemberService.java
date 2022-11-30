package com.doshisha.blog.member.service;

import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.dto.LoginForm;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.exception.MemberNotFound;
import com.doshisha.blog.member.exception.PasswordMismatch;
import com.doshisha.blog.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(MemberNotFound::new);

        if(!passwordValidator(member, request.getPassword())) {
            throw new PasswordMismatch();
        }
    }

    public boolean passwordValidator(Member member, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, member.getPassword());
    }
}
