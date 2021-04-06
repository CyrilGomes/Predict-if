/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.presentation;

import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServiceApplication;

/**
 *
 * @author creep
 */
public class PresentationUtilisateur {
    public static void requeteAuthentification() {
        final String mail = Saisie.lireChaine("Adresse mail: ");
        final String motDePasse = Saisie.lireChaine("Mot de passe: ");
        
        ServiceApplication serviceApplication = new ServiceApplication();
        Utilisateur u = serviceApplication.authentifier(mail, motDePasse);
        if(u != null){
            System.out.println("Connexion réussie");
            System.out.println(u);
        }
        else{
            System.out.println("Echec de connexion");
        }
    }
}
