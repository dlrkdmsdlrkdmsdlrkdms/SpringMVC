package com.busanit.springmvc.dto;

import com.busanit.springmvc.entity.Article;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
// DTO : Data Transfer Object 데이터 전달 객체
public class ArticleForm {
    private String title;
    private String content;

    // lombok : 요청받은 제목과 내용을 필드에 저장하는 생성자
    // lombok : 데이터를 잘 받았는 확인하는 메서드 (toString)

    // DTO를 Entity로 변환
    public Article toEntity() {
        return new Article(null, title, content);
    }
}
