package com.doshisha.blog.community.repository.board;

import com.doshisha.blog.community.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardCustomRepository {

}
