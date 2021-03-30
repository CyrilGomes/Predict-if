/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.service;

import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.UtilisateurDao;
import com.projet.dasi.model.Utilisateur;

/**
 *
 * @author creep
 */
public class ServiceUtilisateur {
    UtilisateurDao utilisateurDao;
    
    public ServiceUtilisateur() {
        utilisateurDao = new UtilisateurDao();
    }
        
    
    /* AUTHENTIFIER UN CLIENT */
    public Utilisateur authentifier(String mail, String mdp) {
        
        Utilisateur c;
        try {
            JpaUtil.creerContextePersistance();
            c = utilisateurDao.authentifier(mail, mdp);      
        }
        catch (Exception ex) {
            ex.printStackTrace(); 
            c = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
    }
}
