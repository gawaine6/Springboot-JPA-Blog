package com.k.blog.service;

import com.k.blog.model.Member;
import com.k.blog.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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

    @Transactional(readOnly = true) // Select할 때 트랜잭션 시작, 서비스 종료시에 트랜잭션 종료(정합성 유지)
    public Member 로그인(Member member) {
        return memberRepository.findByMemberNameAndPassword(member.getMemberName(), member.getPassword());
    }
}
