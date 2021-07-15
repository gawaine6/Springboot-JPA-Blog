package com.k.blog.service;

import com.k.blog.model.Member;
import com.k.blog.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Transactional
    // 전체가 성공하면 commit, 실패하면 rollback
    public void 회원가입(Member member) {
        memberRepository.save(member);
    }
}
