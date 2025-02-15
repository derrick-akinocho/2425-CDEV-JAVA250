package com.example.demo.service.impl;

import com.example.demo.dto.ArticleDto;
import com.example.demo.repository.ArticleRepository;
import com.example.demo.service.ArticleService;
import com.example.demo.service.mapper.ArticleMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Service contenant les actions métiers liées aux articles.
 */
@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final ArticleMapper articleMapper;

    public ArticleServiceImpl(ArticleRepository articleRepository, ArticleMapper articleMapper) {
        this.articleRepository = articleRepository;
        this.articleMapper = articleMapper;
    }

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll().stream().map(articleMapper::transformerEnDto).collect(toList());
    }

    @Override
    public ArticleDto findById(Long id) {
        return articleRepository.findById(id).map(articleMapper::transformerEnDto).orElse(null);
    }

}
