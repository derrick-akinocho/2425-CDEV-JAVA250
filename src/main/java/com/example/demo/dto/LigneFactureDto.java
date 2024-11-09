package com.example.demo.dto;

public class LigneFactureDto {

    private String article;
    private Double prixUnitaire;
    private Integer quantite;

    public LigneFactureDto(String article, Double prixUnitaire, Integer quantite) {
        this.article = article;
        this.prixUnitaire = prixUnitaire;
        this.quantite = quantite;
    }

    public String getArticle() {
        return article;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public Double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(Double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }
}
