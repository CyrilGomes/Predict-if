/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Cyril
 */
public class InscriptionAction extends Action {

    public InscriptionAction(ServicesApplication service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
        try {
            Client c = new Client(request.getParameter("nom"), request.getParameter("prenom"),
                    request.getParameter("email"), request.getParameter("mdp"),
                    request.getParameter("tel"), request.getParameter("codePostal"), AstroAPI.DATE_FORMAT.parse(request.getParameter("dateNaissance")));
            Utilisateur u = service.inscrireClient(c);

            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonObject result = new JsonObject();
            result.addProperty("inscription", u != null);
            result.add("client", gson.toJsonTree(u));
            request.setAttribute("result", gson.toJson(result));
        } catch(Exception e)
        {}

    }

}
