package com.doshisha.blog.community.repository.boardMenu;

import com.doshisha.blog.community.domain.BoardMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardMenuRepository extends JpaRepository<BoardMenu, Long> {

    List<BoardMenu> findAllByDelYnFalse();

    Optional<BoardMenu> findByMenuPathAndDelYnFalse(String menuPath);
}
