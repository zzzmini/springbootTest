package com.myboard.dto;

import com.myboard.entity.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
@Getter
@Setter
public class ArticleForm {
    private Long id;
    private String title;  // 제목을 받을 필드
    private String content;  // 내용을 받을 필드

    public Article toEntity(){
        return new Article(id, title, content);
    }

    public static ArticleForm to(Article article){
        return new ArticleForm(
                article.getId(),
                article.getTitle(),
                article.getContent()
                );
    }
}
