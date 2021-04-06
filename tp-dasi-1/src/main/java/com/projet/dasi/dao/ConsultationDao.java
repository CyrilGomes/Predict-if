package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Consultation;

public class ConsultationDao {
    
    public ConsultationDao() {}
    
    /* Creer une consultation dans la DB */
    public void creer(Consultation consultation) {
        JpaUtil.obtenirContextePersistance().persist(consultation);
    }
    
    /* Chercher une consultation dans la DB par son ID */
    public Consultation chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Consultation.class, id);
    }
    
    /* Chercher toutes les consultations de la DB */
    public List<Consultation> chercherTous() {
        String s = "SELECT c FROM Consultation c ORDER BY c.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Consultation.class);
        return query.getResultList();
    }

}
