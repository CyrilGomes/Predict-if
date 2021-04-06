package com.projet.dasi.model;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Client extends Utilisateur {
    
    /* Attributs */
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private ProfilAstral profilAstral;
    
    /* Constructeurs */
    public Client(String nom, String prenom, String mail, String motDePasse, String telephone, String codePostal, Date dateNaissance) {
        super(nom, prenom, mail, motDePasse, telephone, codePostal, dateNaissance);
    }    
    public Client(){
    }

    /* Accesseurs */
    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }
    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    /* ToString */
    @Override
    public String toString() {
        return "-> Client: " + super.toString() + "\n" + profilAstral.toString();
    }    
}
