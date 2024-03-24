package com.myboard.controller;

import com.myboard.dto.ArticleForm_Old;
import com.myboard.entity.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.myboard.repository.ArticleRepository;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }


    @PostMapping("/articles/create")
    public String CreateArticle(ArticleForm_Old form, Model model){
        model.addAttribute("result", form.toString());
        // 1. DTO를 Entity로 저장하기
        Article article = form.toEntity();
        // 2. Repository를 이용해 Entity를 DB에 저장하기
        Article saved = articleRepository.save(article);
        return "/result";
    }
}