package com.doshisha.blog.member.dto;

import com.doshisha.blog.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class MemberForm {

    @NotBlank(message = "이름을 입력해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    @NotBlank(message = "이메일을 입력해주세요")
    private String email;

    @NotNull(message = "나이를 입력해주세요")
    private Integer age;

    @Builder
    public MemberForm(String username, String password, String email, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
    }

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .age(age)
                .build();
    }
}
