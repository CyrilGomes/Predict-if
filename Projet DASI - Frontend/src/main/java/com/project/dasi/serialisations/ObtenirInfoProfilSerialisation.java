package com.project.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ObtenirInfoProfilSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Initialiser le container
        JsonObject container = new JsonObject();
        
        // Récupérer les attributs de la requête
        Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
        
        // Populer le container
        if (utilisateur instanceof Client) { 
            ProfilAstral astro = ((Client)utilisateur).getProfilAstral();
            container.addProperty("type", "client");
            container.addProperty("animalTotem", astro.getAnimalTotem());
            container.addProperty("signeAstro", astro.getSigneAstro());
            container.addProperty("couleur", astro.getCouleur());
            container.addProperty("signeChinois", astro.getSigneChinois());
        }
        if (utilisateur instanceof Employe) { container.addProperty("type", "employe"); }
        
        container.addProperty("nom", utilisateur.getNom());
        container.addProperty("prenom", utilisateur.getPrenom());
        container.addProperty("date-de-naissance", utilisateur.getDateNaissance().toString());
        container.addProperty("code-postal", utilisateur.getCodePostal());
        container.addProperty("telephone", utilisateur.getTelephone());
        container.addProperty("mail", utilisateur.getMail());
        container.addProperty("mot-de-passe", utilisateur.getMotDePasse());
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();        
        
    }
    
}
