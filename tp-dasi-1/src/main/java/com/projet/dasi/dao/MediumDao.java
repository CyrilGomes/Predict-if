package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Medium;

public class MediumDao {
    
    public MediumDao() {}
    
    /* Créer un médium dans la DB */
    public void creer(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    
    /* Chercher un médium dans la DB par son ID*/
    public Medium chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    
    /* Chercher tous les médiums de la DB */
    public List<Medium> chercherTous() {
        String s = "SELECT m FROM Medium m ORDER BY m.denomination ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    
    /* Chercher les médiums d'un type donné*/
    public List<Medium> chercherParType(String type) {
        String s = "SELECT m FROM Medium m WHERE m.typeMedium = :unType";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        query.setParameter("unType", type);
        return query.getResultList();
    }
    
    /* Chercher le(s) médium(s) d'une dénomination donnée */
    public List<Medium> chercherParDenomination(String denomination) {
        String s = "SELECT m FROM Medium c WHERE m.denomination LIKE %:uneDenomination%";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        query.setParameter("uneDenomination", denomination);
        return query.getResultList();
    }

}
