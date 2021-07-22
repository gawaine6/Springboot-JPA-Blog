package com.k.blog.config.auth;

import com.k.blog.model.Member;
import com.k.blog.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {

    // 스프링이 로그인 요청 들어오면 username, password를 가로챈다
    // password는 알아서 처리하고
    // username이 DB에 있는지만 확인해주면 된다

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member principal = memberRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("No Username" + username);
                });
        return new PrincipalDetail(principal);
    }
}
