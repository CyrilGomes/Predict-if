/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.project.dasi.serialisations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Consultation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Cyril
 */
public class ConsultationSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // Initialiser le container
        JsonObject container = new JsonObject();

        // Récupérer les attributs de la requête
        Consultation consultation = (Consultation) request.getAttribute("consultation");

        // Populer le container
        container.addProperty("consultation", consultation != null);
                System.out.println(consultation);
        container.addProperty("clientName", consultation.getClient().getPrenom() + " " + consultation.getClient().getNom());

        container.addProperty("signeAstrologique", consultation.getClient().getProfilAstral().getSigneAstro());
        container.addProperty("signeZodiaqueChinois", consultation.getClient().getProfilAstral().getSigneChinois());
        container.addProperty("couleurPorteBonheur", consultation.getClient().getProfilAstral().getCouleur());
        container.addProperty("animalTotem", consultation.getClient().getProfilAstral().getAnimalTotem());

        container.addProperty("mediumName", consultation.getMedium().getDenomination());
        container.addProperty("role", consultation.getMedium().getClass().getSimpleName());
        container.addProperty("presentation", consultation.getMedium().getPresentation());

        container.addProperty("commentaire", consultation.getCommentaire());

        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
    }

}
