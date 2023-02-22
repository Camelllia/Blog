package com.doshisha.blog.board.controller;

import com.doshisha.blog.board.request.BoardCreateRequest;
import com.doshisha.blog.board.service.BoardService;
import com.doshisha.blog.member.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody @Valid BoardCreateRequest request) {
        return boardService.create(request);
    }
}
