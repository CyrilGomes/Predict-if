/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cyril
 */
public class PredictionsSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Initialiser le container
        JsonObject container = new JsonObject();

        // Récupérer les attributs de la requête
        List<String> predictions = (List<String>) request.getAttribute("predictions");
        
        // Populer le container
        container.addProperty("prediction", predictions != null);
        
        container.addProperty("amour",predictions.get(0));
        container.addProperty("sante",predictions.get(1));
        container.addProperty("travail",predictions.get(2));
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
