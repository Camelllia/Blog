package com.doshisha.blog.member.exception;

public class DiscordPassword extends RuntimeException {

    private static final String MESSAGE = "입력한 비밀번호가 일치하지 않습니다";

    public DiscordPassword() {super(MESSAGE);}

    public DiscordPassword(Throwable cause) {super(MESSAGE, cause);}
}
