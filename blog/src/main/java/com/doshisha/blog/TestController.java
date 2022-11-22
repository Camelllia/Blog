package com.doshisha.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j
public class TestController {

    @GetMapping("/")
    public String home(Model model){
        return "home";
    }
    @GetMapping("/user")
    public String dispUser(Model model){
        return "/user/user";
    }
    @GetMapping("/manager")
    public String dispManager(Model model){
        return "/user/manager";
    }
    @GetMapping("/admin")
    public String dispAdmin(Model model){
        return "/user/admin";
    }
}
