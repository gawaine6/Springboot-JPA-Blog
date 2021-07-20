package com.k.blog.controller.api;

import com.k.blog.dto.ResponseDto;
import com.k.blog.model.Member;
import com.k.blog.model.RoleType;
import com.k.blog.service.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@Slf4j
public class MemberApiController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/api/join")
    public ResponseDto<Integer> save(@RequestBody Member member) {
        log.info("Member Join Api Post Controller");
        member.setRole(RoleType.USER);
        memberService.회원가입(member);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // 제대로 되면 OK 상태 값을 반환하는데 이게 값이 200
    }

     /*
    @PostMapping("/api/login")
    public ResponseDto<Integer> login(@RequestBody Member member, HttpSession session) {
        log.info("Member Login Api Post Controller");
        Member loginMember = memberService.로그인(member);
        // 세션 만들어주기
        if(loginMember != null) {
            session.setAttribute("loginMember", loginMember);
            new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        }
        return new ResponseDto<Integer>(HttpStatus.INTERNAL_SERVER_ERROR.value(), 404);
    }
     */
}
