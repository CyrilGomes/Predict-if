package com.projet.dasi.actions;

import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ObtenirHistoriqueClientReqEmployeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        
        String mediumSpecifique = (String)request.getParameter("mediumSpecifique");
        
        // Appel services
        Employe employe = (Employe)session.getAttribute("utilisateur");
        Consultation consultation = service.obtenirConsultationAttribueeAEmploye(employe);
        Client client = consultation.getClient();
        Medium medium = consultation.getMedium();
        List<Consultation> historique; 
        
        // Si on ne demande aucun médium en particulier 
        if ("".equals(mediumSpecifique)) {
            historique = service.obtenirHistoriqueConsultationsClient(client);
        }
        else {
            historique = service.obtenirHistoriqueConsultationsClientSelonMedium(client, medium);   
        }
        
        // Stockage des résultats dans les attributs de la requête
        request.setAttribute("historique", historique);
        request.setAttribute("client", client);

    }

}
