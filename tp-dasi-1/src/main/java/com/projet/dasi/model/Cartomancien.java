package com.projet.dasi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Cartomancien")
public class Cartomancien extends Medium {
    
    /* Constructeurs */
    public Cartomancien(Genre genre, String denomination, String presentation) {
        super(genre, denomination, presentation);
    }
    public Cartomancien() {
    }
    
    /* ToString */
    @Override
    public String toString() {
        return "-> Cartomancien: " + super.toString();
    }
}