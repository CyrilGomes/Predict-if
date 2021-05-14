package com.projet.dasi.actions;

import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SignalerDebutConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        
        
        Employe employe = (Employe) session.getAttribute("utilisateur");
        Consultation consultation = service.obtenirConsultationAttribueeAEmploye(employe);
        
        boolean success = service.signalerDebutConsultation(consultation);

        request.setAttribute("statut", success);
    }

}
