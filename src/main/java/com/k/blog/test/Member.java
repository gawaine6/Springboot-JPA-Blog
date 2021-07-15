package com.k.blog.test;

import lombok.*;

// @AllArgsConstructor
// @NoArgsConstructor // 디폴트 생성자
@Getter
@Setter
// @RequiredArgsConstructor --> final 붙은 애들에 대한 생성자를 만들어준다
// Getter, Setter 한 번에 쓰고 싶으면 Data
public class Member {
    private int id;
    private String username;
    private String password;
    private String email;

    @Builder
    // 이 부분이 AllArgsConstructor
    public Member(int id, String username, String password, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
