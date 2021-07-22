package com.k.blog.repository;

import com.k.blog.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;

// 자동으로 bean 등록이 된다
//@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
