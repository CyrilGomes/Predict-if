package com.projet.dasi.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProfilAstral implements Serializable {
    
    /* Attributs */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String SigneChinois;
    private String Couleur;
    private String SigneAstro;
    private String AnimalTotem;

    /* Constructeurs */
    public ProfilAstral(String SigneAstro, String SigneChinois, String Couleur, String AnimalTotem) {
        this.SigneChinois = SigneChinois;
        this.Couleur = Couleur;
        this.SigneAstro = SigneAstro;
        this.AnimalTotem = AnimalTotem;
    }
    public ProfilAstral() {
    }
    
    /* Accesseurs */
    public Long getId() {
        return id;
    }
    public String getSigneChinois() {
        return SigneChinois;
    }
    public String getCouleur() {
        return Couleur;
    }
    public String getSigneAstro() {
        return SigneAstro;
    }
    public String getAnimalTotem() {
        return AnimalTotem;
    }
    
    /* ToString */
    @Override
    public String toString() {
        return "ProfilAstral{" + "SigneChinois=" + SigneChinois + ", Couleur=" + Couleur + ", SigneAstro=" + SigneAstro + ", AnimalTotem=" + AnimalTotem + '}';
    }
}
