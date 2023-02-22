package com.doshisha.blog.boardMenu.repository;

import com.doshisha.blog.boardMenu.domain.BoardMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardMenuRepository extends JpaRepository<BoardMenu, Long>, BoardMenuCustomRepository {

}
