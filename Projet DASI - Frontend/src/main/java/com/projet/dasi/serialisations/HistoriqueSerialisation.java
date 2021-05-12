package com.projet.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HistoriqueSerialisation extends Serialisation {
    
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
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();        
        
    }
    
}
