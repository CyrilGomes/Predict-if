/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author picka
 */
@WebServlet(name = "InfoProfil", urlPatterns = {"/InfoProfil"})
public class InfoProfil extends Action {

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
        Client nouveauClient = new Client(nom, prenom, email, mdp, tel, codePostal, dateNaissance);
        Utilisateur utilisateur = service.inscrireClient(nouveauClient);

        // Stoquage des résultats dans les attributs de la requête
        request.setAttribute("utilisateur", utilisateur);


    }
}
