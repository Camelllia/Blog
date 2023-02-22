package com.doshisha.blog.boardMenu.repository;

import com.doshisha.blog.boardMenu.domain.BoardMenu;

import java.util.List;

public interface BoardMenuCustomRepository {

    List<BoardMenu> findAllBoardMenu();
}
