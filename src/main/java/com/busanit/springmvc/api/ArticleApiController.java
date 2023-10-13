package com.busanit.springmvc.api;

import com.busanit.springmvc.dto.ArticleForm;
import com.busanit.springmvc.entity.Article;
import com.busanit.springmvc.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@Slf4j
@RequiredArgsConstructor
@RestController
public class ArticleApiController {
    // DI
    private final ArticleRepository articleRepository;
    // GET
    @GetMapping("/api/articles")
    public List<Article> index(){
        ArrayList<Article> all = articleRepository.findAll();
        return all;
    }
    @GetMapping("/api/articles/{id}")
    public Article show(@PathVariable Long id){
        return articleRepository.findById(id).orElse(null);
    }
    // POST
    @PostMapping("/api/articles")
    public Article create(@RequestBody ArticleForm dto){
        Article article = dto.toEntity();
        Article saved = articleRepository.save(article);
        return saved;
    }
    // PATCH
    @PatchMapping("/api/articles/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ArticleForm dto){
        // 1. DTO -> entity
        Article article = dto.toEntity();
        log.info(article.toString());
        // 2. 타겟 요청하기
        Article target = articleRepository.findById(id).orElse(null);
        log.info(article.toString());
        // 3. 잘못된 요청 처리
        if (target == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        // 4. 업데이트 및 정상 응답(200)하기
        target.patch(article);
        Article updated = articleRepository.save(target);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }
    // DELETE
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        Article article = articleRepository.findById(id).orElse(null);

        if (article == null){
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        articleRepository.delete(article);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
