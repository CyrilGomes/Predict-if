package com.projet.dasi;

import com.projet.dasi.dao.ClientDao;
import com.projet.dasi.dao.ConsultationDao;
import com.projet.dasi.dao.EmployeDao;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.dao.MediumDao;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Etat;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Spirite;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.presentation.Saisie;
import com.projet.dasi.service.ServicesApplication;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class Main {
    
    final static ServicesApplication servicesApplication = new ServicesApplication();
    
    public static void main(String[] args){
        
        JpaUtil.init();
        
        // Créer un client test, des employés, et des médiums
        //requeteCreationClient();
        servicesApplication.creerClients();
        servicesApplication.creerEmployes();
        servicesApplication.creerMediums();
        
        // Authentifier le client test
        requeteAuthentification(false);
        
        // Tester la demande d'une consultation
        requeteDemandeConsultation();
        
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

    public static void requeteAuthentification(boolean saisie) {
        
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
        
    }

    public static void requeteDemandeConsultation() {
        
        MediumDao mediumDao = new MediumDao();
        ClientDao clientDao = new ClientDao();  
        EmployeDao employeDao = new EmployeDao();
        ConsultationDao consultationDao = new ConsultationDao();
        JpaUtil.creerContextePersistance();
        
        // Conditions de test       
        Medium medium = (Medium)mediumDao.chercherParType("Astrologue").get(0);
        Client client = (Client)clientDao.chercherTous().get(0);
        Employe empPrec = (Employe)employeDao.chercherTous().get(0);
        Consultation prec = new Consultation(empPrec, client, medium);
        prec.setEtat(Etat.Termine);
        try {
            JpaUtil.ouvrirTransaction();
            consultationDao.creer(prec);
            JpaUtil.validerTransaction();
        }
        catch(Exception ex) {
           ex.printStackTrace();
           JpaUtil.annulerTransaction();
        }
        System.out.println(empPrec);
        
        // Test de demande de consultation
        Consultation res = servicesApplication.demanderConsultation(client, medium);
        if (res != null) {
            System.out.println("> Succès demande consultation");
            System.out.println(res.toString());
        } else {
            System.out.println("> Echec demande consultation");
        }
        
    }

    
}
