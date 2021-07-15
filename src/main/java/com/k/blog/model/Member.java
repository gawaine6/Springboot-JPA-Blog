package com.k.blog.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder // 빌더 패턴
// @DynamicInsert
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    @GenericGenerator(
//            name = "userId",
//            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator"
//            // https://wafy.tistory.com/entry/Hibernate5-오라클-시퀀스-orghibernateidSequenceHiLoGenerator
//    )
    private Long id; // 시퀀스

    @Column(nullable = false, length = 30, unique = true)
    private String memberName; // 아이디

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String email;

    // @ColumnDefault("'user'") // 넣을 때 '' 넣어줘야된다
    @Enumerated(EnumType.STRING)
    private RoleType role; // enum을 쓰는게 좋다
    /*
    DB에 insert하면 왜인지 null로 뜬다, 분명히 디폴트로 해줬는데 왜 그럴까
    insert
            into
    Member
            (createDate, email, memberName, password, role, id)
    values
            (?, ?, ?, ?, ?, ?)

    쿼리를 보니 role이 들어간다, 근데 디폴트 값을 설정해줬으면 role을 삽입하지 않아야한다
    위의 쿼리를 보면 role이 들어가면서 null로 입력되기 때문에 내가 디폴트로 user를 설정해놔도 디비 상에는 null로 보이게 된다
    이거 때문에 쓰는게 @DynamicInsert --> insert할 때 null인 필드는 제외하고 insert한다
    근데 어노테이션 너무 덕지덕지 붙이면 개 더럽다, 그래서 어노테이션 다 빼버릴꺼임
    */


    @CreationTimestamp // 시간 자동 입력
    private Timestamp createDate;

}
