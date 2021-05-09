package com.projet.dasi.servlet;

import com.project.dasi.serialisations.ConnexionSerialisation;
import com.project.dasi.serialisations.InscriptionSerialisation;
import com.project.dasi.serialisations.ObtenirConsultationAttribueeSerialisation;
import com.project.dasi.serialisations.Serialisation;
import com.projet.dasi.actions.Action;
import com.projet.dasi.actions.ConnexionAction;
import com.projet.dasi.actions.InscriptionAction;
import com.projet.dasi.actions.ObtenirConsultationAttribueeAction;
import com.projet.dasi.dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.projet.dasi.service.ServicesApplication;

@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String typeRequete = request.getParameter("todo");
        
        Action action = null;
        Serialisation serialisation = null;
           
        switch (typeRequete) {
            
            case "connexion":
                action = new ConnexionAction();
                serialisation = new ConnexionSerialisation();
                break;
                
            case "inscription":
                action = new InscriptionAction();
                serialisation = new InscriptionSerialisation();
                break;
                
            case "obtenirConsultationAttribuee":
                action = new ObtenirConsultationAttribueeAction();
                serialisation = new ObtenirConsultationAttribueeSerialisation();
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

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

}
