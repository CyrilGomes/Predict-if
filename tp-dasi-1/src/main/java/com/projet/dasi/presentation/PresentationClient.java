/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.presentation;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServiceApplication;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author creep
 */
public class PresentationClient {
    
    public static void requeteCreationClient() {
        
        final String nom = Saisie.lireChaine("Nom: ");
        final String prenom = Saisie.lireChaine("Prénom: ");
        final String mail = Saisie.lireChaine("Adresse mail: ");
        final String motDePasse = Saisie.lireChaine("Mot de passe: ");
        final String telephone = Saisie.lireChaine("Numéro de téléphone: ");
        final String codePostal = Saisie.lireChaine("Code postal");

        final Date dateNaissance;
        try {
            AstroAPI.DATE_FORMAT.setLenient(false);
            dateNaissance = AstroAPI.DATE_FORMAT.parse(Saisie.lireChaine("Date de naissance"));

            final Client c = new Client(nom, prenom, mail, motDePasse, telephone, codePostal, dateNaissance);

            final ServiceApplication serviceApplication = new ServiceApplication();
            Utilisateur res = serviceApplication.inscrireClient(c);

            if (res != null) {
                System.out.println("> Succès inscription");
                System.out.println(c.toString());
            } else {
                System.out.println("> Echec inscription");
                System.out.println(c.toString());
            }
        } catch (ParseException e) {
            System.err.println("Mauvais format de date de naissance");
        }
        
    }
    
}
