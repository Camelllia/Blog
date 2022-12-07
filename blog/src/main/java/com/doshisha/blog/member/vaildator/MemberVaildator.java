package com.doshisha.blog.member.vaildator;

import com.doshisha.blog.member.domain.Member;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MemberVaildator {

    public static boolean passwordValidator(Member member, String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(password, member.getPassword());
    }

    public static boolean passwordEqaulCheckValidator(String password, String passwordRepeat) {
        return password.equals(passwordRepeat);
    }

    public static boolean isValidEmail(String email) {
        String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        return m.matches();
    }
}
