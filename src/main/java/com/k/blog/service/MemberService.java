package com.k.blog.service;

import com.k.blog.model.Member;
import com.k.blog.model.RoleType;
import com.k.blog.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    // 전체가 성공하면 commit, 실패하면 rollback
    public void 회원가입(Member member) {
        log.info("Member Join Service Start");
        String rawPwd = member.getPassword();
        String encPwd = passwordEncoder.encode(rawPwd);
        member.setPassword(encPwd);
        member.setRole(RoleType.USER);
        memberRepository.save(member);
        log.info("Member Join Service End");
    }
}
