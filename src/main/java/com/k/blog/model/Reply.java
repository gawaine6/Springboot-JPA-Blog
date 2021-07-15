package com.k.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 200)
    private String content;

    // 누가? 어느 게시글에 썼지?
    @ManyToOne // Many = Reply, One = Board
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne // Many = Reply, One = Member
    @JoinColumn(name = "memberId")
    private Member member;

    @CreationTimestamp
    private Timestamp createDate;
}
