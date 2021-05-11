package com.projet.dasi.actions;

import com.projet.dasi.service.ServicesApplication;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;

public class GenererStatistiquesAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        
        // Récupération des paramètres de la requête
        String type = request.getParameter("type");
        
        // Appel services
        LinkedHashMap<String, Long> data;
        switch (type) {
            case "mediums":
                data = service.genererStatistiquesMediumsPopulaires(false);
                break;
            case "mediumsPopulaires":
                data = service.genererStatistiquesMediumsPopulaires(true);
                break;
            case "clientsParEmploye":
                data = service.genererStatistiquesRepartitionClientsParEmploye();
                break;
            case "tempsAppelClients":
                data = service.genererStatistiquesTempsAppelClients();
                break;
            default:
                data = null;
                break;
        }
        
        // Stockage des résultats dans les attributs de la requête
        request.setAttribute("statistiques", data);

    }

}
