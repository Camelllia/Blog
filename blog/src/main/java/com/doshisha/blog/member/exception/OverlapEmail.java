package com.doshisha.blog.member.exception;

public class OverlapEmail extends RuntimeException {

    private static final String MESSAGE = "중복되는 이메일입니다";

    public OverlapEmail() {super(MESSAGE);}

    public OverlapEmail(Throwable cause) {super(MESSAGE, cause);}
}
