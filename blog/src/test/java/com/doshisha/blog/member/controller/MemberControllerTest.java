package com.doshisha.blog.member.controller;

import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.dto.LoginForm;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.repository.MemberRepository;
import com.doshisha.blog.security.jwt.filter.JwtAuthenticationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.servlet.server.Session;
import javax.servlet.http.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@Transactional
@WithMockUser(username = "mockUser")
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
                .passwordRepeat("1234")
                .username("name")
                .age(33)
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .cookie(new Cookie("dummyCookie", "dummy"))
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
                .passwordRepeat("1234")
                .username("name")
                .age(33)
                .build();

        String json = objectMapper.writeValueAsString(request);

        //when
        mockMvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .cookie(new Cookie("dummyCookie", "dummy"))
                        .content(json)
                )
                .andExpect(status().isOk())
                .andDo(print());

        //then
        assertEquals(1L, memberRepository.count());
        Member member = memberRepository.findAll().get(0);
        assertEquals("test@test.com", member.getEmail());
        assertEquals("name", member.getUsername());
        assertEquals(33, member.getAge());
        assertEquals("ROLE_USER", member.getRole());
    }

    @Test
    @DisplayName("회원가입시 이메일은 필수다")
    void test3() throws Exception{

        //given
        MemberForm request = MemberForm.builder()
                .password("1234")
                .passwordRepeat("1234")
                .username("name")
                .age(33)
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .cookie(new Cookie("dummyCookie", "dummy"))
                        .content(json)
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("잘못된 요청입니다"))
                .andExpect(jsonPath("$.vaildation.email").value("이메일을 입력해주세요"))
                .andDo(print());
    }

    @Test
    @DisplayName("동일한 이메일로 중복가입 방지")
    void test4() throws Exception{

        //given
        Member firstMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("doshisha")
                .age(33)
                .build();

        memberRepository.save(firstMember);

        MemberForm request = MemberForm.builder()
                .email("test@test.com")
                .password("1234")
                .passwordRepeat("1234")
                .username("name")
                .age(33)
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .cookie(new Cookie("dummyCookie", "dummy"))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("중복되는 이메일입니다"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입시 비밀번호는 일치해야한다")
    void test5() throws Exception{

        //given
        MemberForm request = MemberForm.builder()
                .email("test@test.com")
                .password("1234")
                .passwordRepeat("ABCD")
                .username("name")
                .age(33)
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .cookie(new Cookie("dummyCookie", "dummy"))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("입력한 비밀번호가 일치하지 않습니다"))
                .andDo(print());
    }

    @Test
    @DisplayName("회원가입시 이메일 형식을 지켜야한다")
    void test6() throws Exception{

        //given
        MemberForm request = MemberForm.builder()
                .email("TEST")
                .password("1234")
                .passwordRepeat("1234")
                .username("name")
                .age(33)
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/join")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .cookie(new Cookie("dummyCookie", "dummy"))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("이메일 형식이 아닙니다"))
                .andDo(print());
    }

    @Test
    @DisplayName("미등록 회원 검증")
    void test7() throws Exception{

        //given
        LoginForm request = LoginForm.builder()
                        .email("test@test.com")
                        .password("1234")
                        .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .cookie(new Cookie("dummyCookie", "dummy"))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("존재하지 않는 회원입니다"))
                .andDo(print());
    }

    @Test
    @DisplayName("비밀번호 불일치 검증")
    void test8() throws Exception{

        //given
        Member newMember = Member.builder()
                .email("test@test.com")
                .password("1234")
                .username("doshisha")
                .age(33)
                .build();

        memberRepository.save(newMember);

        LoginForm request = LoginForm.builder()
                .email("test@test.com")
                .password("12345")
                .build();

        String json = objectMapper.writeValueAsString(request);

        //expected
        mockMvc.perform(post("/login")
                        .contentType(APPLICATION_JSON)
                        .content(json)
                        .cookie(new Cookie("dummyCookie", "dummy"))
                )
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.code").value("400"))
                .andExpect(jsonPath("$.message").value("비밀번호가 일치하지 않습니다"))
                .andDo(print());
    }
}