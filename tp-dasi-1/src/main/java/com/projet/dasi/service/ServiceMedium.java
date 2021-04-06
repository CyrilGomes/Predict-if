package com.projet.dasi.service;

import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.MediumDao;
import com.projet.dasi.model.Medium;
import java.util.List;

public class ServiceMedium {
    
    MediumDao mediumDao;
    
    public ServiceMedium() {
        mediumDao = new MediumDao();
    }
    
    /* LISTER TOUS LES MEDIUMS */
    public List<Medium> obtenirMediums() {
        
        List<Medium> lc;
        try {
            JpaUtil.creerContextePersistance();
            lc = mediumDao.chercherTous();        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            lc = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return lc;  
    }
    
    /* LISTER TOUS LES MEDIUMS SELON LEUR TYPE */
    public List<Medium> obtenirMediumsSelonType(String type) {
        
        List<Medium> lc;
        try {
            JpaUtil.creerContextePersistance();
            lc = mediumDao.chercherParType(type);        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            lc = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return lc;  
    }
    
    /* LISTER TOUS LES MEDIUMS SELON LEUR DÉNOMINATION */
    public List<Medium> obtenirMediumsSelonDenomination(String denomination) {
        
        List<Medium> lc;
        try {
            JpaUtil.creerContextePersistance();
            lc = mediumDao.chercherParDenomination(denomination);        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            lc = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return lc;  
    }

}
