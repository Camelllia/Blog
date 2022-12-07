package com.doshisha.blog.community.controller;

import com.doshisha.blog.community.service.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CommunityController {

    private final CommunityService communityService;

    @GetMapping("/community")
    public String main(Model model) {
        model.addAttribute("boardMenuList", communityService.selectBoardMenuList());
        System.out.println(communityService.selectBoardMenuList());
        return "/community/main";
    }

    @GetMapping("/community/{menuPath}")
    public String list(@PathVariable("menuPath") String menuPath, Model model) {
        model.addAttribute("boardMenuInfo", communityService.selectBoardMenu(menuPath));
        model.addAttribute("boardMenuList", communityService.selectBoardMenuList());
        return "/community/list";
    }
}
