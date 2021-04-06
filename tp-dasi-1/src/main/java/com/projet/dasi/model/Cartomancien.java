/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.model;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author picka
 */
@Entity
@DiscriminatorValue("Cartomancien")
public class Cartomancien extends Medium {
    
    public Cartomancien(String presentation, String denomination, Genre genre) {
        super(presentation, denomination, genre);
    }
    public Cartomancien() {
    }
    
    @Override
    public String toString() {
        return "-> Cartomancien: " + this.toString();
    }
}