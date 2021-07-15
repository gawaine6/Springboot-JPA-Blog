package com.k.blog.test;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class Hello {

    private Long id;
    private String he;
    private String go;

    @Builder
    public Hello(Long id, String he, String go) {
        this.id = id;
        this.he = he;
        this.go = go;
    }
}
