package com.projet.dasi;

import com.google.gson.JsonObject;
import com.projet.dasi.dao.ClientDao;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.presentation.Saisie;
import com.projet.dasi.service.ServicesApplication;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main {
    
    final static ServicesApplication servicesApplication = new ServicesApplication();
    static Utilisateur utilisateurConnecte;
    
    public static void main(String[] args) throws InterruptedException {
        
        JpaUtil.init();
        
        // Créer des employés, et des médiums
        servicesApplication.initialiserEmployes();
        servicesApplication.initialiserMediums();
        
        // Générer des clients tests et des consultations
        //requeteCreationClient();
        servicesApplication.genererClients();
        servicesApplication.genererConsultations();
        
        // Authentifier le client test
        utilisateurConnecte = requeteAuthentification(false);
        
        // Tester l'attribution des consultations et leur manipulations
        if (utilisateurConnecte != null) {
            Consultation c = requeteDemandeConsultation(false);
            if (c != null) {
                requeteManipulerConsultation(c, 0); // signaler début
                requeteManipulerConsultation(c, 1); // démarrer
                Thread.sleep(2000); //attendre 2 secondes le temps d'une consultation...
                requeteManipulerConsultation(c, 1); // arrêter
                requeteManipulerConsultation(c, 2); // annuler (impossible après arrêter?)
                requeteManipulerConsultation(c, 3); // sauvegarder un commentaire
            }
        }
        
        // Tester la génération de statistiques
        requeteStatistiques();
        
        JpaUtil.destroy();
        
    }
    
    public static void requeteCreationClient(boolean saisie) {
        
        final String nom, prenom, mail, motDePasse, telephone, codePostal;
        Date dateNaissance = new Date();
        AstroAPI.DATE_FORMAT.setLenient(false);

        nom = Saisie.lireChaine("Nom: ");
        prenom = Saisie.lireChaine("Prénom: ");
        mail = Saisie.lireChaine("Adresse mail: ");
        motDePasse = Saisie.lireChaine("Mot de passe: ");
        telephone = Saisie.lireChaine("Numéro de téléphone: ");
        codePostal = Saisie.lireChaine("Code postal");
        try {
            dateNaissance = AstroAPI.DATE_FORMAT.parse(Saisie.lireChaine("Date de naissance"));
        } 
        catch (ParseException e) {
            System.err.println("Mauvais format de date de naissance");
        }

        final Client c = new Client(nom, prenom, mail, motDePasse, telephone, codePostal, dateNaissance);
        
        Utilisateur res = servicesApplication.inscrireClient(c);
        if (res != null) {
            System.out.println("> Succès inscription");
            System.out.println(res.toString());
        } else {
            System.out.println("> Echec inscription");
            System.out.println(res.toString());
        }

    }

    public static Utilisateur requeteAuthentification(boolean saisie) {
        
        final String mail, motDePasse;
        
        if (saisie) {
            mail = Saisie.lireChaine("Adresse mail: ");
            motDePasse = Saisie.lireChaine("Mot de passe: ");
        }
        else {
            mail = "bertrand.usclat@yahoo.ro";
            motDePasse = "superCanardXXL";
        }
        
        Utilisateur res = servicesApplication.authentifier(mail, motDePasse);
        if(res != null){
            System.out.println("Connexion réussie");
            System.out.println(res);
        }
        else{
            System.out.println("Echec de connexion");
        }
        return res; 
        
    }

    public static Consultation requeteDemandeConsultation(boolean saisie) {
        
        final String denominationMedium;
        
        if (saisie) {
            denominationMedium = Saisie.lireChaine("Médium à consulter: ");
        }
        else {
            denominationMedium = "Mme Irma";
        }

        List<Medium> mediums = servicesApplication.obtenirMediumsSelonDenomination(denominationMedium);
        Medium medium = null;
        Consultation res = null;
        if (mediums.size() > 0) {
            medium = mediums.get(0);
        }
        if (medium != null) {
            res = servicesApplication.demanderConsultation((Client)utilisateurConnecte, medium);
            if (res != null) {
                System.out.println("> Succès demande consultation");
                System.out.println(res.toString());
            } else {
                System.out.println("> Echec demande consultation");
            }
        }
        else {
            System.out.println("> Le médium entré n'a pas été trouvé");
        }
        
        return res;
        
    }
    
    public static void requeteDemandePredictions() {
        
        ClientDao clientDao = new ClientDao();  
        JpaUtil.creerContextePersistance();
        
        Client client = (Client)clientDao.chercherTous().get(0);
        System.out.println(client);
        
        JpaUtil.fermerContextePersistance();
        
        List<String> res = servicesApplication.genererPredictionsClient(client, 5, 2, 1);
        if (res != null) {
            System.out.println("> Succès demande prédictions");
            for (String s : res) {
                System.out.println(s);
            }
        } else {
            System.out.println("> Echec demande prédictions");
        }
           
    }
    
    public static void requeteManipulerConsultation(Consultation consultation, int action) {
        
        String commentaire = "coucou ceci est un commentaire";
        
        switch (action) {
            // Signaler début
            case 0:
                servicesApplication.signalerDebutConsultation(consultation);
                break;
            // Démarrer / Arrêter
            case 1:
                servicesApplication.demarrerOuTerminerConsultation(consultation);
                break;
            // Annuler
            case 2:
                servicesApplication.annulerConsultation(consultation);
                break;
            // Sauvegarder commentaires
            case 3:
                servicesApplication.sauvegarderCommentaireConsultation(consultation, commentaire);
                break;
        }
        
        System.out.println(action + "; " + consultation);
        
    }
    
    public static void requeteStatistiques() {
        
        JsonObject res = servicesApplication.genererStatistiquesMediumsPopulaires(true);
        if (res != null) {
            System.out.println("> Succès demande statistiques mediums");
            System.out.println(res.toString());
        }
        else {
            System.out.println("> Échec demande statistiques mediums");
        }
        
        res = servicesApplication.genererStatistiquesTempsAppelClients();
        if (res != null) {
            System.out.println("> Succès demande statistiques clients");
            System.out.println(res.toString());
        }
        else {
            System.out.println("> Échec demande statistiques clients");
        }
        
        res = servicesApplication.genererStatistiquesRepartitionClientsParEmploye();
        if (res != null) {
            System.out.println("> Succès demande statistiques employes");
            System.out.println(res.toString());
        }
        else {
            System.out.println("> Échec demande statistiques employes");
        }

    }

    
}
