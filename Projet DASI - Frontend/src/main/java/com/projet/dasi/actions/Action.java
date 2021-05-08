package com.projet.dasi.actions;

import com.projet.dasi.service.ServicesApplication;
import javax.servlet.http.HttpServletRequest;

public abstract class Action {

    public abstract void executer(HttpServletRequest request);

}
