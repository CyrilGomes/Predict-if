/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.AstroAPI;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Utilisateur;
import com.projet.dasi.service.ServicesApplication;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Cyril
 */
public class ArchiverConsultationAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        HttpSession session = request.getSession();
        session.removeAttribute("consultation");
        request.setAttribute("statut", true);
    }

}
