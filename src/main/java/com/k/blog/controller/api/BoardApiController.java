package com.k.blog.controller.api;

import com.k.blog.config.auth.PrincipalDetail;
import com.k.blog.dto.ResponseDto;
import com.k.blog.model.Board;
import com.k.blog.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class BoardApiController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/api/board")
    public ResponseDto<Integer> save(@RequestBody Board board, @AuthenticationPrincipal PrincipalDetail principal) {
        // PrincipalDetail은 사용자 정보 받아오는 용도
        log.info("Board Write Api Post Controller");
        boardService.글쓰기(board, principal.getMember());
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
        // 제대로 되면 OK 상태 값을 반환하는데 이게 값이 200
    }
}
