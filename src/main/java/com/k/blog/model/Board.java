package com.k.blog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob // 대용량 데이터
    private String content; // 섬머노트 라이브러리

    @ManyToOne(fetch = FetchType.EAGER)
    // Many = Board, Member = One --> 한명의 사용자는 여러 개의 게시글을 쓸 수 있다, 여러 개의 게시글은 한명의 사용자에 의해 쓰여질 수 있다
    // OneToMany --> One = Board, Many = Member --> 하나의 게시글은 여러 명의 사용자에 의해 쓰여진다, 뭔 개소리죠?? 말이 안됩니다
    // fetch = FetchType.EAGER --> 이 외래키를 내가 가져오는데 내가 가져올게 하나 밖에 없다
    @JoinColumn(name = "memberId")
    private Member member; // DB는 오브젝트를 저장할 수 없다. FK, 자바는 저장할 수 있다

    private int count; // 조회수

    @OneToMany(mappedBy = "board", fetch = FetchType.LAZY)
    // One = Board, Many = Reply --> 하나의 게시글에는 여러 개의 댓글이 달린다, 여러 개의 댓글은 하나의 게시글에 달릴 수 있다
    // 얘는 외래키가 필요없다, 테이블이 만들어지는 형태를 봤을 때 만약에 댓글번호가 외래키로 들어가면 DB 1법칙(원자성)에 위배된다
    // mappedBy - 연관관계의 주인이 아니다, 난 FK가 아니에요. 쉽게 생각해서 외래키가 있는 곳이 주인이다, Reply에 외래키가 있다 그러니까 Reply가 주인이다
    // board는 주인이 아니니까 mappedBy를 써서 주인이 아님을 명시해준다(뭔가 어렵네)
    // fetch = FetchType.LAZY --> 얘는 하나가 될 수도 수만건이 될 수도 있다, 기본적으로 LAZY 전략은 내가 필요할 때 가져오는 방법, ui따라 다른데 바로 전부 다 보고싶다 그러면 EAGER 하면 됨
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
