package com.project.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Spirite;
import com.projet.dasi.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ObtenirHistoriqueSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Initialiser le container
        JsonObject container = new JsonObject();
        
        // Récupérer les attributs de la requête
        Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
        
        // Populer le container
        container.addProperty("utilisateur", utilisateur != null);
        if (utilisateur instanceof Client) { container.addProperty("type", "client"); }
        if (utilisateur instanceof Employe) { container.addProperty("type", "employe"); }
        
        
        List<Consultation> historique = (List<Consultation>)request.getAttribute("historique");
       
        JsonArray historiqueJson = new JsonArray();
        for(Consultation consultation : historique){
            
            Medium medium = consultation.getMedium();
            JsonObject Consultation = new JsonObject();
            
            Consultation.addProperty("date", consultation.getDateDebut().toString());
            Consultation.addProperty("genre", medium.getGenre().toString());
            Consultation.addProperty("id", medium.getId());
            Consultation.addProperty("presentation", medium.getPresentation());
            
            if(medium instanceof Astrologue){
                Consultation.addProperty("formation", ((Astrologue)medium).getFormation());
                Consultation.addProperty("promotion", ((Astrologue)medium).getPromotion());
                Consultation.addProperty("typeMedium", "Astrologue");
            }
            else if(medium instanceof Spirite){
                Consultation.addProperty("support", ((Spirite)medium).getSupport());
                Consultation.addProperty("typeMedium", "Spirite");
            }
            else if(medium instanceof Cartomancien){
                Consultation.addProperty("typeMedium", "Cartomancien");
            }
            historiqueJson.add(Consultation);
        }
        
        container.add("historique", historiqueJson);
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
 
    }
    
}
