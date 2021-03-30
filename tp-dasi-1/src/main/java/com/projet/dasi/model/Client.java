/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author creep
 */
@Entity
public class Client extends Utilisateur {    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private ProfilAstral profilAstral;
    
    public Client(){
        
    }

    public Client(String nom, String prenom, String mail, String motDePasse, String telephone, String codePostal, Date dateNaissance) {
        super(nom, prenom, mail, motDePasse, telephone, codePostal, dateNaissance);
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    @Override
    public String toString() {
        return "-> Client: " + super.toString() + "\n" + profilAstral.toString();
    }    
}
