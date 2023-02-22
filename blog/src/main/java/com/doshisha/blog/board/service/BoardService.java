package com.doshisha.blog.board.service;

import com.doshisha.blog.board.domain.Board;
import com.doshisha.blog.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public List<Board> findMainBoardList() {
        return boardRepository.findMainBoardList();
    }

    public List<Board> findBoardListByMenu(Long menuId) {
        return boardRepository.findBoardListByMenu(menuId);
    }

    public Board findBoardItem(Long menuId, Long boardId) {
        return boardRepository.findBoardItem(menuId, boardId);
    }

}