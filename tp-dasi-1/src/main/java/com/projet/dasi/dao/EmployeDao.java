/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.dao;

import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.model.Utilisateur;
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
}
