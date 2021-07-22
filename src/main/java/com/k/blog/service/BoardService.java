package com.k.blog.service;

import com.k.blog.model.Board;
import com.k.blog.model.Member;
import com.k.blog.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public List<Board> 글목록() {
        return boardRepository.findAll();
    }

    @Transactional
    // 전체가 성공하면 commit, 실패하면 rollback
    public void 글쓰기(Board board, Member member) {
        log.info("Board Write Service");
        board.setCount(0);
        board.setMember(member); // 사용자 정보 받아온다
        boardRepository.save(board);
    }
}
