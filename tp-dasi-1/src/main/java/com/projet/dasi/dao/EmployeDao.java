/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.dao;

import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Etat;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Utilisateur;
import java.util.List;
import javax.persistence.TypedQuery;

public class EmployeDao {

    public EmployeDao() {
    }
    
    /* Chercher tous les employés de la DB */
    public List<Employe> chercherTous() {
        String s = "SELECT e FROM EMPLOYE e ORDER BY e.NOM ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Utilisateur.class);
        return query.getResultList();
    }
    
    /* Chercher les employés disponibles et du genre donné*/
    public List<Employe> chercherEmployesDisponiblesEtDeGenre(Genre genre) {
        String s = ""
                + "SELECT e "
                + "FROM Employe e "
                + "WHERE e NOT IN (SELECT c.employe FROM Consultation c WHERE c.etat = :unEtat1 OR c.etat = :unEtat2) "
                + "AND e.genre = :unGenre ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("unGenre", genre);
        query.setParameter("unEtat1", Etat.EnAttente);
        query.setParameter("unEtat2", Etat.EnCours);
        return query.getResultList();
    }
    
    /* Obtenir le nombre total de consultations qu'a fait un employé donné*/
    public Integer obtenirNombreConsultationsFinies(Employe employe) {
        String s = ""
                + "SELECT SUM(c.id) FROM Consultation c "
                + "WHERE c.etat = :unEtat AND c.employe = :unEmploye ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Integer.class);
        query.setParameter("unEmploye", employe);
        query.setParameter("unEtat", Etat.Termine);
        return (Integer)query.getSingleResult();
    }
}
