package com.doshisha.blog.member.controller;

import com.doshisha.blog.member.dto.LoginForm;
import com.doshisha.blog.member.dto.MemberForm;
import com.doshisha.blog.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String form(Model model) {
        model.addAttribute("userForm", new MemberForm());
        return "user/login/register";
    }

    @PostMapping("/join")
    @ResponseBody
    public void join(@RequestBody @Valid MemberForm request) throws Exception {
        memberService.join(request);
    }

    @GetMapping("/")
    public String index() {
        return "user/login/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public void login(@RequestBody @Valid LoginForm request) throws Exception {
        memberService.login(request);
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }
        return "redirect:/";
    }
}
