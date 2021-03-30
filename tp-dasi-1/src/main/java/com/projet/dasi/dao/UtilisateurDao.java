/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.dao;

import com.projet.dasi.model.Utilisateur;
import java.util.List;
import javax.persistence.TypedQuery;

/**
 *
 * @author creep
 */
public class UtilisateurDao {

    public UtilisateurDao() {
    }
    
    
    public Utilisateur authentifier(String mail, String mdp) {
        String s = "SELECT c FROM Client c WHERE c.mail = :unMail AND c.motDePasse = :unMotDePasse";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Utilisateur.class);
        query.setParameter("unMotDePasse", mdp);
        query.setParameter("unMail", mail);    
        List<Utilisateur> lc = query.getResultList();
        Utilisateur c = null;
        if (lc.size() > 0) {
            c = lc.get(0);
        }
        return c;
    }
}
