package com.doshisha.blog.boardMenu.repository;

import com.doshisha.blog.boardMenu.domain.BoardMenu;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.doshisha.blog.boardMenu.domain.QBoardMenu.boardMenu;

import java.util.List;

public class BoardMenuCustomRepositoryImpl implements BoardMenuCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public BoardMenuCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<BoardMenu> findAllBoardMenu() {
        return jpaQueryFactory.selectFrom(boardMenu)
                .where(boardMenu.delYn.eq(Boolean.FALSE))
                .orderBy(boardMenu.regDate.desc())
                .fetch();
    };

}
