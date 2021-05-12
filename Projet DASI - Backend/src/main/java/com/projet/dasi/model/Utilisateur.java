package com.projet.dasi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Utilisateur implements Serializable {

    /* Attributs */
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique=true)
    private String mail;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String telephone;
    private String codePostal;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    
    /* Constructeurs */
    public Utilisateur(String prenom, String nom, String mail, String motDePasse, String telephone, String codePostal, Date dateNaissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.codePostal = codePostal;
        this.dateNaissance = dateNaissance;        
    }
    public Utilisateur() {
    }
    
    /* Accesseurs */
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNom() { 
        return nom; 
    }
    public void setNom(String nom) { 
        this.nom = nom; 
    }
    public String getPrenom() { 
        return prenom; 
    }
    public void setPrenom(String prenom) { 
        this.prenom = prenom; 
    }
    public String getMail() { 
        return mail; 
    }
    public void setMail(String mail) { 
        this.mail = mail; 
    }
    public String getMotDePasse() { 
        return motDePasse; 
    }
    public void setMotDePasse(String motDePasse) { 
        this.motDePasse = motDePasse; 
    }    
    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getCodePostal() {
        return codePostal;
    }
    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    /* ToString */
    @Override
    public String toString() {
        String clientToString = "id=" + id + ", mail=" + mail + ", motDePasse=" + motDePasse + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", codePostal=" + codePostal + ", dateNaissance=" + dateNaissance.toString();
        return clientToString;
    }
}
