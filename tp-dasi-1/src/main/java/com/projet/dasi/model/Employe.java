/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 *
 * @author creep
 */
@Entity
public class Employe extends Utilisateur {
    @Enumerated(EnumType.STRING)
    private Genre genre;
    
    public Employe(){
        
    }

    public Employe(Genre genre, String nom, String prenom, String mail, String motDePasse, String telephone, String codePostal, Date dateNaissance) {
        super(nom, prenom, mail, motDePasse, telephone, codePostal, dateNaissance);
        this.genre = genre;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "-> Employe: " + super.toString() + " " + genre.toString();
    }    
}
