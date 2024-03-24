package com.myboard.dto;

import com.myboard.entity.Article;

public class ArticleForm_Old {
    private String title;  // 제목을 받을 필드
    private String content;  // 내용을 받을 필드

    @Override
    public String toString() {
        return "ArticleForm{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleForm_Old(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity(){
        return new Article(null, title, content);
    }
}
