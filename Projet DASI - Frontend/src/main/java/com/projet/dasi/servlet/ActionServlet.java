package com.projet.dasi.servlet;

import com.projet.dasi.serialisations.StatutSerialisation;
import com.projet.dasi.serialisations.PredictionsSerialisation;
import com.projet.dasi.serialisations.ListeMediumSerialisation;
import com.projet.dasi.serialisations.StatistiquesSerialisation;
import com.projet.dasi.serialisations.TypeUtilisateurSerialisation;
import com.projet.dasi.serialisations.ConsultationSerialisation;
import com.projet.dasi.serialisations.HistoriqueClientReqClientSerialisation;
import com.projet.dasi.serialisations.HistoriqueClientReqEmployeSerialisation;
import com.projet.dasi.serialisations.Serialisation;
import com.projet.dasi.serialisations.UtilisateurSerialisation;
import com.projet.dasi.actions.Action;
import com.projet.dasi.actions.AnnulerConsultationAction;
import com.projet.dasi.actions.DemarrerTerminerConsultationAction;
import com.projet.dasi.actions.ObtenirPredictionsAction;
import com.projet.dasi.actions.SauvegarderCommentaireConsultationAction;
import com.projet.dasi.actions.DemanderConsultationAction;
import com.projet.dasi.actions.ArchiverConsultationAction;
import com.projet.dasi.actions.ConnexionAction;
import com.projet.dasi.actions.DeconnexionAction;
import com.projet.dasi.actions.GenererStatistiquesAction;
import com.projet.dasi.actions.InscriptionAction;
import com.projet.dasi.actions.ObtenirListeMediumAction;
import com.projet.dasi.actions.ObtenirConsultationAttribueeAction;
import com.projet.dasi.actions.ObtenirEtatConsultationSessionAction;
import com.projet.dasi.actions.ObtenirHistoriqueClientReqClientAction;
import com.projet.dasi.actions.ObtenirHistoriqueClientReqEmployeAction;
import com.projet.dasi.actions.ObtenirUtilisateurSessionAction;
import com.projet.dasi.actions.SauvegarderProfilAction;
import com.projet.dasi.actions.SignalerDebutConsultationAction;
import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.serialisations.EtatConsultationSerialisation;
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
                action = new ObtenirUtilisateurSessionAction();
                serialisation = new TypeUtilisateurSerialisation();
                break;
                
            case "demandeDeconnexion":
                action = new DeconnexionAction();
                serialisation = new TypeUtilisateurSerialisation();
                break;
                
            case "obtenirHistoriqueClientReqClient":
                action = new ObtenirHistoriqueClientReqClientAction();
                serialisation = new HistoriqueClientReqClientSerialisation();

                break;
                
            case "obtenirHistoriqueClientReqEmploye":
                action = new ObtenirHistoriqueClientReqEmployeAction();
                serialisation = new HistoriqueClientReqEmployeSerialisation();
                break;

            
            case "sauvegarderProfil":
                action = new SauvegarderProfilAction();
                serialisation = new StatutSerialisation();
                break;
                
            case "obtenirUtilisateurCourant":
                action = new ObtenirUtilisateurSessionAction();
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
                
            case "archiverConsultation":
                action = new ArchiverConsultationAction();
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
                action = new DemanderConsultationAction();
                serialisation = new StatutSerialisation();
                break;
                
            case "obtenirConsultationAttribuee":
                action = new ObtenirConsultationAttribueeAction();
                serialisation = new ConsultationSerialisation();
                break;
            
            case "obtenirEtatConsultationSession":
                action = new ObtenirEtatConsultationSessionAction();
                serialisation = new EtatConsultationSerialisation();
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
