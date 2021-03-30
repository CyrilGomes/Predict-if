/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.presentation;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.service.ServiceClient;
import java.io.IOException;
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
            dateNaissance = AstroAPI.DATE_FORMAT.parse(Saisie.lireChaine("Date de naissance"));

            AstroAPI astroApi = new AstroAPI();
            final ProfilAstral profil = astroApi.getProfil(prenom, dateNaissance);

            final Client c = new Client(nom, prenom, mail, motDePasse, telephone, codePostal, dateNaissance, profil);

            final ServiceClient serviceInscription = new ServiceClient();
            Client res = serviceInscription.inscrire(c);

            if (res != null) {
                System.out.println("> Succès inscription");
                System.out.println(c.toString());
            } else {
                System.out.println("> Echec inscription");
                System.out.println(c.toString());
            }
        } catch (ParseException e) {
            System.err.println("Mauvais format de date de naissance");
        } catch (IOException e){
            System.err.println("Echec de connexion à l'API");
        }
        
    }
    
}
