package com.projet.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
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
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConsultationSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Initialiser le container
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonObject container = new JsonObject();
        
        // Récupérer les attributs de la requête
        Consultation consultation = (Consultation)request.getAttribute("consultation");
        
        // Populer le container
        container.addProperty("consultation", consultation != null);
        
        if (consultation != null) {
        
            // Sérialiser le client de la consultation
            Client client = consultation.getClient();
            ProfilAstral profil = client.getProfilAstral();
            JsonObject clientData = new JsonObject();
            JsonObject profilData = new JsonObject();
            profilData.addProperty("signeAstro", profil.getSigneAstro());
            profilData.addProperty("signeChinois", profil.getSigneChinois());
            profilData.addProperty("couleur", profil.getCouleur());
            profilData.addProperty("animalTotem", profil.getAnimalTotem());
            clientData.addProperty("prenom", client.getPrenom());
            clientData.addProperty("nom", client.getNom());
            clientData.add("profil", profilData);
            container.add("client", clientData);

            // Sérialiser le médium de la consultation
            Medium medium = consultation.getMedium();
            JsonObject mediumData = (JsonObject)gson.toJsonTree(medium);
            if (medium instanceof Cartomancien) { mediumData.addProperty("type", "cartomancien"); }
            if (medium instanceof Spirite) { mediumData.addProperty("type", "spirite"); }
            if (medium instanceof Astrologue) { mediumData.addProperty("type", "astrologue"); }
            container.add("medium", mediumData);
            
            // Sérialiser le commentaire et l'état
            container.addProperty("commentaire", consultation.getCommentaire());
            container.addProperty("etat", consultation.getEtat().toString());
        
        }
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        gson.toJson(container, out);
        out.close();
        
    }
    
}
