package com.busanit.springmvc.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@NoArgsConstructor  // 기본 생성자
@AllArgsConstructor // 모든 필드를 파라미터로 받는 생성자
@ToString
@Getter
@Entity         // Entity 선언

public class Article {
    @Id         // 식별자(PK)
    @GeneratedValue     // 숫자 자동생성
    private Long id;
    @Column     // DB 테이블의 열과 연결
    private String title;
    @Column
    private String content;

    // 수정 메소드
    public void patch(Article article) {
        if (article.title != null){
            this.title = article.title;
        }
        if(article.content != null){
            this.content = article.content;
        }
    }
}