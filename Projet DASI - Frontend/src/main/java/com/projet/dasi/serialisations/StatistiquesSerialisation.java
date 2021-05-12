package com.projet.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StatistiquesSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Récupérer les attributs de la requête
        LinkedHashMap<String, Long> map = (LinkedHashMap<String, Long>)request.getAttribute("statistiques");
        Set<String> keys = map.keySet();
        JsonArray keysetJSON = new JsonArray();
        JsonArray valuesJSON = new JsonArray();
        
        for (String key : keys) {
            keysetJSON.add(key); 
            valuesJSON.add(map.get(key)); 
        }
        
        JsonObject container = new JsonObject();
        container.add("x", keysetJSON);
        container.add("y", valuesJSON);
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
}
