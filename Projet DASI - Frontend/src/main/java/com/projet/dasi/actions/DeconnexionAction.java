/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
/**
 *
 * @author picka
 */
public class DeconnexionAction extends Action {
    
    @Override
    public void executer(HttpServletRequest request) {
        // Récupération de la session
        HttpSession session = request.getSession();
        
        // Suppression de la session
        session.invalidate();
    }
}
