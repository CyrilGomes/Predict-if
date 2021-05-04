package com.projet.dasi.model;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Employe extends Utilisateur {
    
    /* Attributs */
    @Enumerated(EnumType.STRING)
    private Genre genre;

    /* Constructeurs */
    public Employe(Genre genre, String nom, String prenom, String mail, String motDePasse, String telephone, String codePostal, Date dateNaissance) {
        super(nom, prenom, mail, motDePasse, telephone, codePostal, dateNaissance);
        this.genre = genre;
    }
    public Employe(){
    }

    /* Accesseurs */
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    /* ToString */
    @Override
    public String toString() {
        return "-> Employe: " + super.toString() + " " + genre.toString();
    }    
}
