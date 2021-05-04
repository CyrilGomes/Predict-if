package com.projet.dasi.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Spirite")
public class Spirite extends Medium implements Serializable {

    /* Attributs */
    private static final long serialVersionUID = 1L;
    
    private String support;
    
    /* Constructeurs */
    public Spirite(Genre genre, String denomination, String presentation, String support) {
        super(genre, denomination, presentation);
        this.support = support;
    }
    public Spirite() {
    }

    /* ToString */
    @Override
    public String toString() {
        return "-> Cartomancien: " + super.toString() + ", support=" + support;
    }

}
