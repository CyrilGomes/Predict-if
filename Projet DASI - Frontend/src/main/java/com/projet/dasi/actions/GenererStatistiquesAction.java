package com.projet.dasi.actions;

import com.google.gson.JsonObject;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.service.ServicesApplication;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GenererStatistiquesAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        
        // Récupération des paramètres de la requête
        String type = request.getParameter("type");
        
        // Appel services
        HashMap<String, Integer> data;
        switch (type) {
            case "mediums":
                data = (HashMap<String,Integer>)service.genererStatistiquesMediumsPopulaires(false);
                break;
            case "mediumsPopulaires":
                data = (HashMap<String,Integer>)service.genererStatistiquesMediumsPopulaires(true);
                break;
            case "clientsParEmploye":
                data = (HashMap<String,Integer>)service.genererStatistiquesRepartitionClientsParEmploye();
                break;
            case "tempsAppelClients":
                data = (HashMap<String,Integer>)service.genererStatistiquesTempsAppelClients();
                break;
            default:
                data = null;
                break;
        }
        
        // Stockage des résultats dans les attributs de la requête
        request.setAttribute("statistiques", data);

    }

}
