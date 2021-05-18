/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cyril
 */
public class SauvegarderCommentaireConsultationAction extends Action{

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        
        String commentaire = request.getParameter("commentaire");
        if ("null".equals(commentaire)) {
            commentaire = "Aucun";
        }
        Employe employe = (Employe)session.getAttribute("utilisateur");
        Consultation consultation = service.obtenirConsultationAttribueeAEmploye(employe);
        boolean success = false;
        if (consultation != null) {
            success = service.sauvegarderCommentaireConsultation(consultation, commentaire);
        }
        else {
            consultation = (Consultation)session.getAttribute("consultation");
            success = service.sauvegarderCommentaireConsultation(consultation, commentaire);
        }
        
        request.setAttribute("statut", success);
    }
    
}
