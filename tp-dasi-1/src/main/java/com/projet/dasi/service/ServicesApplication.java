package com.projet.dasi.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projet.dasi.AstroAPI;
import com.projet.dasi.dao.ConsultationDao;
import com.projet.dasi.dao.EmployeDao;
import com.projet.dasi.presentation.Message;
import com.projet.dasi.dao.ClientDao;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.MediumDao;
import com.projet.dasi.dao.UtilisateurDao;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Etat;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.model.Spirite;
import com.projet.dasi.model.Utilisateur;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServicesApplication {

    public ServicesApplication() {
    }
    
    /* Créer les employes dans la BD */
    public void initialiserEmployes() {

        // Créer les DAOs et le contexte de persistance
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        JpaUtil.creerContextePersistance();
        
        try {
            // Ajouter des employés à une liste (en dur)
            ArrayList<Employe> employes = new ArrayList<Employe>();
            employes.add(new Employe(Genre.Femme, "Camille", "Martin", "camcam.marmar@yahoo.fr", ")arkIsland48", "0785552975", "84230", AstroAPI.DATE_FORMAT.parse("18/11/1990")));
            employes.add(new Employe(Genre.Homme, "Martin", "Teibo", "martin.teibo@wanadoo.fr", "blu3Amber73", "0655583971", "69230", AstroAPI.DATE_FORMAT.parse("13/08/2000")));
            employes.add(new Employe(Genre.Homme, "Jammy", "Bombaz", "jammy-man@bing.uk", "o)dSoap89", "0765552655", "56812", AstroAPI.DATE_FORMAT.parse("19/10/1982")));
            employes.add(new Employe(Genre.Femme, "Paola", "Pritchard", "paola.pritchard@hotmail.com", "=reshBoot79", "0700555359", "13657", AstroAPI.DATE_FORMAT.parse("01/12/2000")));
            employes.add(new Employe(Genre.Femme, "Victoire-Michèle", "Lejeune", "v.michele.lejeune@gmail.fr", "goldNor+h37", "0928423954", "97048", AstroAPI.DATE_FORMAT.parse("15/06/1976")));
            employes.add(new Employe(Genre.Femme, "Monique", "Gonzalez", "mogonzalez42@yahoo.com", "oliveNoi$e90", "0178385422", "42681", AstroAPI.DATE_FORMAT.parse("28/05/1990")));
            employes.add(new Employe(Genre.Homme, "Yves", "Vincent", "yves.vinvin@wanadoo.fr", "oliveNoi$e90", "0770733813", "93112", AstroAPI.DATE_FORMAT.parse("09/07/1993")));
            employes.add(new Employe(Genre.Homme, "Richard-Patrick", "Denis", "patriri.denis@gmail.fr", "whit3Robin72", "0913135610", "67318", AstroAPI.DATE_FORMAT.parse("27/05/1951")));
            employes.add(new Employe(Genre.Homme, "Louis", "Guyot-Perrin", "louis-gprrin@hotmail.fr", "dar<Mass11", "0667981441", "51414", AstroAPI.DATE_FORMAT.parse("04/10/1992")));
            employes.add(new Employe(Genre.Femme, "Suzanne", "Jacob", "zazanne.jacob@gmail.com", "lushMus+ang36", "0651663718", "42681", AstroAPI.DATE_FORMAT.parse("14/07/1983")));   
            employes.add(new Employe(Genre.Homme, "Dimitri", "Rolland", "dimirolan84@yahoo.com", "fh7rdm3g", "0761596478", "35370", AstroAPI.DATE_FORMAT.parse("03/05/1984")));
            employes.add(new Employe(Genre.Homme, "Florence", "Pottier", "flora.potter@wanadoo.fr", "ujvz6wpw", "0673861235", "69490", AstroAPI.DATE_FORMAT.parse("26/07/1987")));
            employes.add(new Employe(Genre.Femme, "Marie", "Doucet", "douce-maria@gmail.fr", "a5m66iqt", "0614336007", "02590", AstroAPI.DATE_FORMAT.parse("31/01/1987")));
            employes.add(new Employe(Genre.Femme, "Jennifer", "Gouatloude", "filo.jennie@hotmail.fr", "inleqz42", "0600923533", "22300", AstroAPI.DATE_FORMAT.parse("15/05/1964")));
            employes.add(new Employe(Genre.Homme, "Paul", "Diaz", "paulotdiaz79@gmail.com", "7a4dlcad", "0907265287", "52170", AstroAPI.DATE_FORMAT.parse("04/01/1979")));
            employes.add(new Employe(Genre.Femme, "Jacinthe", "Auger", "jacinthedu29@hotmail.fr", "9nyiyqyd", "0638284835", "29260", AstroAPI.DATE_FORMAT.parse("06/11/1978")));
            
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
    
    /* Créer les mediums dans la BD */
    public void initialiserMediums() {
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        try {
            // Ajouter des médiums à une liste (en dur)
            ArrayList<Medium> mediums = new ArrayList<Medium>();
            mediums.add(new Spirite(Genre.Femme, "Gwenaëlle", "Spécialiste des grandes conversations au-delà de TOUTES les frontières.", "Boule de Crystal"));
            mediums.add(new Spirite(Genre.Homme, "Professeur Tran", "Votre avenir est devant vous : regardons-le ensemble !", "Marc de café, boule de cristal, oreilles de lapin"));
            mediums.add(new Spirite(Genre.Femme, "Super Emma", "Regardez ma bouboule OwO.", "Boule de Crystal, Marques de Thé"));
            mediums.add(new Spirite(Genre.Homme, "Barkaba Junior", "Explorons votre inconscient pour révéler les grands secrets de votre avenir !", "Marques de thé, pattes d'igouanes"));
            mediums.add(new Cartomancien(Genre.Femme, "Mme Irma", "Comprenez votre entourage grâce à mes cartes ! Résultats rapides..."));
            mediums.add(new Cartomancien(Genre.Femme, "Endora", "Mes cartes répondront à toutes vos questions personnelles."));
            mediums.add(new Cartomancien(Genre.Homme, "Sire Kartmalo", "Voulez-vous voir un tour de magie ?"));
            mediums.add(new Cartomancien(Genre.Homme, "Le Grand Maître", "Je détiens le contrôle absolu sur les cartes."));
            mediums.add(new Astrologue(Genre.Femme, "Serena", "Basée à Champigny-sur-Marne, Serena vous révèlera votre avenir pour éclairer votre passé.", "École Normale Supérieure d’Astrologie (ENS-Astro)", "2006"));
            mediums.add(new Astrologue(Genre.Homme, "Mr M", "Avenir, avenir, que nous réserves-tu ? N'attendez plus, demandez à me consulter!", "nstitut des Nouveaux Savoirs Astrologiques (ENE)", "2010"));
            mediums.add(new Astrologue(Genre.Homme, "Monsieur N'TOMA", "Arrêtez de pleurer. Je vais résoudre vos problèmes.", "École Nouvelle des Étoiles (ENE)", "2015"));
            mediums.add(new Astrologue(Genre.Femme, "Lady Chalala", "Trouve des solutions à tous vos problèmes d'impuissance, de finances, de mariage, et de dos", "Formation Intensive aux Métiers Interstellaires (FIMI)", "2012"));
            
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
    
    /* DEBUG: Générer des clients dans la BD */
    public void genererClients() {
            
        try {
            // Ajouter des employés à une liste (en dur)
            ArrayList<Client> clients = new ArrayList<Client>();
            clients.add(new Client("Bertrand", "Usclat", "bertrand.usclat@yahoo.ro", "superCanardXXL", "0685123975", "69200", AstroAPI.DATE_FORMAT.parse("25/06/1965")));
            clients.add(new Client("Jeannine", "Odoux", "jeannette_xxx@trashmail.fr", "secretPassword", "0782953267", "32100", AstroAPI.DATE_FORMAT.parse("20/08/1985")));

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
    
    /* DEBUG: Générer des consultations finies dans la BD */
    public void genererConsultations() {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        EmployeDao employeDao = new EmployeDao();
        MediumDao mediumDao = new MediumDao();
        ClientDao clientDao = new ClientDao();
        JpaUtil.creerContextePersistance();
        
        // Obtenir tous les cients, employes, mediums
        List<Employe> employes = employeDao.chercherTous();
        List<Medium> mediums = mediumDao.chercherTous();
        List<Client> clients = clientDao.chercherTous();
        
        // Créer PLEIN de consultations
        try {
            JpaUtil.ouvrirTransaction();
            for (Employe e : employes) {
                if(Math.random() < 0.4) continue;
                for (Medium m : mediums) {
                    if(Math.random() < 0.4) continue;
                    for (Client c : clients) {
                        if(Math.random() < 0.4) continue;
                        Consultation consultation = new Consultation(e, c, m);
                        consultation.setEtat(Etat.Termine);
                        consultation.setDateDebut(new Date());
                        consultation.setDateFin(new Date((new Date()).getTime() + (int)(Math.random()*20)*60*1000));
                        consultationDao.creer(consultation);
                    }
                }
            }
            JpaUtil.validerTransaction();
        }
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
        }
        
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
            Message.envoyerMail(
                "contact.predict.if", client.getMail(), "Bienvenu chez PREDICT'IF", 
                "Bonjour " + client.getPrenom() + ", nous vous confirmons votre inscription au service PREDICT’IF. Rendez-vous vite sur notre site pour consulter votre profil astrologique et profiter des dons incroyables de nos médiums"
            );

        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            Message.envoyerMail(
                "contact.predict.if", client.getMail(), "Echec de l’inscription chez PREDICT’IF", 
                "Bonjour " + client.getPrenom() + ", votre inscription au service PREDICT’IF a malencontreusement échoué... \nMerci de recommencer ultérieurement."
            );
            client = null;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return client;

    }

    /* Authentifier un utilisateur */
    public Utilisateur authentifier(String mail, String mdp) {

        // Créer les DAOs et le contexte de persistance
        UtilisateurDao utilisateurDao = new UtilisateurDao();
        JpaUtil.creerContextePersistance();
        
        // Authentifier un utilisateur avec le mail et le mot de passe donnés
        Utilisateur utilisateur = utilisateurDao.authentifier(mail, mdp);

        JpaUtil.fermerContextePersistance();
        
        return utilisateur;
        
    }
    
    /* Lister tous les mediums */
    public List<Medium> obtenirMediums() {
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer tous les médiums (triés par ordre croissant de dénomination)
        List<Medium> listeMediums = mediumDao.chercherTous();        

        JpaUtil.fermerContextePersistance();
        
        return listeMediums;  
        
    }
    
    /* Lister tous les mediums selon leur type */
    public List<Medium> obtenirMediumsSelonType(String type) {
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer tous les médiums du type donné
        List<Medium> listeMediums = mediumDao.chercherParType(type);        

        JpaUtil.fermerContextePersistance();
        
        return listeMediums;
        
    }
    
    /* Lister tous les mediums selon leur dénomination */
    public List<Medium> obtenirMediumsSelonDenomination(String denomination) {
        
        // Créer les DAOs et le contexte de persistance
        MediumDao mediumDao = new MediumDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer tous les médiums d'une dénomination similaire à celle donnée
        List<Medium> listeMediums = mediumDao.chercherParDenomination(denomination);

        JpaUtil.fermerContextePersistance();
        
        return listeMediums;
        
    }
    
    /* Demander consultation */
    public Consultation demanderConsultation(Client client, Medium medium) {
        
        // Créer les DAOs et le contexte de persistance
        EmployeDao employeDao = new EmployeDao();
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        Consultation consultation;
        
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
            try {
                // La persister
                JpaUtil.ouvrirTransaction();
                consultationDao.creer(consultation);
                JpaUtil.validerTransaction();
            }
            catch (Exception ex) {
                ex.printStackTrace();
                JpaUtil.annulerTransaction();
                consultation = null;
            }
        }
        // Si aucun employé disponible et de bon genre, consultation impossible
        else {
            consultation = null;
        }

        JpaUtil.fermerContextePersistance();
        
        // Notifier l'employé de la demande de consultation
        if (consultation != null) {
            Message.envoyerNotification(
                consultation.getEmploye().getTelephone(),
                "Bonjour " + consultation.getEmploye().getPrenom() + ". Consultation requise pour " + consultation.getClient().getPrenom() + " " + consultation.getClient().getNom() + ". Médium à incarner : " + consultation.getMedium().getDenomination()
            );
        }

        return consultation;
        
    }
    
    /* Obtenir la consultation assignée à employé */
    public Consultation obtenirConsultationAttribueeAEmploye(Employe employe) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer tous les médiums d'une dénomination similaire à celle donnée
        Consultation consultation = consultationDao.chercherEnCoursParEmploye(employe);

        JpaUtil.fermerContextePersistance();
        
        return consultation;
        
    }
    
    /* Signaler le début d'une consultation (employé est prêt et attend appel client) */
    public boolean signalerDebutConsultation(Consultation consultation) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Modifier la consultation
        boolean reussite = true;
        consultation.setEtat(Etat.EnAttenteClient);
        consultation.setDateDebut(new Date());
        
        // Mettre à jour la modification
        try {
            JpaUtil.ouvrirTransaction();
            consultation = consultationDao.mettreAJour(consultation);
            JpaUtil.validerTransaction();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            reussite = false;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        
        // Notifier le client qu'il peut appeler le médium
        if (reussite) {
            SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy à HH'h'mm");  
            Message.envoyerNotification(
                consultation.getClient().getTelephone(),
                "Bonjour " + consultation.getClient().getPrenom() + ". J’ai bien reçu votre demande de consultation du " + formatDate.format(consultation.getDateDebut()) + ". Vous pouvez dès à présent me contacter au " + consultation.getEmploye().getTelephone() + ". A tout de suite ! Médiumiquement vôtre, " + consultation.getMedium().getDenomination() 
            );
        }
        
        return reussite;
        
    }

    /* Débuter ou terminer une consultation (selon son état) */
    public boolean demarrerOuTerminerConsultation(Consultation consultation) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Modifier la consultation
        boolean reussite = true;
        switch (consultation.getEtat()) {
            case EnAttenteClient:
                consultation.setEtat(Etat.EnCours); // Démarrer
                consultation.setDateDebut(new Date());
                break;
            case EnCours:
                consultation.setEtat(Etat.Termine); // Terminer
                consultation.setDateFin(new Date());
                break;
            default:
                reussite = false;
                break;
        }
        
        // Mettre à jour la modification
        try {
            JpaUtil.ouvrirTransaction();
            consultation = consultationDao.mettreAJour(consultation);
            JpaUtil.validerTransaction();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            reussite = false;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return reussite;
        
    }
    
    /* Annuler une consultation */
    public boolean annulerConsultation(Consultation consultation) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Modifier la consultation
        boolean reussite = true;
        consultation.setEtat(Etat.Annule);
        
        // Mettre à jour la modification
        try {
            JpaUtil.ouvrirTransaction();
            consultation = consultationDao.mettreAJour(consultation);
            JpaUtil.validerTransaction();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            reussite = false;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return reussite;
        
    }
    
    /* Sauvegarder le commentaire d'une consultation */
    public boolean sauvegarderCommentaireConsultation(Consultation consultation, String commentaire) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Modifier la consultation
        boolean reussite = true;
        consultation.setCommentaire(commentaire);
        
        // Mettre à jour la modification
        try {
            JpaUtil.ouvrirTransaction();
            consultation = consultationDao.mettreAJour(consultation);
            JpaUtil.validerTransaction();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            reussite = false;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return reussite;
        
    }
    
    /* Obtenir l'historique de consultations d'un client */
    public List<Consultation> obtenirHistoriqueConsultationsClient(Client client)  {
     
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer tous les médiums d'une dénomination similaire à celle donnée
        List<Consultation> listeConsultations = consultationDao.chercherTermineParClient(client);
       
        JpaUtil.fermerContextePersistance();
            
        return listeConsultations;
        
    }
    
    /* Obtenir l'historique de consultations d'un client selon un médium donné */
    public List<Consultation> obtenirHistoriqueConsultationsClientSelonMedium(Client client, Medium medium)  {
     
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer tous les médiums d'une dénomination similaire à celle donnée
        List<Consultation> listeConsultations = consultationDao.chercherTermineParClientEtMedium(client, medium);
        
        JpaUtil.fermerContextePersistance();
        
        return listeConsultations;
        
    }
 
    /* Sauvegarder la modification d'un profil de Client */
    public boolean modifierProfil(Client client, String nom, String prenom, String dateNaissance, String codePostal, String telephone, String email, String motDePasse) {
        
        // Créer les DAOs et le contexte de persistance
        ClientDao clientDao = new ClientDao();
        JpaUtil.creerContextePersistance();
        
        // Modifier la consultation
        boolean reussite = true;
        client.setNom(nom);
        client.setPrenom(prenom);
        client.setCodePostal(codePostal);
        client.setTelephone(telephone);
        client.setMail(email);
        client.setMotDePasse(motDePasse);
        try {
            client.setDateNaissance(AstroAPI.DATE_FORMAT.parse(dateNaissance));
        }
        catch (ParseException ex) {
            ex.printStackTrace();
            reussite = false;
        }
        
        // Mettre à jour la modification
        try {
            JpaUtil.ouvrirTransaction();
            clientDao.mettreAJour(client);
            JpaUtil.validerTransaction();
        } 
        catch (Exception ex) {
            ex.printStackTrace();
            JpaUtil.annulerTransaction();
            reussite = false;
        } 
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return reussite;
        
    }
    
    /* Générer des prédictions pour un client selon des scores donnés */
    public List<String> genererPredictionsClient(Client client, int amour, int sante, int travail) {
        
        AstroAPI astroApi = new AstroAPI();
        List<String> predictions;
        try {
            predictions = astroApi.getPredictions(client.getProfilAstral(), amour, sante, travail);
        } 
        catch (IOException ex) {
            ex.printStackTrace();
            predictions = null;
        }
        return predictions;
        
    }
    
    public JsonObject genererStatistiquesMediumsPopulaires(boolean top5) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer tous les médiums d'une dénomination similaire à celle donnée
        List<Object[]> listeStatistiques; 
        if (top5) {
            listeStatistiques = consultationDao.obtenirTop5NombreConsultationsParMedium();
        }
        else {
            listeStatistiques = consultationDao.obtenirNombreConsultationsParMedium();
        }
        
        JpaUtil.fermerContextePersistance();
        
        // Construire le JSON Object
        JsonObject statistiques = new JsonObject();
        if (listeStatistiques != null) {
            JsonArray jsonArray = new JsonArray();
            for (Object[] coupleStats : listeStatistiques) {
                JsonObject jsonInfosMedium = new JsonObject();
                jsonInfosMedium.addProperty("denomination", ((Medium)coupleStats[0]).getDenomination());
                jsonInfosMedium.addProperty("nombreConsultations", coupleStats[1].toString());
                jsonArray.add(jsonInfosMedium);
            }
            statistiques.add("listeMediums", jsonArray);
        }
        else {
            statistiques = null;
        }
        return statistiques;
        
    }
    
    public JsonObject genererStatistiquesTempsAppelClients() {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        ClientDao clientDao = new ClientDao();
        JpaUtil.creerContextePersistance();
        
        JsonObject statistiques = new JsonObject();
        JsonArray jsonArray = new JsonArray();
        
        // Boucler sur tous les clients
        List<Client> clients = clientDao.chercherTous();
        for (Client cli : clients) {
            // Obtenir leur historique de consultations
            List<Consultation> historiqueConsultations = consultationDao.chercherTermineParClient(cli);
            long somme = 0;
            // Calculer la somme de leur temps d'appel
            for (Consultation con : historiqueConsultations) {
                Date debut = con.getDateDebut();
                Date fin = con.getDateFin();
                long diff = Math.abs(fin.getTime() - debut.getTime());
                somme += diff;
            }
            Date temps = new Date(somme);
            // Ajouter les données de chaque client au JSON
            JsonObject jsonInfosClient = new JsonObject();
            jsonInfosClient.addProperty("nom", cli.getNom());
            jsonInfosClient.addProperty("tempsAppelTotal", temps.getMinutes());
            jsonArray.add(jsonInfosClient);
        }
        
        JpaUtil.fermerContextePersistance();
        
        statistiques.add("listeClients", jsonArray);
        return statistiques;
    
    }
    
    /* Générer les statistiques de répartition des clients par employés */
    public JsonObject genererStatistiquesRepartitionClientsParEmploye() {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Récupérer une liste d'employés avec le nombre de clients distincts qu'ils ont géré
        List<Object[]> listeStatistiques = consultationDao.obtenirNombreClientsParEmploye();
        
        JpaUtil.fermerContextePersistance();
        
        // Construire le JSON Object
        JsonObject statistiques = new JsonObject();
        if (listeStatistiques != null){
            JsonArray jsonArray = new JsonArray();
            listeStatistiques.forEach(infosEmploye -> {
                JsonObject jsonInfosEmploye = new JsonObject();
                jsonInfosEmploye.addProperty("nom", (String)infosEmploye[0] + " " + (String)infosEmploye[1]);
                jsonInfosEmploye.addProperty("nombreClients", (long)infosEmploye[2]);
                jsonArray.add(jsonInfosEmploye);
            });
            statistiques.add("listeEmployes", jsonArray);
        }
        
        return statistiques;
    }
    
}
