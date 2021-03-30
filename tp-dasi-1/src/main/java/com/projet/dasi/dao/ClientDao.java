package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Client;

public class ClientDao {
    
    public ClientDao() {}
    
    public void creer(Client client) {
        JpaUtil.obtenirContextePersistance().persist(client);
    }
    public void supprimer(Client client) {
        JpaUtil.obtenirContextePersistance().remove(client);
    }
    public Client modifier(Client client) {
        return JpaUtil.obtenirContextePersistance().merge(client);
    }
    public Client chercherParId(Long id) {
        return JpaUtil.obtenirContextePersistance().find(Client.class, id);
    }
    public List<Client> chercherTous() {
        String s = "SELECT c FROM Client c ORDER BY c.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }
}
