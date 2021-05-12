package com.projet.dasi.servlet;

import com.project.dasi.serialisations.AnnulerConsultationSerialisation;
import com.project.dasi.serialisations.ConnexionSerialisation;
import com.project.dasi.serialisations.ConsultationSerialisation;
import com.project.dasi.serialisations.DemarrerTerminerSerialisation;
import com.project.dasi.serialisations.InscriptionSerialisation;
import com.project.dasi.serialisations.PredictionSerialisation;
import com.project.dasi.serialisations.SaveCommentaireSerialisation;
import com.project.dasi.serialisations.DemandeConsultationSerialisation;
import com.project.dasi.serialisations.ListeMediumSerialisation;
import com.project.dasi.serialisations.GenererStatistiquesSerialisation;
import com.project.dasi.serialisations.ObtenirTypeUtilisateurSerialisation;
import com.project.dasi.serialisations.ObtenirConsultationAttribueeSerialisation;
import com.project.dasi.serialisations.ObtenirHistoriqueSerialisation;
import com.project.dasi.serialisations.Serialisation;
import com.project.dasi.serialisations.ObtenirUtilisateurSerialisation;
import com.projet.dasi.actions.Action;
import com.projet.dasi.actions.AnnulerConsultationAction;
import com.projet.dasi.actions.ConnexionAction;
import com.projet.dasi.actions.ConsultationAction;
import com.projet.dasi.actions.DemarrerTerminerAction;
import com.projet.dasi.actions.InscriptionAction;
import com.projet.dasi.actions.PredictionAction;
import com.projet.dasi.actions.SaveCommentaireAction;
import com.projet.dasi.actions.ActionDemandeConsultation;
import com.projet.dasi.actions.ConnexionAction;
import com.projet.dasi.actions.GenererStatistiquesAction;
import com.projet.dasi.actions.InscriptionAction;
import com.projet.dasi.actions.ListeMediumAction;
import com.projet.dasi.actions.ObtenirConsultationAttribueeAction;
import com.projet.dasi.actions.ObtenirHistoriqueAction;
import com.projet.dasi.actions.ObtenirUtilisateurCourantAction;
import com.projet.dasi.actions.SauvegarderProfilAction;
import com.projet.dasi.dao.JpaUtil;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String typeRequete = request.getParameter("todo");

        Action action = null;
        Serialisation serialisation = null;

        switch (typeRequete) {
            
            case "obtenirTypeUtilisateurCourant":
                action = new ObtenirUtilisateurCourantAction();
                serialisation = new ObtenirTypeUtilisateurSerialisation();
                break;
                
            case "obtenirListeConsultations":
                action = new ObtenirHistoriqueAction();
                serialisation = new ObtenirHistoriqueSerialisation();
                break;
            
            case "sauvegarderDonnees":
                action = new SauvegarderProfilAction();
                serialisation = new ObtenirTypeUtilisateurSerialisation();
                break;
                
            case "obtenirUtilisateurCourant":
                action = new ObtenirUtilisateurCourantAction();
                serialisation = new ObtenirUtilisateurSerialisation();
                break;
            
            case "connexion":
                action = new ConnexionAction();
                serialisation = new ObtenirTypeUtilisateurSerialisation();
                break;

            case "inscription":
                action = new InscriptionAction();
                serialisation = new ObtenirTypeUtilisateurSerialisation();
                break;

            case "prediction":
                action = new PredictionAction();
                serialisation = new PredictionSerialisation();
                break;
            case "consultation":
                action = new ConsultationAction();
                serialisation = new ConsultationSerialisation();
                break;
            case "saveCommentaire":
                action = new SaveCommentaireAction();
                serialisation = new SaveCommentaireSerialisation();
                break;
            case "demarrerTerminer":
                action = new DemarrerTerminerAction();
                serialisation = new DemarrerTerminerSerialisation();
                break;
            case "annulerConsultation":
                action = new AnnulerConsultationAction();
                serialisation = new AnnulerConsultationSerialisation();
                
            case "listeMediums":
                action = new ListeMediumAction();
                serialisation = new ListeMediumSerialisation();
                break;
                
            case "demanderConsultation":
                action = new ActionDemandeConsultation();
                serialisation = new DemandeConsultationSerialisation();
                break;
                
            case "obtenirConsultationAttribuee":
                action = new ObtenirConsultationAttribueeAction();
                serialisation = new ObtenirConsultationAttribueeSerialisation();
                break;
                
            case "genererStatistiques":
                action = new GenererStatistiquesAction();
                serialisation = new GenererStatistiquesSerialisation();
                break;
        }

        if (action != null && serialisation != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        } else {
            response.sendError(400, "Bad Request (pas d'action ou de sérialisation à traiter)");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public void init() throws ServletException {
        super.init();
        JpaUtil.init();
    }

    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
