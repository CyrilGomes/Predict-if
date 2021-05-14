package com.projet.dasi.actions;

import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Genre;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.ProfilAstral;
import com.projet.dasi.model.Etat;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class ObtenirEtatConsultationSessionAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();

        Employe employe = (Employe) session.getAttribute("utilisateur");
        Consultation consultation = service.obtenirConsultationAttribueeAEmploye(employe);
        String etat = "";
        if (consultation != null) {
            etat = consultation.getEtat().toString();
        }
        request.setAttribute("etat", etat);

    }

}
