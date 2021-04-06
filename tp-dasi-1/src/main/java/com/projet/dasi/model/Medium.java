package com.projet.dasi.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="typeMedium")
public class Medium implements Serializable {

    /* Attributs */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    @Enumerated(EnumType.STRING)
    private Genre genre;
    private String denomination;
    private String presentation;

    /* Constructeurs */
    public Medium(String denomination, String presentation, Genre genre) {
        this.denomination = denomination;
        this.presentation = presentation;
        this.genre = genre;
    }
    public Medium() {
    }

    /* Accesseurs */
    public String getDenomination() {
        return denomination;
    }
    public void setDenomination(String denomination) {
        this.denomination = denomination;
    }
    public String getPresentation() {
        return presentation;
    }
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }
    public Genre getGenre() {
        return genre;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    
    /* ToString */
    @Override
    public String toString() {
        return "id=" + id + ", denomination=" + denomination + ", presentation=" + presentation;
    }
   
}
