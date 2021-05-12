/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.model.Medium;
import com.projet.dasi.service.ServicesApplication;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author creep
 */
public class ListeMediumAction extends Action {

    @Override
    public void executer(HttpServletRequest request) {
        ServicesApplication serviceApplication = new ServicesApplication();
        List<Medium> listeMediums = serviceApplication.obtenirMediums();
        request.setAttribute("listeMediums", listeMediums);
    }
    
}
