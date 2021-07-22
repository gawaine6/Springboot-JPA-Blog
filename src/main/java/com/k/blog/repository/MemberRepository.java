package com.k.blog.repository;

import com.k.blog.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// 자동으로 bean 등록이 된다
//@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // JPA Naming 전략
//    @Query(value = "select * from Member where membername =? and password =?", nativeQuery = true)
//    Member findByMemberNameAndPassword(String memberName, String password);
    Optional<Member> findByUsername(String username);
}
