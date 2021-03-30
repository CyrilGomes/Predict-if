package com.projet.dasi.service;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.dao.ClientDao;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.ProfilAstralDao;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.ProfilAstral;
import java.util.List;

public class ServiceClient {
    
    ClientDao clientDao;
    ProfilAstralDao profilAstralDao;
    
    public ServiceClient() {
        clientDao = new ClientDao();
        profilAstralDao = new ProfilAstralDao();
    }
    
    /* INSCRIRE UN CLIENT */
    public Client inscrire(Client c) {
        
        try {
            AstroAPI astroApi = new AstroAPI();
            ProfilAstral profil = astroApi.getProfil(c.getPrenom(), c.getDateNaissance());
            c.setProfilAstral(profil);
            
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            clientDao.creer(c);
            JpaUtil.validerTransaction();
        }
        catch (Exception ex) {
            JpaUtil.annulerTransaction();
            ex.printStackTrace();
            c = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;
        
    }
    
    /* RECHERCHER UN CLIENT (par son id) */
    public Client rechercher(Long id) {
        
        Client c;
        try {
            JpaUtil.creerContextePersistance();
            c = clientDao.chercherParId(id);
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
    
    /* LISTER TOUS LES CLIENTS */
    public List<Client> listerTous() {
        
        List<Client> lc;
        try {
            JpaUtil.creerContextePersistance();
            lc = clientDao.chercherTous();        
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
    
    /* AUTHENTIFIER UN CLIENT */
    public Client authentifier(String mail, String mdp) {
        
        Client c;
        try {
            JpaUtil.creerContextePersistance();
            c = clientDao.authentifier(mail, mdp);      
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
