package com.example.demo.dto;

import java.time.LocalDate;
import java.util.List;

/**
 * Classe permettant d'exposer des données au format JSON au client.
 */
public class FactureDto {

    private Long id;
    private LocalDate date;
    private String clientNom;
    private String clientPrenom;
    private List<LigneFactureDto> ligneFactures;
    private Double prixTotal;

    public FactureDto(Long id, LocalDate date, String clientNom, String clientPrenom, List<LigneFactureDto> ligneFactures, Double prixTotal) {
        this.id = id;
        this.date = date;
        this.clientNom = clientNom;
        this.clientPrenom = clientPrenom;
        this.ligneFactures = ligneFactures;
        this.prixTotal = prixTotal;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getClientNom() {
        return clientNom;
    }

    public void setClientNom(String clientNom) {
        this.clientNom = clientNom;
    }

    public String getClientPrenom() {
        return clientPrenom;
    }

    public void setClientPrenom(String clientPrenom) {
        this.clientPrenom = clientPrenom;
    }

    public List<LigneFactureDto> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFactureDto> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

    public Double getPrixTotal() {
        return prixTotal;
    }

    public void setPrixTotal(Double prixTotal) {
        this.prixTotal = prixTotal;
    }
}
