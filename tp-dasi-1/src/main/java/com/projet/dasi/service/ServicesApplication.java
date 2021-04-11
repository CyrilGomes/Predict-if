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
import java.util.List;

public class ServicesApplication {

    public ServicesApplication() {
    }

    /* Inscrire un client */
    public Client inscrireClient(Client client) {
        
        // Créer les DAOs et le contexte de persistance
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        JpaUtil.creerContextePersistance();
        
        try {
            // Générer un Profil Astral et l'attribuer au client à inscrire
            AstroAPI astroApi = new AstroAPI();
            ProfilAstral profil = astroApi.getProfil(client.getPrenom(), client.getDateNaissance());
            client.setProfilAstral(profil);

            // Persister le client
            JpaUtil.ouvrirTransaction();
            utilisateurDao.creer(client);
            JpaUtil.validerTransaction();
            
            // Le notifier de son inscription
            Message.envoyerMail("contact.predict.if", client.getMail(), "Bienvenu chez PREDICT'IF", "Bonjour " + client.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF. Rendezvous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos médiums");

        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            Message.envoyerMail("contact.predict.if", client.getMail(), "Echec de l’inscription chez PREDICT’IF", "Bonjour " + client.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué... \nMerci de recommencer ultérieurement.");
            client = null;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;

    }

    /* DEBUG: CREER DES CLIENTS*/
    public void creerClients() {
        
         // Créer les DAOs et le contexte de persistance
        UtilisateurDao utilisateurDao = new UtilisateurDao();
            
        try {
            // Ajouter des employés à une liste (en dur)
            ArrayList<Client> clients = new ArrayList<Client>();
            clients.add(new Client(
                    "Bertrand",
                    "Usclat",
                    "bertrand.usclat@yahoo.ro",
                    "superCanardXXL",
                    "0685123975",
                    "69200",
                    AstroAPI.DATE_FORMAT.parse("25/06/1965")
            ));
            clients.add(new Client(
                    "Jeannine",
                    "Odoux",
                    "jeannette_xxx@trashmail.fr",
                    "secretPassword",
                    "0782953267",
                    "32100",
                    AstroAPI.DATE_FORMAT.parse("20/08/1985")
            ));

            // Inscrire les clients 
            for (Client c : clients) {
                inscrireClient(c);
            }
        } 
        catch (ParseException ex) {
            ex.printStackTrace();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        } 
        
    }
    
    /* CREER LES EMPLOYES */
    public void creerEmployes() {

        // Créer les DAOs et le contexte de persistance
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        JpaUtil.creerContextePersistance();
            
        try {
            // Ajouter des employés à une liste (en dur)
            ArrayList<Employe> employes = new ArrayList<Employe>();
            employes.add(new Employe(Genre.Homme,
                    "Martin",
                    "Teibo",
                    "martin.teibo@wanadoo.fr",
                    "12345",
                    "0102030405",
                    "00000",
                    AstroAPI.DATE_FORMAT.parse("01/12/2000")
            ));
            employes.add(new Employe(Genre.Homme,
                    "Jammy",
                    "Bombaz",
                    "awesome.man@bing.uk",
                    "12345",
                    "0102030405",
                    "00000",
                    AstroAPI.DATE_FORMAT.parse("01/12/2000")
            ));
            employes.add(new Employe(Genre.Femme,
                    "Paola",
                    "Pritchard",
                    "paola.pritchard@hotmail.com",
                    "12345",
                    "0102030405",
                    "00000",
                    AstroAPI.DATE_FORMAT.parse("01/12/2000")
            ));

            // Persister les employés
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
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        try {
            // Ajouter des médiums à une liste (en dur)
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

            // Persister les médiums
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

        // Créer les DAOs et le contexte de persistance
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        JpaUtil.creerContextePersistance();
        
        Utilisateur utilisateur;
        try {
            // Authentifier un utilisateur avec le mail et le mot de passe donnés
            utilisateur = utilisateurDao.authentifier(mail, mdp);
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            utilisateur = null;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return utilisateur;
        
    }
    
    /* LISTER TOUS LES MEDIUMS */
    public List<Medium> obtenirMediums() {
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        List<Medium> listeMediums;
        try {
            // Récupérer tous les médiums (triés par ordre croissant de dénomination)
            listeMediums = mediumDao.chercherTous();        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            listeMediums = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeMediums;  
        
    }
    
    /* LISTER TOUS LES MEDIUMS SELON LEUR TYPE */
    public List<Medium> obtenirMediumsSelonType(String type) {
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        List<Medium> listeMediums;
        try {
            // Récupérer tous les médiums du type donné
            listeMediums = mediumDao.chercherParType(type);        
        }
        catch (Exception ex) {
            ex.printStackTrace();
            listeMediums = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeMediums;
        
    }
    
    /* LISTER TOUS LES MEDIUMS SELON LEUR DÉNOMINATION */
    public List<Medium> obtenirMediumsSelonDenomination(String denomination) {
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        List<Medium> listeMediums;
        try {
            // Récupérer tous les médiums d'une dénomination similaire à celle donnée
            listeMediums = mediumDao.chercherParDenomination(denomination);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            listeMediums = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeMediums;
        
    }
    
    /* DEMANDER CONSULTATION */
    public Consultation demanderConsultation(Client client, Medium medium) {
        
        // Créer les DAOs et le contexte de persistance
        EmployeDao employeDao = new EmployeDao();
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        Consultation consultation;
        try {
            // Obtenir les employés qui sont disponibles et qui sont du bon genre
            List<Employe> employesDisponiblesDeBonGenre = employeDao.chercherEmployesDisponiblesEtDeGenre(medium.getGenre());
            // S'il y en a, 
            if (employesDisponiblesDeBonGenre.size() > 0) {
                // Chercher celui qui a fait le moins de consultations
                Integer nbConsultationsMin = Integer.MAX_VALUE;
                Employe employe = null;
                for (Employe e : employesDisponiblesDeBonGenre) {
                    Integer nbConsultations = employeDao.obtenirNombreConsultationsFinies(e);
                    if (nbConsultations != null && nbConsultations < nbConsultationsMin) {
                        nbConsultationsMin = nbConsultations;
                        employe = e;
                    }
                }
                // Si personne n'a fait de consultations, prendre le premier
                if (nbConsultationsMin == Integer.MAX_VALUE) {
                    employe = employesDisponiblesDeBonGenre.get(0);
                }
                // Créer une consultation avec l'employé choisi
                consultation = new Consultation(employe, client, medium);
                // La persister
                JpaUtil.ouvrirTransaction();
                consultationDao.creer(consultation);
                JpaUtil.validerTransaction();
            }
            // Si aucun employé disponible et de bon genre, consultation impossible
            else {
                consultation = null;
            }
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
