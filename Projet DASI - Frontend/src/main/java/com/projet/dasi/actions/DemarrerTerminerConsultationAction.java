/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cyril
 */
public class DemarrerTerminerConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();

        Employe employe = (Employe)session.getAttribute("utilisateur");
        Consultation consultation = service.obtenirConsultationAttribueeAEmploye(employe);
        boolean result = service.demarrerOuTerminerConsultation(consultation);
        request.setAttribute("statut", result);
        
        //Sauvegarder temporairement la consultation en session 
        session.setAttribute("consultation", consultation);
    }

}
