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
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cyril
 */
public class ConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        HttpSession session = request.getSession();

        Employe employeLogge = (Employe) request.getSession().getAttribute("utilisateur");
        Medium mediumTest = new Cartomancien(Genre.Homme, "ASTROMAN", "LE CHRIST COSMIQUE");
        ProfilAstral profilAstralTest = new ProfilAstral("OSEF", "PLEASE", "LAISSEZ MOI", "TRANQUILLE");
        Client clientTest = new Client("NOM", "PRENOM", "ceciEstMonMail@gmail.com", "secretMdp", "0695932520", "69230", new Date());
        clientTest.setProfilAstral(profilAstralTest);
        session.setAttribute("consultation", new Consultation(employeLogge, clientTest, mediumTest));
        
        ServicesApplication service = new ServicesApplication();
        Consultation consultation = (Consultation) session.getAttribute("consultation");

        if (consultation != null) {
            request.setAttribute("consultation", consultation);
        }
    }

}
