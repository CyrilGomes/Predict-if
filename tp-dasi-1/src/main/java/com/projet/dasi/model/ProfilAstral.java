/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

/**
 *
 * @author yoann */
@Entity
public class ProfilAstral {
    
    private String SigneChinois;
    private String Couleur;
    private String SigneAstro;
    private String AnimalTotem;

    public ProfilAstral(String SigneChinois, String Couleur, String SigneAstro, String AnimalTotem) {
        this.SigneChinois = SigneChinois;
        this.Couleur = Couleur;
        this.SigneAstro = SigneAstro;
        this.AnimalTotem = AnimalTotem;
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
}
