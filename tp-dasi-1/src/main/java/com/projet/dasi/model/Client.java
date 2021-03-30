package com.projet.dasi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.OneToOne;

@Entity
public class Client implements Serializable {

    /* Attributes */
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
    private Date dateNaissance;
    
    @OneToOne
    private ProfilAstral profilAstral;
    
    /* Constructors */
    public Client(String nom, String prenom, String mail, String motDePasse, String telephone, String codePostal, Date dateNaissance, ProfilAstral profilAstral) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.codePostal = codePostal;
        this.dateNaissance = dateNaissance;
        this.profilAstral = profilAstral;
        
    }
    public Client() {
    }
    
    /* Getters / Setters */
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

    public String getCodePostal() {
        return codePostal;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public ProfilAstral getProfilAstral() {
        return profilAstral;
    }

    public void setProfilAstral(ProfilAstral profilAstral) {
        this.profilAstral = profilAstral;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    
    /* Overrided methods */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistence.Client[ id=" + id + " ]";
    }
    
}
