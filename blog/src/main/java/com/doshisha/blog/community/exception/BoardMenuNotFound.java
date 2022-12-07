package com.doshisha.blog.community.exception;

public class BoardMenuNotFound extends RuntimeException {

    private static final String MESSAGE = "존재하지 않는 게시판입니다";

    public BoardMenuNotFound() {super(MESSAGE);}

    public BoardMenuNotFound(Throwable cause) {super(MESSAGE, cause);}
}
