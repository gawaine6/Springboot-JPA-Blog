package com.k.blog.repository;

import com.k.blog.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// 자동으로 bean 등록이 된다
// @Repository 이거 생략 가능
public interface MemberRepository extends JpaRepository<Member, Long> {
}
