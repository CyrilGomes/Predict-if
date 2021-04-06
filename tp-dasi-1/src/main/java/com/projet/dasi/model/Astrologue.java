package com.projet.dasi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Astrologue")
public class Astrologue extends Medium {
    
    /* Attributs */
    private String formation;
    private String  promotion;
    
    /* Constructeurs */
    public Astrologue(String presentation, String denomination, Genre genre, String formation, String promotion) {
        super(presentation, denomination, genre);
        this.formation = formation;
        this.promotion = promotion;
    }
    public Astrologue() {
    }
    
    /* Accesseurs */
    public String getFormation() {
        return formation;
    }
    public void setFormation(String formation) {
        this.formation = formation;
    }
    public String getPromotion() {
        return promotion;
    }
    public void setPromotion(String promotion) {
        this.promotion = promotion;
    }

    /* ToString*/
    @Override
    public String toString() {
        return "-> Astrologue: " + super.toString() + ", formation=" + formation + ", promotion=" + promotion;
    }
    
}