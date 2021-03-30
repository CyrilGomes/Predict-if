package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Client;

public class ClientDao {
    
    public ClientDao() {}
    
    public void creer(Client employe) {
        JpaUtil.obtenirContextePersistance().persist(employe);
    }
    public void supprimer(Client employe) {
        JpaUtil.obtenirContextePersistance().remove(employe);
    }
    public Client modifier(Client employe) {
        return JpaUtil.obtenirContextePersistance().merge(employe);
    }
    public Client chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    public List<Client> chercherTous() {
        String s = "SELECT c FROM Client c ORDER BY c.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }
    public Client authentifier(String mail, String mdp) {
        String s = "SELECT c FROM Client c WHERE c.mail = :unMail AND c.motDePasse = :unMotDePasse";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        query.setParameter("unMotDePasse", mdp);
        query.setParameter("unMail", mail);    
        List<Client> lc = query.getResultList();
        Client c = null;
        if (lc.size() > 0) {
            c = lc.get(0);
        }
        return c;
    }

}
