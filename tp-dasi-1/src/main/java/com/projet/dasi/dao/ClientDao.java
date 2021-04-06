package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.Client;

public class ClientDao {
    
    public ClientDao() {}
    
    public List<Client> chercherTous() {
        String s = "SELECT c FROM Client c ORDER BY c.nom ASC";
        TypedQuery query = JpaUtil.obtenirContextePersistance().createQuery(s, Client.class);
        return query.getResultList();
    }
}
