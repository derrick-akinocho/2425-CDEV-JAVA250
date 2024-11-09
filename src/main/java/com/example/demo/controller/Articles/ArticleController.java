package com.example.demo.controller.Articles;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping("/articles/{articleId}")
    public ModelAndView articleById(@PathVariable("articleId") Long articleId) {
        ModelAndView modelAndView = new ModelAndView("article");

        ArticleDto article = articleService.findById(articleId);

        modelAndView.addObject("article", article);

        return modelAndView;
    }
}
