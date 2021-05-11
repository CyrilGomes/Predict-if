package com.projet.dasi.actions;

import com.google.gson.JsonObject;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GenererStatistiquesAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        
        // Récupération des paramètres de la requête
        String type = request.getParameter("type");
        
        // Appel services
        JsonObject data = new JsonObject();
        switch (type) {
            case "mediums":
                data = (JsonObject)service.genererStatistiquesMediumsPopulaires(false);
                break;
            case "mediumsPopulaires":
                data = (JsonObject)service.genererStatistiquesMediumsPopulaires(true);
                break;
            case "clientsParEmploye":
                data = (JsonObject)service.genererStatistiquesRepartitionClientsParEmploye();
                break;
            case "tempsAppelClients":
                data = (JsonObject)service.genererStatistiquesTempsAppelClients();
                break;
        }
        
        // Stoquage des résultats dans les attributs de la requête
        request.setAttribute("statistiques", data);

    }

}
