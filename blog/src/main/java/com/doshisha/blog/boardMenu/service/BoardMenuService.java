package com.doshisha.blog.boardMenu.service;

import com.doshisha.blog.boardMenu.domain.BoardMenu;
import com.doshisha.blog.boardMenu.exception.BoardMenuNotFound;
import com.doshisha.blog.boardMenu.repository.BoardMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardMenuService {

    private final BoardMenuRepository boardMenuRepository;

    public List<BoardMenu> findAllBoardMenu() {
        return boardMenuRepository.findAllBoardMenu();
    }

    public BoardMenu findBoardMenuById(Long id) {
        return boardMenuRepository.findById(id)
                .orElseThrow(BoardMenuNotFound::new);
    }
}
