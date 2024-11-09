package com.example.demo.service;

import com.example.demo.entity.ArticleEntity;
import com.example.demo.entity.ClientEntity;
import com.example.demo.entity.FactureEntity;
import com.example.demo.entity.LigneFactureEntity;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDate;

/**
 * Classe permettant d'insérer des données dans l'application.
 */
@Service
@Transactional
public class InitData implements ApplicationListener<ApplicationReadyEvent> {

    private final EntityManager entityManager;

    public InitData(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        insertTestData();
    }

    private void insertTestData() {
        // Init Articles
        ArticleEntity a1 = createArticle("Les conserves de viande de licorne", 22.98, "https://static.hitek.fr/img/actualite/2016/08/26/41gn6tpvqtl.jpg", 10);
        ArticleEntity a2 = createArticle("Wenger Couteau suisse géant", 46.39, "https://static.hitek.fr/img/actualite/2016/08/26/61abqa-gt8s-sx522.jpg", 14);
        ArticleEntity a3 = createArticle("PAPIER TOILETTE DONALD TRUMP", 4.99, "https://static.hitek.fr/img/actualite/2016/08/26/61cb4xnrbol-sx522.jpg", 2);
        ArticleEntity a4 = createArticle("Grattoir pour Chat en Forme de Platine de DJ", 23.14, "https://static.hitek.fr/img/actualite/2016/08/26/61griray9-l-sx522.jpg", 5);
        ArticleEntity a5 = createArticle("Jay nothing", 2, "https://static.hitek.fr/img/actualite/2016/08/26/61vu-jqjygl-sy679.jpg", 1);
        ArticleEntity a6 = createArticle("UN AFFINEUR DE VISAGE", 52, "https://static.hitek.fr/img/actualite/2016/08/26/w_41r-1yapf5l.jpg", 5);

        // Init Clients
        ClientEntity c1 = createClient("Dupont", "Jean", LocalDate.of(1934, 8, 11));
        ClientEntity c2 = createClient("Martin", "Marie", LocalDate.of(1993, 9, 10));
        ClientEntity c3 = createClient("Durand", "Pierre", LocalDate.of(1955, 2, 16));
        ClientEntity c4 = createClient("Lefevre", "Sophie", LocalDate.of(1981, 2, 28));

        // Factures pour le client c1
        FactureEntity f1 = createFacture(true, LocalDate.of(2023, 2, 1), c1);
        LigneFactureEntity lf11 = createLigneFacture(1, f1, a1);
        LigneFactureEntity lf12 = createLigneFacture(6, f1, a3);

        FactureEntity f2 = createFacture(true, LocalDate.of(2024, 3, 15), c1);
        LigneFactureEntity lf21 = createLigneFacture(1, f2, a1);
        LigneFactureEntity lf22 = createLigneFacture(6, f2, a3);
        LigneFactureEntity lf23 = createLigneFacture(4, f2, a6);

        // Factures pour le client c2
        FactureEntity f3 = createFacture(true, LocalDate.of(2022, 2, 15), c2);
        LigneFactureEntity lf31 = createLigneFacture(1, f3, a2);

        FactureEntity f4 = createFacture(true, LocalDate.of(2023, 5, 20), c2);
        LigneFactureEntity lf41 = createLigneFacture(2, f4, a4);
        LigneFactureEntity lf42 = createLigneFacture(3, f4, a5);

        // Factures pour le client c3
        FactureEntity f5 = createFacture(true, LocalDate.of(2023, 7, 10), c3);
        LigneFactureEntity lf51 = createLigneFacture(1, f5, a1);
        LigneFactureEntity lf52 = createLigneFacture(2, f5, a6);

        FactureEntity f6 = createFacture(true, LocalDate.of(2024, 1, 5), c3);
        LigneFactureEntity lf61 = createLigneFacture(3, f6, a3);
        LigneFactureEntity lf62 = createLigneFacture(1, f6, a4);

        // Factures pour le client c4
        FactureEntity f7 = createFacture(true, LocalDate.of(2023, 10, 1), c4);
        LigneFactureEntity lf71 = createLigneFacture(1, f7, a2);
        LigneFactureEntity lf72 = createLigneFacture(2, f7, a5);

        FactureEntity f8 = createFacture(true, LocalDate.of(2024, 4, 12), c4);
        LigneFactureEntity lf81 = createLigneFacture(1, f8, a1);
        LigneFactureEntity lf82 = createLigneFacture(1, f8, a6);

    }

    private ArticleEntity createArticle(String libelle, double prix, String imageUrl, int quantite) {

        ArticleEntity a1 = new ArticleEntity();
        a1.setLibelle(libelle);
        a1.setPrix(prix);
        a1.setImageUrl(imageUrl);
        a1.setQuantite(quantite);
        entityManager.persist(a1);
        return a1;
    }

    private ClientEntity createClient(String nom, String prenom, LocalDate dateNaissance) {

        ClientEntity c1 = new ClientEntity();
        c1.setNom(nom);
        c1.setPrenom(prenom);
        c1.setDateNaissance(dateNaissance);
        entityManager.persist(c1);
        return c1;
    }

    private LigneFactureEntity createLigneFacture(int quantite, FactureEntity facture, ArticleEntity article) {

        LigneFactureEntity ligneFacture = new LigneFactureEntity();
        ligneFacture.setQuantite(quantite);
        ligneFacture.setArticle(article);
        entityManager.persist(ligneFacture);
        facture.getLigneFactures().add(ligneFacture);
        return ligneFacture;
    }

    private FactureEntity createFacture(boolean isPaye, LocalDate dateFinalisation, ClientEntity client) {

        FactureEntity facture = new FactureEntity();
        facture.setClient(client);
        entityManager.persist(facture);
        return facture;
    }

}
