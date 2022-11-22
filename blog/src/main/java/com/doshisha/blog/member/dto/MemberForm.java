package com.doshisha.blog.member.dto;

import com.doshisha.blog.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class MemberForm {

    private String username;
    private String password;
    private String email;
    private Integer age;
    private String role;

    @Builder
    public MemberForm(String username, String password, String email, Integer age, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public Member toEntity() {
        return Member.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .age(age)
                .role(role)
                .build();
    }
}
