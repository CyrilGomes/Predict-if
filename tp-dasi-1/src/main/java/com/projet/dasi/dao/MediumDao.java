package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Medium;

public class MediumDao {
    
    public MediumDao() {}
    
    public void creer(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    public Medium chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    public List<Medium> chercherParType(String type) {
        String s = "SELECT m FROM Medium m WHERE m.TYPE_MEDIUM = :type";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    public List<Medium> chercherParDenomination(String denomination) {
        String s = "SELECT m FROM Medium c WHERE m.DENOMATION = :type";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    public List<Medium> chercherTous() {
        String s = "SELECT m FROM Medium m ORDER BY m.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }

}
