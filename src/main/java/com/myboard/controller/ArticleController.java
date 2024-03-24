package com.myboard.controller;

import com.myboard.dto.ArticleForm;
import com.myboard.dto.ArticleForm_Old;
import com.myboard.entity.Article;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.myboard.repository.ArticleRepository;
import org.springframework.web.servlet.view.RedirectView;

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
    public RedirectView createArticle(ArticleForm form, Model model){
        model.addAttribute("result", form.toString());
        // 1. DTO를 Entity로 저장하기
        Article article = form.toEntity();
        // 2. Repository를 이용해 Entity를 DB에 저장하기
        Article saved = articleRepository.save(article);
//        System.out.println(saved);
        log.info(saved.toString());
        String url = "/articles/" + saved.getId();
        return new RedirectView(url) ;
    }

    @GetMapping("/articles/{id}")
    public String showOneArticle(@PathVariable Long id, Model model){
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Article articleEntity = articleRepository.findById(id).orElse(null);
        //log.info(articleEntity.toString());
        ArticleForm dtoList = ArticleForm.to(articleEntity);
        log.info(dtoList.toString());

        // 2. 모델에 데이터 등록하기
        model.addAttribute("dto", dtoList);
        // 3. 뷰  페이지 반환하기
        return "/articles/show";
    }

    @GetMapping("/articles")
    public String showAllArticles(Model model){
        List<Article> articles = new ArrayList<>();
        List<ArticleForm> articleForms = new ArrayList<>();
        // 1. 모든 데이터 가져오기
        articles = articleRepository.findAll();

        for(Article article : articles){
            articleForms.add(ArticleForm.to(article));
        }

        // 2. 모델에 데이터 등록하기
        model.addAttribute("articles", articleForms);
        // 3. 뷰  페이지 반환하기
        return "/articles/show_all";
    }

    @GetMapping("/articles/{id}/update")
    public String viewUpdateArticle(@PathVariable Long id, Model model){
        // 수정할 데이터 가져오기
        Article article = articleRepository.findById(id).orElse(null);
        ArticleForm dto = ArticleForm.to(article);
        // 뷰 페이지 설정하기
        model.addAttribute("dto", dto);
        return "/articles/update";
    }

    @PostMapping("/articles/update")
    public RedirectView updateArticle(ArticleForm form){
        log.info("updateArticle : " + form.toString());
        Article saved;
        String url = "/articles/";
        // 1. DTO를 Entity로 저장하기
        Article article = form.toEntity();
        log.info("updateArticle : " +article.toString());
        // 2. Repository를 이용해 Entity를 DB에 저장하기
        Article target = articleRepository.findById(form.getId()).orElse(null);

        if(target != null){
            saved = articleRepository.save(article);
            url = url + saved.getId();
        }
        return new RedirectView(url) ;
    }
}
