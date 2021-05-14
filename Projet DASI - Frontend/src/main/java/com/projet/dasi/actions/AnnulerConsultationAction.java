/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.model.Consultation;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cyril
 */
public class AnnulerConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        Consultation consultation = (Consultation)session.getAttribute("consultation");
        boolean result = service.annulerConsultation(consultation);
        request.setAttribute("statut", result);
    }
    
}
