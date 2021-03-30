/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi;

import com.projet.dasi.dao.JpaUtil;
import com.projet.dasi.presentation.PresentationClient;
import com.projet.dasi.presentation.PresentationUtilisateur;

/**
 *
 * @author creep
 */
public class Main {
    public static void main(String[] args){
        JpaUtil.init();
        //PresentationClient.requeteCreationClient();
        PresentationUtilisateur.requeteAuthentification();
        JpaUtil.destroy();
    }
}
