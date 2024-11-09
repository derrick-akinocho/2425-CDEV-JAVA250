package com.example.demo.service.mapper;

import com.example.demo.dto.ArticleDto;
import com.example.demo.entity.ArticleEntity;
import org.springframework.stereotype.Component;

@Component
public class ArticleMapper {

    public ArticleDto transformerEnDto(ArticleEntity article) {
        return new ArticleDto(article.getId(), article.getLibelle(), article.getPrix(), article.getImageUrl(), article.getQuantite());
    }

}
