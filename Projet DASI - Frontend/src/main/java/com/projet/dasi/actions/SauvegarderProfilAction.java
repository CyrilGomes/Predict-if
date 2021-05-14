package com.projet.dasi.actions;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SauvegarderProfilAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {

        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Client utilisateur = (Client) request.getSession().getAttribute("utilisateur");
        boolean succes = service.modifierProfil(utilisateur, nom, prenom, dateNaissance, codePostal, tel, email, mdp);

        // Mettre à jour l'utilisateur de la session
        if (succes) {
            Utilisateur utilisateurSession = (Utilisateur) session.getAttribute("utilisateur");
            if (utilisateurSession != null) {
                utilisateurSession.setNom(nom);
                utilisateurSession.setPrenom(prenom);
                utilisateurSession.setDateNaissance(dateNaissance);
                utilisateurSession.setCodePostal(codePostal);
                utilisateurSession.setTelephone(tel);
                utilisateurSession.setMail(email);
                utilisateurSession.setMotDePasse(mdp);
                session.setAttribute("utilisateur", utilisateurSession);
            }
        }

        // Stoquage des résultats dans les attributs de la requête
        request.setAttribute("statut", succes);

    }

}
