/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.projet.dasi.presentation;

import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Genre;
import com.projet.dasi.service.ServiceApplication;

/**
 *
 * @author creep
 */
public class PresentationConsultation {
    
    public static void requeteDemandeConsultation() {
        
        Cartomancien medium = new Cartomancien("Irma", "WOW", Genre.Femme);
        Client client = new Client();
        ServiceApplication sa = new ServiceApplication();
        
        Consultation c = sa.demanderConsultation(client, medium);
        System.out.println(c.toString());
        
    }
    
}
