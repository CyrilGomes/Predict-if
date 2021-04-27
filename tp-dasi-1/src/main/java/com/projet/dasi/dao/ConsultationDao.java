package com.projet.dasi.dao;

import com.projet.dasi.model.Client;
import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Etat;
import com.projet.dasi.model.Medium;
import java.util.HashMap;
import java.util.Map;

public class ConsultationDao {
    
    public ConsultationDao() {}
    
    /* Creer une consultation dans la DB */
    public void creer(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    /* Mettre à jour une consultation dans la DB */
    public Consultation mettreAJour(Consultation consultation) {
        return JpaUtil.obtenirContextePersistance().merge(consultation);
    }
    
    /* Chercher une consultation dans la DB par son ID */
    public Consultation chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
    }
    
    /* Chercher toutes les consultations de la DB */
    public List<Consultation> chercherTous() {
        String s = "SELECT c FROM Consultation c";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        return query.getResultList();
    }

    /* Chercher les consultations d'un certain client */
    public List<Consultation> chercherParClient(Client client) {
        String s = ""
                + "SELECT c FROM Consultation c "
                + "WHERE c.client = :unClient AND c.etat = :unEtat";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("unClient", client);
        query.setParameter("unEtat", Etat.Termine);
        return query.getResultList();
    }
    
    /* Chercher les consultations d'un certain client avec un certain médium */
    public List<Consultation> chercherParClientEtMedium(Client client, Medium medium) {
        String s = ""
                + "SELECT c FROM Consultation c "
                + "WHERE c.client = :unClient AND c.medium = :unMedium AND c.etat = :unEtat";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("unClient", client);
        query.setParameter("unMedium", medium);
        query.setParameter("unEtat", Etat.Termine);
        return query.getResultList();
    }
    
    /* Chercher les consultations attribuées à un certain employé */
    public Consultation chercherEnCoursParEmploye(Employe employe) {
        String s = ""
                + "SELECT c FROM Consultation c "
                + "WHERE c.employe = :unEmploye AND c.etat IN (:unEtat1, :unEtat2, :unEtat3) ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("unEmploye", employe);
        query.setParameter("unEtat1", Etat.EnAttenteEmploye);
        query.setParameter("unEtat2", Etat.EnAttenteClient);
        query.setParameter("unEtat3", Etat.EnCours);
        return (Consultation)query.getSingleResult();
    }
    
    /* Chercher le nombre de consultations attribuées à chaque médiums */
    public List<Object[]> obtenirNombreConsultationsParMedium() {
        String s = ""
                + "SELECT c.medium, COUNT(c) FROM Consultation c "
                + "GROUP BY c.medium ";
        TypedQuery<Object[]> query = JpaUtil.obtenirContextePersistance().createQuery(s, Object[].class);
        return query.getResultList();
    }    
    
    /* Chercher le top 5 des médiums avec le plus de consultations */
    public List<Object[]> obtenirTop5NombreConsultationsParMedium() {
        String s = ""
                + "SELECT c.medium, COUNT(c) AS s FROM Consultation c "
                + "GROUP BY c.medium "
                + "ORDER BY s ";
        TypedQuery<Object[]> query = JpaUtil.obtenirContextePersistance().createQuery(s, Object[].class);
        query.setFirstResult(0);
        query.setMaxResults(5);
        return query.getResultList();
    } 
    
    /* Chercher, pour chaque employé, le nombre de clients associés */
    public List<Object[]> chercherNbClientParEmploye(){
        String s = "SELECT e.nom, e.prenom, COUNT(DISTINCT c.client) " +
                   "FROM Consultation c " +
                   "JOIN c.employe e " +
                   "WHERE c.etat = :etat " +
                   "GROUP BY e.nom, e.prenom";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        query.setParameter("etat", Etat.Termine);
        return query.getResultList();
    }
}
