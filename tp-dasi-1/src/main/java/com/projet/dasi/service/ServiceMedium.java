package com.projet.dasi.service;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.dao.MediumDao;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.MediumDao;
import com.projet.dasi.dao.ProfilAstralDao;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import java.util.List;

public class ServiceMedium {
    
    MediumDao mediumDao;
    
    public ServiceMedium() {
        mediumDao = new MediumDao();
    }
    
    /* LISTER TOUS LES MEDIUMS */
    public List<Medium> obtenirMediums() {
        
        List<Medium> lm;
        try {
            JpaUtil.creerContextePersistance();
            lm = mediumDao.chercherTous();        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            lm = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return lm;  
    }
    
    /* LISTER TOUS LES MEDIUMS SELON LEUR TYPE */
    public List<Medium> obtenirMediumsSelonType(String type) {
        
        List<Medium> lm;
        try {
            JpaUtil.creerContextePersistance();
            lm = mediumDao.chercherParType(type);        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            lm = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return lm;  
    }
    
    /* LISTER TOUS LES MEDIUMS SELON LEUR DÉNOMINATION */
    public List<Medium> obtenirMediumsSelonDenomination(String denomination) {
        
        List<Medium> lm;
        try {
            JpaUtil.creerContextePersistance();
            lm = mediumDao.chercherParDenomination(denomination);        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            lm = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return lm;  
    }

}
