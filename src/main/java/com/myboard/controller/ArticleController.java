package com.myboard.controller;

import com.myboard.dto.ArticleForm;
import com.myboard.dto.ArticleForm_Old;
import com.myboard.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.myboard.repository.ArticleRepository;

import java.util.ArrayList;
import java.util.List;

@Slf4j   // 로깅을 위한 추가
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
//        System.out.println(saved);
        log.info(saved.toString());
        return "/result";
    }

    @GetMapping("/articles/{id}")
    public String showOneArticle(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //log.info(articleEntity.toString());
        ArticleForm dto = ArticleForm.to(articleEntity);
        log.info(dto.toString());

        // 2. 모델에 데이터 등록하기
        model.addAttribute("dto", dto);
        // 3. 뷰  페이지 반환하기
        return "/articles/show";
    }

    @GetMapping("/articles")
    public String showAllArticles(Model model){
        // 1. 모든 데이터 가져오기
        List<Article> articleList = new ArrayList<>();
        List<ArticleForm> articleFormList = new ArrayList<>();
        ArticleForm form;
        articleList = articleRepository.findAll();
        for(Article article : articleList){
            form = ArticleForm.to(article);
            articleFormList.add(form);
        }

        // 2. dtoList 에 담기
        model.addAttribute("articles", articleFormList);
        // 3. 뷰페이지에 전달하기
        return "/articles/show_all";
    }
}
