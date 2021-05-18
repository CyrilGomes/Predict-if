/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author creep
 */
public class ObtenirConsultationEnCoursSelonClientAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicesApplication serviceApplication = new ServicesApplication();
        
        HttpSession session = request.getSession();
        
        // Appel services
        Client client = (Client)session.getAttribute("utilisateur");
        Consultation consultation = serviceApplication.obtenirConsultationEnCoursSelonClient(client);
        request.setAttribute("consultation", consultation);
    }
    
}
