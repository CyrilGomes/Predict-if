/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;

/**
 *
 * @author Cyril
 */
public class ObtenirPredictionsAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();

        ServicesApplication service = new ServicesApplication();

        Employe employe = (Employe) session.getAttribute("utilisateur");
        Consultation consultation = service.obtenirConsultationAttribueeAEmploye(employe);
        int amour = Integer.parseInt(request.getParameter("amour"));
        int travail = Integer.parseInt(request.getParameter("travail"));
        int sante = Integer.parseInt(request.getParameter("sante"));

        if (consultation != null) {
            request.setAttribute("predictions", service.genererPredictionsClient(consultation.getClient(), amour, sante, travail));
        }
    }

}
