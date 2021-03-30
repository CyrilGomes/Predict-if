package com.projet.dasi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public abstract class Utilisateur implements Serializable {

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
    
    /* Constructors */
    public Utilisateur(String nom, String prenom, String mail, String motDePasse, String telephone, String codePostal, Date dateNaissance) {
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
        if (!(object instanceof Utilisateur)) {
            return false;
        }
        Utilisateur other = (Utilisateur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String clientToString = "id=" + id + ", mail=" + mail + ", motDePasse=" + motDePasse + ", nom=" + nom + ", prenom=" + prenom + ", telephone=" + telephone + ", codePostal=" + codePostal + ", dateNaissance=" + dateNaissance.toString();
        return clientToString;
    }
 
    /*
        private String mail;
    private String motDePasse;
    private String nom;
    private String prenom;
    private String telephone;
    private String codePostal;
    private Date dateNaissance;
    
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch=FetchType.EAGER)
    private ProfilAstral profilAstral;
    */
}
