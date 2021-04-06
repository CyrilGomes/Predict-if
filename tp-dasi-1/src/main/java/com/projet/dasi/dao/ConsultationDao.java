package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Medium;

public class ConsultationDao {
    
    public ConsultationDao() {}
    
    public void creer(Medium medium) {
        JpaUtil.obtenirContextePersistance().persist(medium);
    }
    public Medium chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Medium.class, id);
    }
    public List<Medium> chercherParType(String type) {
        String s = "SELECT c FROM Medium c WHERE c.TYPE_MEDIUM = :type";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    public List<Medium> chercherParDenomination(String denomination) {
        String s = "SELECT c FROM Medium c WHERE c.DENOMATION = :type";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }
    public List<Medium> chercherTous() {
        String s = "SELECT c FROM Medium c ORDER BY c.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Medium.class);
        return query.getResultList();
    }

}
