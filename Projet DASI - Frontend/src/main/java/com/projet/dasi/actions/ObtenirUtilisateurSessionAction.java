package com.projet.dasi.actions;

import com.projet.dasi.model.Utilisateur;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ObtenirUtilisateurSessionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        
        // Appel services
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
        
        // Stoquage des résultats dans les attributs de la requête
        request.setAttribute("utilisateur", utilisateur);

    }

}
