package com.projet.dasi.service;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.dao.ConsultationDao;
import com.projet.dasi.dao.EmployeDao;
import com.projet.dasi.Message;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.MediumDao;
import com.projet.dasi.dao.UtilisateurDao;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.model.Spirite;
import com.projet.dasi.model.Utilisateur;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceApplication {

    public ServiceApplication() {
    }

    /* INSCRIRE UN CLIENT */
    public Client inscrireClient(Client c) {

        try {
            AstroAPI astroApi = new AstroAPI();
            ProfilAstral profil = astroApi.getProfil(c.getPrenom(), c.getDateNaissance());
            c.setProfilAstral(profil);

            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();

            UtilisateurDao utilisateurDao = new UtilisateurDao();
            utilisateurDao.creer(c);

            JpaUtil.validerTransaction();
            Message.envoyerMail("contact.predict.if", c.getMail(), "Bienvenu chez PREDICT'IF", "Bonjour " + c.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF. Rendezvous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos médiums");

        } catch (Exception ex) {
            JpaUtil.annulerTransaction();
            Message.envoyerMail("contact.predict.if", c.getMail(), "Echec de l’inscription chez PREDICT’IF", "Bonjour " + c.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué...\n"
                    + "Merci de recommencer ultérieurement.");
            ex.printStackTrace();
            c = null;
        } finally {
            JpaUtil.fermerContextePersistance();
        }
        return c;

    }

    /* CREER LES EMPLOYES */
    public void creerEmployes() {
        try {
            ArrayList<Employe> employes = new ArrayList<Employe>();
            employes.add(new Employe(Genre.Femme,
                    "Paola",
                    "Pritchard",
                    "paola.pritchard@hotmail.com",
                    "12345",
                    "0102030405",
                    "00000",
                    AstroAPI.DATE_FORMAT.parse("01/12/2000")
            ));
            employes.add(new Employe(Genre.Homme,
                    "Martin",
                    "Teibo",
                    "martin.teibo@wanadoo.fr",
                    "12345",
                    "0102030405",
                    "00000",
                    AstroAPI.DATE_FORMAT.parse("01/12/2000")
            ));

            UtilisateurDao utilisateurDao = new UtilisateurDao();
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            for (Employe e : employes) {
                utilisateurDao.creer(e);
            }
            JpaUtil.validerTransaction();
        } 
        catch (ParseException ex) {
            ex.printStackTrace();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }
    
    /* CREER LES MEDIUMS */
    public void creerMediums() {
        try {
            ArrayList<Medium> mediums = new ArrayList<Medium>();
            mediums.add(new Cartomancien(
                    "Sire Kartmalo", 
                    "Voulez-vous voir un tour de magie?", 
                    Genre.Homme
            ));
            mediums.add(new Astrologue(
                    "Monsieur N'TOMA", 
                    "Arrêtez de pleurer.", 
                    Genre.Homme,
                    "Institut Nouveau des Spirites Armateurs (INSA)",
                    "2012"
            ));
            mediums.add(new Spirite(
                    "Super Emma", 
                    "Bouboule de crystal OWO", 
                    Genre.Femme,
                    "Boule de Crystal, Marques de Thé"
            ));

            MediumDao mediumDao = new MediumDao();
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            for (Medium m : mediums) {
                mediumDao.creer(m);
            }
            JpaUtil.validerTransaction();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
    }

    /* AUTHENTIFIER UN UTILISATEUR */
    public Utilisateur authentifier(String mail, String mdp) {

        Utilisateur c;
        try {
            JpaUtil.creerContextePersistance();
            UtilisateurDao utilisateurDao = new UtilisateurDao();
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
    
    /* LISTER TOUS LES MEDIUMS */
    public List<Medium> obtenirMediums() {
        
        MediumDao mediumDao = new MediumDao();
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
        
        MediumDao mediumDao = new MediumDao();
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
        MediumDao mediumDao = new MediumDao();
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
    
    /* DEMANDER CONSULTATION */
    public Consultation demanderConsultation(Client client, Medium medium) {
        
        EmployeDao employeDao = new EmployeDao();
        ConsultationDao consultationDao = new ConsultationDao();
        
        List<Employe> employesDisponiblesDeBonGenre;
        Consultation consultation;
        
        try {
            
            JpaUtil.creerContextePersistance();
            
            employesDisponiblesDeBonGenre = employeDao.chercherEmployesDisponiblesEtDeGenre(medium.getGenre());
            Date tempsMin = new Date(100000);
            Employe employeChoisi = null;
            
            for (Employe e : employesDisponiblesDeBonGenre) {
                Date tempsDeTravail = (Date)employeDao.calculerTempsTravail(e).get(0);
                if (tempsDeTravail.before(tempsMin)) {
                    tempsMin = tempsDeTravail;
                    employeChoisi = e;
                }
            }
       
            consultation = new Consultation(employeChoisi, client, medium);
            
            JpaUtil.creerContextePersistance();
            JpaUtil.ouvrirTransaction();
            consultationDao.creer(consultation);
            JpaUtil.validerTransaction();
            
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            consultation = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
        
    }
}
