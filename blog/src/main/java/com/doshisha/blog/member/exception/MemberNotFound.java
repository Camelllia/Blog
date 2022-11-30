package com.doshisha.blog.member.exception;

public class MemberNotFound extends RuntimeException{

    private static final String MESSAGE = "존재하지 않는 회원입니다";

    public MemberNotFound() {super(MESSAGE);}

    public MemberNotFound(Throwable cause) {super(MESSAGE, cause);}
}
