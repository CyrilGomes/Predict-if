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

public class ObtenirConsultationAttribueeAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        
        ServicesApplication service = new ServicesApplication();
        HttpSession session = request.getSession();
        
        // Appel services
        Consultation consultation = (Consultation)session.getAttribute("consultation");
        if (consultation == null) {
            Employe employe = (Employe)session.getAttribute("utilisateur");
            consultation = service.obtenirConsultationAttribueeAEmploye(employe);
            
            //DEBUG
            /*
            Employe employeLogge = (Employe) request.getSession().getAttribute("utilisateur");
            Medium mediumTest = new Cartomancien(Genre.Homme, "ASTROMAN", "LE CHRIST COSMIQUE");
            ProfilAstral profilAstralTest = new ProfilAstral("OSEF", "PLEASE", "LAISSEZ MOI", "TRANQUILLE");
            Client clientTest = new Client("NOM", "PRENOM", "ceciEstMonMail@gmail.com", "secretMdp", "0695932520", "69230", new Date());
            clientTest.setProfilAstral(profilAstralTest);
            consultation = new Consultation(employeLogge, clientTest, mediumTest);
            */
            
            session.setAttribute("consultation", consultation);
        }
        
        // Stoquage des résultats dans les attributs de la requête
        request.setAttribute("consultation", consultation);

    }

}
