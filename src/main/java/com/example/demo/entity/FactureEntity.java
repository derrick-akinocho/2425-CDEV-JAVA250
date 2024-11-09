package com.example.demo.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity représentant un article.
 */
@Entity
public class FactureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private ClientEntity client;

    @Column
    private LocalDate date;

    @OneToMany
    private List<LigneFactureEntity> ligneFactures = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<LigneFactureEntity> getLigneFactures() {
        return ligneFactures;
    }

    public void setLigneFactures(List<LigneFactureEntity> ligneFactures) {
        this.ligneFactures = ligneFactures;
    }

}
