package com.doshisha.blog.member.dto;

import com.doshisha.blog.member.domain.Member;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Data
@NoArgsConstructor
public class MemberForm {

    private Long id;
    private String username;
    private String password;
    private String email;
    private Integer age;
    private String role;

    @Builder
    public MemberForm(Long id, String username, String password, String email, Integer age, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }

    public Member toEntity() {
        return Member.builder()
                .id(id)
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .email(email)
                .age(age)
                .role(role)
                .build();
    }
}
