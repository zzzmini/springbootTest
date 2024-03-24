package com.myboard.repository;

import com.myboard.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository  extends JpaRepository<Article, Long> {
}
