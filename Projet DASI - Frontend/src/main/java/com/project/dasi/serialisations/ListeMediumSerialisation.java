/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author creep
 */
public class ListeMediumSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Medium> listeMediums = (List<Medium>)request.getAttribute("listeMediums");
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        JsonArray listeMediumsJson = new JsonArray();
        for(Medium medium : listeMediums){
            JsonObject objetMedium = new JsonObject();
            objetMedium.addProperty("denomination", medium.getDenomination());
            objetMedium.addProperty("genre", medium.getGenre().toString());
            objetMedium.addProperty("id", medium.getId());
            objetMedium.addProperty("presentation", medium.getPresentation());
            
            if(medium instanceof Astrologue){
                objetMedium.addProperty("formation", ((Astrologue)medium).getFormation());
                objetMedium.addProperty("promotion", ((Astrologue)medium).getPromotion());
                objetMedium.addProperty("typeMedium", "Astrologue");
            }
            else if(medium instanceof Spirite){
                objetMedium.addProperty("support", ((Spirite)medium).getSupport());
                objetMedium.addProperty("typeMedium", "Spirite");
            }
            else if(medium instanceof Cartomancien){
                objetMedium.addProperty("typeMedium", "Cartomancien");
            }
            listeMediumsJson.add(objetMedium);
        }
        
        JsonObject container = new JsonObject();
        container.add("listeMediums", listeMediumsJson);        
        PrintWriter out = this.getWriter(response);
        gson.toJson(container, out);
        out.close();
    }
    
}
