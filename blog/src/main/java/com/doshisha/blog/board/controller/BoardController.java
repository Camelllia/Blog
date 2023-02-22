package com.doshisha.blog.board.controller;

import com.doshisha.blog.board.service.BoardService;
import com.doshisha.blog.boardMenu.service.BoardMenuService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.Id;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    private final BoardMenuService boardMenuService;

    @GetMapping("/community")
    public String main(Model model) {
        model.addAttribute("boardMenuList", boardMenuService.findAllBoardMenu());
        return "community/main";
    }

    @GetMapping("/community/{id}")
    public String list(@PathVariable("id") Long menuId, Model model) {
        model.addAttribute("boardMenuInfo", boardMenuService.findBoardMenuById(menuId));
        model.addAttribute("boardMenuList", boardMenuService.findAllBoardMenu());

        log.info("menuId : {}", menuId);
        log.info("boardMenuInfo : {}", boardMenuService.findBoardMenuById(menuId));

        return "/community/list";
    }
}
