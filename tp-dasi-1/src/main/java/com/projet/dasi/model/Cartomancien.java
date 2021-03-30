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
public class Cartomancien extends Medium{
    Cartomancien(String presentation, String denomination){
        super(presentation,denomination);
    }
    Cartomancien(){
        
    }

    @Override
    public String toString() {
        return "Cartomancien{" +this.toString()+ '}';
    }
}
