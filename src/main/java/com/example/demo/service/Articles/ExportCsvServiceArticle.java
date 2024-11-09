package com.example.demo.service.Articles;

import com.example.demo.dto.ArticleDto;
import com.example.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;

@Service
public class ExportCsvServiceArticle {

    @Autowired
    private ArticleService articleService;

    public void exportBase(PrintWriter writer) {

        Iterable<ArticleDto> articleDtos = articleService.findAll();

        writer.println("Libelle ; Prix ; Image_URL ; Quantite");

        for (ArticleDto articleDto : articleDtos) {

            writer.println("\"" +articleDto.getLibelle() + "\"" +
                    ";" + "\"" + articleDto.getPrix() + "\"" +
                    ";" + "\"" + articleDto.getImageUrl() + "\"" +
                    ";" + "\"" + articleDto.getQuantite() + "\""
            );
        }

        writer.close();
    }
}
