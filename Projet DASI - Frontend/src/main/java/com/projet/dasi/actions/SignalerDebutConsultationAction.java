package com.projet.dasi.actions;

import com.projet.dasi.model.Consultation;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignalerDebutConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        
        Consultation consultation = (Consultation)session.getAttribute("consultation");
        
        boolean success = service.signalerDebutConsultation(consultation);

        request.setAttribute("statut", success);
    }

}
