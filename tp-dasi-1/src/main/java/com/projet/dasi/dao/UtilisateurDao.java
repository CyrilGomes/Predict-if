package com.projet.dasi.dao;

import com.projet.dasi.model.Utilisateur;
import java.util.List;
import javax.persistence.TypedQuery;

public class UtilisateurDao {

    public UtilisateurDao() {
    }
    
    /* Créer un utilisateur dans la DB */
    public void creer(Utilisateur utilisateur) {
        JpaUtil.obtenirContextePersistance().persist(utilisateur);
    }
    /* Supprimer un utilisateur de la DB */
    public void supprimer(Utilisateur utilisateur) {
        JpaUtil.obtenirContextePersistance().remove(utilisateur);
    }
    /* Mettre à jour un utilisateur dans la DB */
    public Utilisateur mettreAJour(Utilisateur utilisateur) {
        return JpaUtil.obtenirContextePersistance().merge(utilisateur);
    }
    
    /* Chercher un utilisateur dans la DB par son ID*/
    public Utilisateur chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Utilisateur.class, id);
    }
    
    /* Authentifier un utilisateur à partir d'un mail et d'un mot de passe */
    public Utilisateur authentifier(String mail, String mdp) {
        String s = "SELECT u from Utilisateur u WHERE u.mail = :unMail AND u.motDePasse = :unMotDePasse";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Utilisateur.class);
        query.setParameter("unMotDePasse", mdp);
        query.setParameter("unMail", mail);    
        return (Utilisateur)query.getSingleResult();
    }
}
