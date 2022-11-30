package com.doshisha.blog.member.exception;

public class PasswordMismatch extends RuntimeException {

    private static final String MESSAGE = "비밀번호가 일치하지 않습니다";

    public PasswordMismatch() {super(MESSAGE);}

    public PasswordMismatch(Throwable cause) {super(MESSAGE, cause);}
}
