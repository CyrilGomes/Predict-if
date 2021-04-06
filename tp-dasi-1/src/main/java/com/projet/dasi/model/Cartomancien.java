package com.projet.dasi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cartomancien")
public class Cartomancien extends Medium {
    
    /* Constructeurs */
    public Cartomancien(String presentation, String denomination, Genre genre) {
        super(presentation, denomination, genre);
    }
    public Cartomancien() {
    }
    
    /* ToString */
    @Override
    public String toString() {
        return "-> Cartomancien: " + super.toString();
    }
}