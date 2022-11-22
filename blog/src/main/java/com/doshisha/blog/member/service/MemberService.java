package com.doshisha.blog.member.service;

import com.doshisha.blog.member.domain.Member;
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
    public Long join(MemberForm form) {
        Member member = form.toEntity();
        memberRepository.save(member);
        return member.getId();
    }
}
