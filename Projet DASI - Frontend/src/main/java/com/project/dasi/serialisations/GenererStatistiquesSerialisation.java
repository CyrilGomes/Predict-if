package com.project.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GenererStatistiquesSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        // Récupérer les attributs de la requête
        HashMap<String, Integer> map = (HashMap<String, Integer>)request.getAttribute("statistiques");
        Set<String> keySet = map.keySet();
        Collection<Integer> values = map.values();
        
        JsonArray keySetJSON = new JsonArray();
        for(String key : keySet){
            keySetJSON.add(key);
        }
        
        JsonArray valuesJSON = new JsonArray();
        for(Integer value : values){
            keySetJSON.add(value);
        }

        JsonObject container = new JsonObject();
        container.add("x", keySetJSON);
        container.add("y", valuesJSON);        
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
        
    }
    
}
