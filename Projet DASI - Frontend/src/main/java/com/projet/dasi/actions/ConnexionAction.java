/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Cyril
 */
public class ConnexionAction extends Action {

    public ConnexionAction(ServicesApplication service) {
        super(service);
    }

    @Override
    public void execute(HttpServletRequest request) {
                         
                    Utilisateur u = service.authentifier((String) request.getParameter("login"), (String) request.getParameter("password"));

                    Gson gson = new GsonBuilder().setPrettyPrinting().create();
                    JsonObject result = new JsonObject();
                    result.addProperty("connexion", u != null);
                    result.add("client", gson.toJsonTree(u));
                    request.setAttribute("result", gson.toJson(result));
                    
    }
    
}
