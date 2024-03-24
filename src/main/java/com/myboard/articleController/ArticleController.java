package com.myboard.articleController;

import dto.ArticleForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ArticleController {
    @GetMapping("/articles/new")
    public String newArticleForm(){
        return "/articles/new";
    }


    @PostMapping("/articles/create")
    public String CreateArticle(ArticleForm form, Model model){
        model.addAttribute("result", form.toString());
        // 1. DTO를 Entity로 저장하기
        // 2. Repository를 이용해 Entity를 DB에 저장하기
        return "/result";
    }
}
