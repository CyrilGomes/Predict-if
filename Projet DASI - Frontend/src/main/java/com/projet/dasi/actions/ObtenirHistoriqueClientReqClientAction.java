package com.projet.dasi.actions;

import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ObtenirHistoriqueClientReqClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        
        // Appel services
        Client client = (Client)session.getAttribute("utilisateur");
        List<Consultation> historique = service.obtenirHistoriqueConsultationsClient(client);
        
        // Stockage des résultats dans les attributs de la requête
        request.setAttribute("historique", historique);

    }

}
