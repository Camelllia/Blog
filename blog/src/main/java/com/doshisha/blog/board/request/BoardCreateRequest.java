package com.doshisha.blog.board.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class BoardCreateRequest {

    @NotBlank(message = "제목을 입력해주세요")
    private String title;

    @NotBlank(message =  "내용을 입력해주세요")
    private String content;

    @NotNull(message = "카테고리를 선택해주세요")
    private Long menuId;
}
