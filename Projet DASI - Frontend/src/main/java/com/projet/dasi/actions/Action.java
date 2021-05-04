/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.actions;

import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Cyril
 */
public abstract class Action {

    ServicesApplication service;

    public Action(ServicesApplication service) {
        this.service = service;
    }

    public abstract void execute(HttpServletRequest request);

}
