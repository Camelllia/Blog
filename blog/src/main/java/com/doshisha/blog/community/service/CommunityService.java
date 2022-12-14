package com.doshisha.blog.community.service;

import com.doshisha.blog.community.domain.BoardMenu;
import com.doshisha.blog.community.exception.BoardMenuNotFound;
import com.doshisha.blog.community.repository.BoardMenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommunityService {

    private final BoardMenuRepository boardMenuRepository;

    public List<BoardMenu> selectBoardMenuList() {
        return boardMenuRepository.findAllByDeleteYnFalse();
    }

    public BoardMenu selectBoardMenu(String menuPath) {
        return boardMenuRepository.findByMenuPathAndDeleteYnFalse(menuPath)
                .orElseThrow(BoardMenuNotFound::new);
    }
}
