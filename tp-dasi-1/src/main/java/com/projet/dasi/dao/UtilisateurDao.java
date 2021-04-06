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
    
    public void creer(Utilisateur utilisateur) {
        JpaUtil.obtenirContextePersistance().persist(utilisateur);
    }
    
    public void supprimer(Utilisateur utilisateur) {
        JpaUtil.obtenirContextePersistance().remove(utilisateur);
    }
    public Utilisateur modifier(Utilisateur utilisateur) {
        return JpaUtil.obtenirContextePersistance().merge(utilisateur);
    }
    public Utilisateur chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Utilisateur.class, id);
    }
    
    public Utilisateur authentifier(String mail, String mdp) {
        String s = "SELECT u from Utilisateur u WHERE u.mail = :unMail AND u.motDePasse = :unMotDePasse";
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
