package com.doshisha.blog.member.service;

import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입")
    void test1() {

        //given
        Member requestMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("doshisha")
                .age(33)
                .build();

        //when
        memberRepository.save(requestMember);

        //then
        Member member = memberRepository.findAll().get(0);
        assertEquals(1L, memberRepository.count());
        assertEquals("test@test.com", member.getEmail());
        assertEquals("doshisha", member.getUsername());
        assertEquals(33, member.getAge());
    }
}