package com.projet.dasi.dao;

import java.util.List;
import javax.persistence.TypedQuery;
import com.projet.dasi.model.ProfilAstral;

public class ProfilAstralDao {
    
    public ProfilAstralDao() {}
    
    public void creer(ProfilAstral profilAstral) {
        JpaUtil.obtenirContextePersistance().persist(profilAstral);
    }
    public void supprimer(ProfilAstral profilAstral) {
        JpaUtil.obtenirContextePersistance().remove(profilAstral);
    }

}
