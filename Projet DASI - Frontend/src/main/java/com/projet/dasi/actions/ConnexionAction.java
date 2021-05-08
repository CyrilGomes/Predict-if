package com.projet.dasi.actions;

import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ConnexionAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        
        // Récupération des paramètres de la requête
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        
        // Appel service
        Utilisateur utilisateur = service.authentifier(login, password);
        
        // Sauvegarde de l'utilisateur dans la session
        session.setAttribute("utilisateur", utilisateur);
        
        // Stoquage des résultats dans les attributs de la requête
        request.setAttribute("utilisateur", utilisateur);
                    
    }
    
}
