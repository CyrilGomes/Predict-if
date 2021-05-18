/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Spirite;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author creep
 */
public class ObtenirConsultationEnCoursSelonClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Consultation consultation = (Consultation) request.getAttribute("consultation");

        JsonObject container = new JsonObject();
        container.addProperty("consultationEnCours", consultation != null);
        if (consultation != null) {
            Medium medium = consultation.getMedium();
            JsonObject mediumData = new JsonObject();
            mediumData.addProperty("denomination", medium.getDenomination());
            mediumData.addProperty("genre", medium.getGenre().toString());
            mediumData.addProperty("id", medium.getId());
            mediumData.addProperty("presentation", medium.getPresentation());

            if (medium instanceof Astrologue) {
                mediumData.addProperty("formation", ((Astrologue) medium).getFormation());
                mediumData.addProperty("promotion", ((Astrologue) medium).getPromotion());
                mediumData.addProperty("type", "Astrologue");
            } else if (medium instanceof Spirite) {
                mediumData.addProperty("support", ((Spirite) medium).getSupport());
                mediumData.addProperty("type", "Spirite");
            } else if (medium instanceof Cartomancien) {
                mediumData.addProperty("type", "Cartomancien");
            }
            container.add("mediumConsulte", mediumData);
        }
        
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
