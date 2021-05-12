package com.project.dasi.serialisations;

import java.util.Calendar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Client;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Spirite;
import com.projet.dasi.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ObtenirHistoriqueClientReqEmployeSerialisation extends Serialisation {
    
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        // Initialiser le container
        JsonObject container = new JsonObject();
        
        // Récupérer les attributs de la session
        Utilisateur utilisateur = (Utilisateur)session.getAttribute("utilisateur");
        
        // Populer le container
        container.addProperty("utilisateur", utilisateur != null);
        
        List<Consultation> historique = (List<Consultation>)request.getAttribute("historique");
       
        JsonArray historiqueJson = new JsonArray();
        for(Consultation consultation : historique){
            
            Medium medium = consultation.getMedium();
            JsonObject Consultation = new JsonObject();
            
            Date date = consultation.getDateDebut();
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String stringDate = simpleDateFormat.format(date);
            Consultation.addProperty("employe",consultation.getEmploye().getPrenom()+" "+consultation.getEmploye().getNom());
            Consultation.addProperty("commentaire",consultation.getCommentaire());
            Consultation.addProperty("denomination", medium.getDenomination());
            Consultation.addProperty("date",stringDate);
            
            if(medium instanceof Astrologue){
                Consultation.addProperty("typeMedium", "Astrologue");
            }
            else if(medium instanceof Spirite){
                Consultation.addProperty("typeMedium", "Spirite");
            }
            else if(medium instanceof Cartomancien){
                Consultation.addProperty("typeMedium", "Cartomancien");
            }
            historiqueJson.add(Consultation);
        }
        
        container.add("historique", historiqueJson);
        
        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();
 
    }
    
}
