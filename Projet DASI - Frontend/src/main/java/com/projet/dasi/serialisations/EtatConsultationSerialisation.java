package com.projet.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.model.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EtatConsultationSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Initialiser le container
        JsonObject container = new JsonObject();
        
        // Récupérer les attributs de la requête
        String etat = (String)request.getAttribute("etat");
        
        // Populer le container
        container.addProperty("etat", etat);
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
}
