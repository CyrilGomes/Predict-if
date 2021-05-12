package com.projet.dasi.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Consultation implements Serializable {
    
    /* Attributs*/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Enumerated(EnumType.STRING)
    private Etat etat;
    private Date dateDebut;
    private Date dateFin;
    private String commentaire;
    @ManyToOne
    private Employe employe;
    @ManyToOne
    private Client client;
    @ManyToOne
    private Medium medium;

    /* Constructeurs */
    public Consultation(Employe employe, Client client, Medium medium) {
        this.employe = employe;
        this.client = client;
        this.medium = medium;
        this.etat = Etat.EnAttenteEmploye;
    }
    public Consultation() {
    }
    
    /* Accesseurs */
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public String getCommentaire() {
        return commentaire;
    }
    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Etat getEtat() {
        return etat;
    }
    public void setEtat(Etat etat) {
        this.etat = etat;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Medium getMedium() {
        return medium;
    }
    public void setMedium(Medium medium) {
        this.medium = medium;
    }

    /* ToString */
    @Override
    public String toString() {
        return "Consultation{" + "etat=" + etat + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", commentaire=" + commentaire + ", employe=" + employe + ", client=" + client + ", medium=" + medium + ", id=" + id + '}';
    }
}
