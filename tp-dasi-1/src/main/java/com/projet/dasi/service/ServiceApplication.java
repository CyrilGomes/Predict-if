package com.projet.dasi.service;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.dao.ConsultationDao;
import com.projet.dasi.dao.EmployeDao;
import com.projet.dasi.Message;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.MediumDao;
import com.projet.dasi.dao.UtilisateurDao;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.model.Utilisateur;
import java.text.ParseException;
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
            JpaUtil.creerContextePersistance();

            Employe emp1 = new Employe(Genre.Femme,
                    "Paola",
                    "Pritchard",
                    "paola.pritchard@hotmail.com",
                    "12345",
                    "0102030405",
                    "00000",
                    AstroAPI.DATE_FORMAT.parse("01/12/2000")
            );

            Employe emp2 = new Employe(Genre.Homme,
                    "Martin",
                    "Teibo",
                    "martin.teibo@wanadoo.fr",
                    "12345",
                    "0102030405",
                    "00000",
                    AstroAPI.DATE_FORMAT.parse("01/12/2000")
            );

            JpaUtil.ouvrirTransaction();
            UtilisateurDao utilisateurDao = new UtilisateurDao();
            utilisateurDao.creer(emp1);
            utilisateurDao.creer(emp2);
            JpaUtil.validerTransaction();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (Exception e) {
            JpaUtil.annulerTransaction();
            e.printStackTrace();
        } finally {
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
        } catch (Exception ex) {
            ex.printStackTrace();
            c = null;
        } finally {
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
            consultation = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return consultation;
        
    }
}
