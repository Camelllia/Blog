package com.doshisha.blog.board.service;

import com.doshisha.blog.board.domain.Board;
import com.doshisha.blog.board.repository.BoardRepository;
import com.doshisha.blog.board.request.BoardCreateRequest;
import com.doshisha.blog.boardMenu.exception.BoardMenuNotFound;
import com.doshisha.blog.boardMenu.repository.BoardMenuRepository;
import com.doshisha.blog.member.context.MemberContext;
import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.exception.MemberNotFound;
import com.doshisha.blog.member.repository.MemberRepository;
import com.doshisha.blog.security.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    private final BoardMenuRepository boardMenuRepository;

    private final MemberRepository memberRepository;

    public List<Board> findMainBoardList() {
        return boardRepository.findMainBoardList();
    }

    public List<Board> findBoardListByMenu(Long menuId) {
        return boardRepository.findBoardListByMenu(menuId);
    }

    public Board findBoardItem(Long menuId, Long boardId) {
        return boardRepository.findBoardItem(menuId, boardId);
    }

    public ResponseEntity<?> create(BoardCreateRequest request) {

        Board board = Board.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .boardMenu(boardMenuRepository.findById(request.getMenuId()).orElseThrow(BoardMenuNotFound::new))
                .member(memberRepository.findByEmail(SecurityUtil.getCurrentMemberId()).orElseThrow(MemberNotFound::new))
                .build();


        boardRepository.save(board);

        return new ResponseEntity<>("22", HttpStatus.OK);
    }

}
