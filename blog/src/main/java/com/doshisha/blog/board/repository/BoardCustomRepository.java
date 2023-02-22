package com.doshisha.blog.board.repository;

import com.doshisha.blog.board.domain.Board;

import java.util.List;

public interface BoardCustomRepository {

    List<Board> findMainBoardList();

    List<Board> findBoardListByMenu(Long id);

    Board findBoardItem(Long menuId, Long boardId);
}
