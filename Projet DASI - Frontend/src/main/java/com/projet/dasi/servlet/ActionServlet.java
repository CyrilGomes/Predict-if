package com.projet.dasi.servlet;

import com.project.dasi.serialisations.StatutSerialisation;
import com.project.dasi.serialisations.ConsultationSerialisation;
import com.project.dasi.serialisations.PredictionsSerialisation;
import com.project.dasi.serialisations.ListeMediumSerialisation;
import com.project.dasi.serialisations.StatistiquesSerialisation;
import com.project.dasi.serialisations.TypeUtilisateurSerialisation;
import com.project.dasi.serialisations.ConsultationSerialisation;
import com.project.dasi.serialisations.HistoriqueSerialisation;
import com.project.dasi.serialisations.Serialisation;
import com.project.dasi.serialisations.UtilisateurSerialisation;
import com.projet.dasi.actions.Action;
import com.projet.dasi.actions.AnnulerConsultationAction;
import com.projet.dasi.actions.ConnexionAction;
import com.projet.dasi.actions.ConsultationAction;
import com.projet.dasi.actions.DemarrerTerminerConsultationAction;
import com.projet.dasi.actions.InscriptionAction;
import com.projet.dasi.actions.ObtenirPredictionsAction;
import com.projet.dasi.actions.SauvegarderCommentaireConsultationAction;
import com.projet.dasi.actions.ActionDemandeConsultation;
import com.projet.dasi.actions.ConnexionAction;
import com.projet.dasi.actions.GenererStatistiquesAction;
import com.projet.dasi.actions.InscriptionAction;
import com.projet.dasi.actions.ObtenirListeMediumAction;
import com.projet.dasi.actions.ObtenirConsultationAttribueeAction;
import com.projet.dasi.actions.ObtenirHistoriqueAction;
import com.projet.dasi.actions.ObtenirUtilisateurCourantAction;
import com.projet.dasi.actions.SauvegarderProfilAction;
import com.projet.dasi.actions.SignalerDebutConsultationAction;
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
                serialisation = new TypeUtilisateurSerialisation();
                break;
                
            case "obtenirHistorique":
                action = new ObtenirHistoriqueAction();
                serialisation = new HistoriqueSerialisation();
                break;
            
            case "sauvegarderProfil":
                action = new SauvegarderProfilAction();
                serialisation = new TypeUtilisateurSerialisation();
                break;
                
            case "obtenirUtilisateurCourant":
                action = new ObtenirUtilisateurCourantAction();
                serialisation = new UtilisateurSerialisation();
                break;
            
            case "connexion":
                action = new ConnexionAction();
                serialisation = new TypeUtilisateurSerialisation();
                break;

            case "inscription":
                action = new InscriptionAction();
                serialisation = new TypeUtilisateurSerialisation();
                break;

            case "obtenirPredictions":
                action = new ObtenirPredictionsAction();
                serialisation = new PredictionsSerialisation();
                break;
                
            case "sauvegarderCommentaire":
                action = new SauvegarderCommentaireConsultationAction();
                serialisation = new StatutSerialisation();
                break;
                
            case "demarrerTerminerConsultation":
                action = new DemarrerTerminerConsultationAction();
                serialisation = new StatutSerialisation();
                break;
                
            case "annulerConsultation":
                action = new AnnulerConsultationAction();
                serialisation = new StatutSerialisation();
                break;
                
            case "obtenirListeMediums":
                action = new ObtenirListeMediumAction();
                serialisation = new ListeMediumSerialisation();
                break;
                
            case "demanderConsultation":
                action = new ActionDemandeConsultation();
                serialisation = new StatutSerialisation();
                break;
                
            case "obtenirConsultationAttribuee":
                action = new ObtenirConsultationAttribueeAction();
                serialisation = new ConsultationSerialisation();
                break;
                
            case "genererStatistiques":
                action = new GenererStatistiquesAction();
                serialisation = new StatistiquesSerialisation();
                break;
                
            case "signalerDebutConsultation":
                action = new SignalerDebutConsultationAction();
                serialisation = new StatutSerialisation();
                break;
        }

        if (action != null && serialisation != null) {
            action.executer(request);
            serialisation.serialiser(request, response);
        } 
        else {
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
