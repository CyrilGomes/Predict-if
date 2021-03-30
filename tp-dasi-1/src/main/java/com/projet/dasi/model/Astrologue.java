/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.model;

import javax.persistence.Entity;

/**
 *
 * @author picka
 */
@Entity
public class Astrologue extends Medium{
    private String formation;
    private String  promotion;

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
    
    public Astrologue() {
    }

    public Astrologue(String presentation, String denomination, String formation, String promotion) {
        this.formation = formation;
        this.promotion = promotion;
        this(presentation,denomination);
    }

    public String toString() {
        return "Astrologue{"+ this.toString() + ", formation=" + formation + ", promotion=" + promotion + '}';
    }
    
}
