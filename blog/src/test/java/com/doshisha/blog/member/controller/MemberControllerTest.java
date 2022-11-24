package com.doshisha.blog.member.controller;

import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.repository.MemberRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private MemberRepository memberRepository;

    @BeforeEach
    void clean() {
        memberRepository.deleteAll();
    }

    @Test
    @DisplayName("회원가입 성공시 리다이렉트")
    void test1() throws Exception{

        //given
        MemberForm request = MemberForm.builder()
                .email("test@test.com")
                .password("1234")
                .username("name")
                .age(33)
                .role("admin")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/login/form")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입 성공시 DB에 값 저장")
    void test2() throws Exception{

        //given
        MemberForm request = MemberForm.builder()
                .email("test@test.com")
                .password("1234")
                .username("name")
                .age(33)
                .role("admin")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/login/form")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1L, memberRepository.count());
        Member member = memberRepository.findAll().get(0);
        assertEquals("test@test.com", member.getEmail());
        assertEquals("name", member.getUsername());
        assertEquals(33, member.getAge());
        assertEquals("admin", member.getRole());
    }

    @Test
    @DisplayName("회원가입시 이메일은 필수다")
    void test3() throws Exception{

        //given
        MemberForm request = MemberForm.builder()
                .password("1234")
                .username("name")
                .age(33)
                .role("admin")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/login/form")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .with(csrf())
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다"))
                .andExpect(jsonPath("$.vaildation.email").value("이메일을 입력해주세요"))
                .andDo(print());
    }
}