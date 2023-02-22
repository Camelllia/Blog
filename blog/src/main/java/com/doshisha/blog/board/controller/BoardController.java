package com.doshisha.blog.board.controller;

import com.doshisha.blog.board.service.BoardService;
import com.doshisha.blog.boardMenu.service.BoardMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final BoardMenuService boardMenuService;

    @GetMapping("/community")
    public String main(Model model) {
        model.addAttribute("boardMenuList", boardMenuService.findAllBoardMenu());
        model.addAttribute("boardList", boardService.findMainBoardList());
        return "community/main";
    }

    @GetMapping("/community/{menuId}")
    public String list(@PathVariable("menuId") Long menuId, Model model) {
        model.addAttribute("boardMenuInfo", boardMenuService.findBoardMenuById(menuId));
        model.addAttribute("boardMenuList", boardMenuService.findAllBoardMenu());
        model.addAttribute("boardList", boardService.findBoardListByMenu(menuId));

        log.info("menuId : {}", menuId);
        log.info("boardMenuInfo : {}", boardMenuService.findBoardMenuById(menuId));
        log.info("boardList : {}", boardService.findBoardListByMenu(menuId));

        return "/community/list";
    }

    @GetMapping("/community/{menuId}/{boardId}")
    public String detail(@PathVariable("menuId") Long menuId, @PathVariable("boardId") Long boardId, Model model) {
        model.addAttribute("boardMenuInfo", boardMenuService.findBoardMenuById(menuId));
        model.addAttribute("boardMenuList", boardMenuService.findAllBoardMenu());
        model.addAttribute("boardItem", boardService.findBoardItem(menuId, boardId));

        log.info("boardItem : {}", boardService.findBoardItem(menuId, boardId));

        return "/community/detail";
    }
}
