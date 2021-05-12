/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author creep
 */
public class DemanderConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicesApplication serviceApplication = new ServicesApplication();
        int idMedium = Integer.parseInt(request.getParameter("id"));
        Medium medium = serviceApplication.obtenirMediumSelonId(idMedium);
        
        HttpSession session = request.getSession();
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
        
        if(utilisateur instanceof Client){
            Consultation c = serviceApplication.demanderConsultation((Client)utilisateur, medium);
            request.setAttribute("statut", c != null);
        }
    }
    
}
