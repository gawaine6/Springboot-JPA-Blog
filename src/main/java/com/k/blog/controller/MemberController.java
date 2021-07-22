package com.k.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

// 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth/** 허용
// 그냥 주소가 / 이면 index.html 허용
// static 이하에 있는 /js/**, /css/**, /img/** 허용, 이거 config 안에 있는 그 내용이네
@Controller
public class MemberController {

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "member/joinForm";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm() {
        return "member/loginForm";
    }
}
