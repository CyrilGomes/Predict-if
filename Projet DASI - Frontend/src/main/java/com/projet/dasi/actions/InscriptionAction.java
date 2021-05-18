package com.projet.dasi.actions;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

public class InscriptionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        
        // Récupération des paramètres de la requête
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String tel = request.getParameter("tel");
        String codePostal = request.getParameter("codePostal");
        Date dateNaissance = new Date();
        try {
            dateNaissance = AstroAPI.JSON_DATE_FORMAT.parse(request.getParameter("dateNaissance"));
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        
        // Appel services
        Client nouveauClient = new Client(prenom, nom, email, mdp, tel, codePostal, dateNaissance);
        Utilisateur utilisateur = service.inscrireClient(nouveauClient);

        // Stoquage des résultats dans les attributs de la requête
        request.setAttribute("utilisateur", utilisateur);


    }

}
