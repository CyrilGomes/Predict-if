package com.projet.dasi.service;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
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
import com.projet.dasi.presentation.Saisie;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicesApplication {

    public ServicesApplication() {
    }

    /* DEBUG: Creer des clients */
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
    
    /* Créer les employes */
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
    
    /* Créer les mediums */
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
    
    public void creerConsultations() {
        
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
        
        // Créer PLEIN  de consultations
        try {
            JpaUtil.ouvrirTransaction();
            for (Employe e : employes) {
                //if(Math.random() < 0.5) continue;
                for (Medium m : mediums) {
                    //if(Math.random() < 0.5) continue;
                    for (Client c : clients) {
                        //if(Math.random() < 0.5) continue;
                        Consultation consultation = new Consultation(e, c, m);
                        consultation.setEtat(Etat.Termine);
                        consultation.setDateDebut(new Date());
                        consultation.setDateFin(new Date((new Date()).getTime() + 60*1000));
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

    /* Authentifier un utilisateur */
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
    
    /* Lister tous les mediums */
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
    
    /* Lister tous les mediums selon leur type */
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
    
    /* Lister tous les mediums selon leur dénomination */
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
    
    /* Demander consultation */
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
                Saisie.lireChaine("PAUSE");
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
    
    /* Obtenir la consultation assignée à employé */
    public Consultation obtenirConsultationAttribueeAEmploye(Employe employe) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        Consultation consultation;
        try {
            // Récupérer tous les médiums d'une dénomination similaire à celle donnée
            consultation = consultationDao.chercherEnCoursParEmploye(employe);
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
        return reussite;
        
    }

    /* Débuter ou terminer une consultation (selon son état) */
    public boolean demarrerOuTerminerConsultation(Consultation consultation) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Modifier la consultation
        boolean reussite = true;
        if (consultation.getEtat() == Etat.EnAttenteClient) {
            consultation.setEtat(Etat.EnCours); // Démarrer
            consultation.setDateDebut(new Date());
        }
        else if (consultation.getEtat() == Etat.EnCours) {
            consultation.setEtat(Etat.Termine); // Terminer
            consultation.setDateFin(new Date());
        }
        else {
            reussite = false;
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
        
        List<Consultation> listeConsultations;
        try {
            // Récupérer tous les médiums d'une dénomination similaire à celle donnée
            listeConsultations = consultationDao.chercherParClient(client);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            listeConsultations = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        return listeConsultations;
        
    }
    
    /* Obtenir l'historique de consultations d'un client selon un médium donné */
    public List<Consultation> obtenirHistoriqueConsultationsClientSelonMedium(Client client, Medium medium)  {
     
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        List<Consultation> listeConsultations;
        try {
            // Récupérer tous les médiums d'une dénomination similaire à celle donnée
            listeConsultations = consultationDao.chercherParClientEtMedium(client, medium);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            listeConsultations = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
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
    
    public JsonObject genererStatistiquesMedium(boolean top5) {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Obtenir les statistiques
        List<Object[]> liste;
        try {
            // Récupérer tous les médiums d'une dénomination similaire à celle donnée
            if (top5) {
                liste = consultationDao.obtenirTop5NombreConsultationsParMedium();
            }
            else {
                liste = consultationDao.obtenirNombreConsultationsParMedium();
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            liste = null;
        }
        finally {
            JpaUtil.fermerContextePersistance();
        }
        
        // Construire le JSON Object
        JsonObject jsonObject = new JsonObject();
        if (liste != null) {
            JsonArray stats = new JsonArray();
            for (Object[] coupleStats : liste) {
                Medium medium = (Medium)coupleStats[0];
                String denominationMedium = medium.getDenomination();
                String nombreConsultations = coupleStats[1].toString();
                JsonObject data = new JsonObject();
                data.add("denominationMedium", new JsonPrimitive(denominationMedium));
                data.add("nombreConsultations", new JsonPrimitive(nombreConsultations));
                stats.add(data);
            }
            jsonObject.add("listeMediums", stats);
        }
        else {
            jsonObject = null;
        }
        return jsonObject;
        
    }
    
    public JsonObject genererStatistiquesClient() {
        
        // Créer les DAOs et le contexte de persistance
        ConsultationDao consultationDao = new ConsultationDao();
        ClientDao clientDao = new ClientDao();
        JpaUtil.creerContextePersistance();
        
        JsonObject jsonObject = new JsonObject();
        JsonArray stats = new JsonArray();
        
        // Boucler sur tous les clients
        List<Client> clients = clientDao.chercherTous();
        for (Client cli : clients) {
            // Obtenir leur historique de consultations
            List<Consultation> historiqueConsultations = consultationDao.chercherParClient(cli);
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
            JsonObject data = new JsonObject();
            data.add("client", new JsonPrimitive(cli.getNom()));
            data.add("tempsAppelTotal", new JsonPrimitive(temps.getMinutes()));
            stats.add(data);
        }
        jsonObject.add("listeClients", stats);
        return jsonObject;
    
    }
    
    /* Générer les statistiques de répartition des clients par employés */
    public JsonObject genererStatistiquesRepartitionClients(){
        ConsultationDao consultationDao = new ConsultationDao();
        JsonObject statistiques = new JsonObject();
        List<Object[]> listeStatistiques;
        
        JpaUtil.creerContextePersistance();
        listeStatistiques = consultationDao.chercherNbClientParEmploye();
        JpaUtil.fermerContextePersistance();
        if(listeStatistiques != null){
            JsonArray jsonArray = new JsonArray();
            listeStatistiques.forEach(infosEmploye -> {
                JsonObject jsonInfosEmploye = new JsonObject();
                jsonInfosEmploye.addProperty("Nom", (String)infosEmploye[0] + " " + (String)infosEmploye[1]);
                jsonInfosEmploye.addProperty("nbClients", (long)infosEmploye[2]);
                jsonArray.add(jsonInfosEmploye);
            });
            statistiques.add("listeEmployes", jsonArray);
        }
        
        return statistiques;
    }
    
}
