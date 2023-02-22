package com.doshisha.blog.community.repository.board;

import com.doshisha.blog.community.domain.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class BoardCustomRepositoryImpl implements BoardCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;

    public BoardCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

}
