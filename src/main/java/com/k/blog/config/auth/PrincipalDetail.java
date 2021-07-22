package com.k.blog.config.auth;

import com.k.blog.model.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Getter // 글쓰기할 때 사용자 정보가 필요하다, 그 정보를 여기서 받아올거기 때문에 요거 넣어준다
public class PrincipalDetail implements UserDetails {

    private Member member;

    public PrincipalDetail(Member member) {
        this.member = member;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        // 안에 메소드가 하나 밖에 없어서 편하게 걍 람다로 씀
        collection.add(() -> {
            return "ROLE_" + member.getRole();
        });
        return collection;
    }
}
