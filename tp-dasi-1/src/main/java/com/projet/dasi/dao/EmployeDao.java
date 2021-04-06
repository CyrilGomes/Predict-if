/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.dao;

import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Utilisateur;
import java.util.Date;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author creep
 */
public class EmployeDao {

    public EmployeDao() {
    }
    
    public List<Employe> chercherTous() {
        String s = "SELECT emp FROM Employe emp ORDER BY emp.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Utilisateur.class);
        return query.getResultList();
    }
    
    public List<Employe> chercherEmployesDisponiblesEtDeGenre(Genre genre) {
        String s = ""
                + "SELECT e "
                + "FROM Employe e "
                + "WHERE e NOT IN ("
                + "   SELECT emp "
                + "   FROM Employe emp JOIN Consultation consul ON emp.id = consul.employe  "
                + "   WHERE consul.etat = 'EnAttente'"
                + ") AND e.genre = :unGenre";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("unGenre", genre.toString());
        return query.getResultList();
    }
    
    public List<Date> calculerTempsTravail(Employe employe) {
        String s = ""
                + "SELECT SUM(c.dateFin - c.dateDebut) "
                + "FROM Consultation c "
                + "WHERE c.etat = 'Termine' AND employe = :unEmploye";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Date.class);
        query.setParameter("unEmploye", employe.getId());
        return query.getResultList();
    }
}
