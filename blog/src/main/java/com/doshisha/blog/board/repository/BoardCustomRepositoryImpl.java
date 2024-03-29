package com.doshisha.blog.board.repository;

import com.doshisha.blog.board.domain.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.doshisha.blog.board.domain.QBoard.board;

import java.util.List;

public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public BoardCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<Board> findMainBoardList() {
        return jpaQueryFactory.selectFrom(board)
                .where(board.delYn.eq(Boolean.FALSE))
                .orderBy(board.regDate.desc())
                .fetch();
    }

    @Override
    public List<Board> findBoardListByMenu(Long id) {
        return jpaQueryFactory.selectFrom(board)
                .where(board.boardMenu.id.eq(id))
                .where(board.delYn.eq(Boolean.FALSE))
                .orderBy(board.regDate.desc())
                .fetch();
    }

    @Override
    public Board findBoardItem(Long menuId, Long boardId) {
        return jpaQueryFactory.selectFrom(board)
                .where(board.boardMenu.id.eq(menuId))
                .where(board.id.eq(boardId))
                .where(board.delYn.eq(Boolean.FALSE))
                .orderBy(board.regDate.desc())
                .fetchOne();
    }
}
