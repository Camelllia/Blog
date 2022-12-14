package com.doshisha.blog.community.service;

import com.doshisha.blog.community.domain.BoardMenu;
import com.doshisha.blog.community.repository.BoardMenuRepository;
import com.doshisha.blog.member.domain.Member;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CommunityServiceTest {

    @Autowired
    CommunityService communityService;

    @Autowired
    BoardMenuRepository boardMenuRepository;

    @BeforeEach
    void clean() {
        boardMenuRepository.deleteAll();
    }

    @Test
    @DisplayName("커뮤니티 메뉴 - 메뉴 리스트 조회")
    void test1() {

        //given
        List<BoardMenu> requestBoardMenus = IntStream.range(0, 5)
                .mapToObj(i -> BoardMenu.builder()
                        .menuPath("/free" + i)
                        .menuName("메뉴" + i)
                        .writeYn(false)
                        .deleteYn(false)
                        .build())
                .collect(Collectors.toList());

        boardMenuRepository.saveAll(requestBoardMenus);

        //when
        List<BoardMenu> boardMenus = communityService.selectBoardMenuList();

        //then
        assertEquals(5L, boardMenus.size());
        assertEquals("/free0", boardMenus.get(0).getMenuPath());
        assertEquals("메뉴2", boardMenus.get(2).getMenuName());
        assertEquals("/free4", boardMenus.get(4).getMenuPath());
        assertFalse(boardMenus.get(0).getWriteYn());
        assertFalse(boardMenus.get(3).getWriteYn());
        assertFalse(boardMenus.get(4).getDeleteYn());
    }

    @Test
    @DisplayName("커뮤니티 메뉴 - 특정 메뉴 정보 조회")
    void test2() {

        //given
        BoardMenu request = BoardMenu.builder()
                .menuPath("/free")
                .menuName("자유게시판")
                .writeYn(true)
                .deleteYn(false)
                .build();

        boardMenuRepository.save(request);

        //when
        Optional<BoardMenu> boardMenu = boardMenuRepository.findByMenuPathAndDeleteYnFalse("/free");

        //then
        assertTrue(boardMenu.isPresent());
        assertEquals("/free", boardMenu.get().getMenuPath());
        assertEquals("자유게시판", boardMenu.get().getMenuName());
        assertTrue(boardMenu.get().getWriteYn());
        assertFalse(boardMenu.get().getDeleteYn());
    }

    @Test
    @DisplayName("커뮤니티 메뉴 - 없거나 삭제된 메뉴 조회")
    void test3() {

        //given
        BoardMenu request = BoardMenu.builder()
                .menuPath("/free")
                .menuName("자유게시판")
                .writeYn(true)
                .deleteYn(true)
                .build();

        boardMenuRepository.save(request);

        //when
        Optional<BoardMenu> boardMenuFree = boardMenuRepository.findByMenuPathAndDeleteYnFalse("/free");
        Optional<BoardMenu> boardMenuNotice = boardMenuRepository.findByMenuPathAndDeleteYnFalse("/notice");

        //then
        assertFalse(boardMenuFree.isPresent());
        assertFalse(boardMenuNotice.isPresent());
    }
}