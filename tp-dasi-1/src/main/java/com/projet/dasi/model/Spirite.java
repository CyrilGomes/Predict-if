package com.projet.dasi.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Spirite")
public class Spirite extends Medium implements Serializable {

    /* Attributes */
    private static final long serialVersionUID = 1L;
    
    private String support;
    
    /* Constructors */
    public Spirite(String denomination, String presentation, Genre genre, String support) {
        super(denomination, presentation, genre);
        this.support = support;
    }
    public Spirite() {
    }

    @Override
    public String toString() {
        return this.toString() + ", support=" + support;
    }

}
