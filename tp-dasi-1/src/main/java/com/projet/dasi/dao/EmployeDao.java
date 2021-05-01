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
        String s = "SELECT e FROM Employe e ORDER BY e.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Utilisateur.class);
        return query.getResultList();
    }
    
    /* Chercher les employés disponibles et du genre donné*/
    public List<Employe> chercherEmployesDisponiblesEtDeGenre(Genre genre) {
        String s = ""
                + "SELECT e "
                + "FROM Employe e "
                + "WHERE e.genre = :unGenre AND NOT EXISTS ( "
                + "     SELECT c.employe FROM Consultation c "
                + "     WHERE c.employe = e AND c.etat IN (:unEtat1, :unEtat2, :unEtat3)"
                + ") ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Employe.class);
        query.setParameter("unGenre", genre);
        query.setParameter("unEtat1", Etat.EnAttenteEmploye);
        query.setParameter("unEtat2", Etat.EnAttenteClient);
        query.setParameter("unEtat3", Etat.EnCours);
        return query.getResultList();
    }
    
    /* Obtenir le nombre total de consultations qu'a fait un employé donné*/
    public Integer obtenirNombreConsultationsFinies(Employe employe) {
        String s = ""
                + "SELECT COALESCE(SUM(1), 0) FROM Consultation c "
                + "WHERE c.etat = :unEtat AND c.employe = :unEmploye ";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Integer.class);
        query.setParameter("unEmploye", employe);
        query.setParameter("unEtat", Etat.Termine);
        return (Integer)query.getSingleResult();
    }
}
