package com.example.demo.service.mapper;

import com.example.demo.dto.FactureDto;
import com.example.demo.dto.LigneFactureDto;
import com.example.demo.entity.FactureEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FactureMapper {

    public FactureDto transformerEnDto(FactureEntity factureEntity) {

        Double prixTotal = factureEntity.getLigneFactures().stream()
                .map(ligne -> ligne.getQuantite() * ligne.getArticle().getPrix())
                .reduce(0d, Double::sum);

        List<LigneFactureDto> ligneFactureDtos = factureEntity.getLigneFactures().stream()
                .map(ligne -> new LigneFactureDto(ligne.getArticle().getLibelle(), ligne.getArticle().getPrix(), ligne.getQuantite()))
                .collect(Collectors.toList());

        return new FactureDto(
                factureEntity.getId(),
                factureEntity.getDate(),
                factureEntity.getClient().getNom(),
                factureEntity.getClient().getPrenom(),
                ligneFactureDtos,
                prixTotal
        );
    }

}
