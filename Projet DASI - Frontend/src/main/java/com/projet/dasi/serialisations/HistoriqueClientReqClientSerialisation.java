package com.projet.dasi.serialisations;

import java.util.Calendar;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.projet.dasi.model.Astrologue;
import com.projet.dasi.model.Cartomancien;
import com.projet.dasi.model.Consultation;
import com.projet.dasi.model.Medium;
import com.projet.dasi.model.Spirite;
import com.projet.dasi.model.Utilisateur;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class HistoriqueClientReqClientSerialisation extends Serialisation {

    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        HttpSession session = request.getSession();
        JsonObject container = new JsonObject();

        Utilisateur utilisateur = (Utilisateur) session.getAttribute("utilisateur");

        container.addProperty("utilisateur", utilisateur != null);

        List<Consultation> historique = (List<Consultation>) request.getAttribute("historique");
        JsonArray historiqueJson = new JsonArray();
        
        for (Consultation consultation : historique) {

            Medium medium = consultation.getMedium();
            JsonObject consultationData = new JsonObject();

            Date date = consultation.getDateDebut();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            
            // Récupération du jour du mois
            int jourDuMois = calendar.get(Calendar.DAY_OF_MONTH);

            // Récupération du jour de la semaine
            int jourDeLaSemaine = calendar.get(Calendar.DAY_OF_WEEK);
            String nomJour = null;
            switch (jourDeLaSemaine) {
                case 1:
                    nomJour = "Dimanche";
                    break;
                case 2:
                    nomJour = "Lundi";
                    break;
                case 3:
                    nomJour = "Mardi";
                    break;
                case 4:
                    nomJour = "Mercredi";
                    break;
                case 5:
                    nomJour = "Jeudi";
                    break;
                case 6:
                    nomJour = "Vendredi";
                    break;
                case 7:
                    nomJour = "Samedi";

            }
            // Récupération du mois
            int mois = calendar.get(Calendar.MONTH);
            String nomMois = null;
            switch (mois) {
                case 0:
                    nomMois = "Janvier";
                    break;
                case 1:
                    nomMois = "Février";
                    break;
                case 2:
                    nomMois = "Mars";
                    break;
                case 3:
                    nomMois = "Avril";
                    break;
                case 4:
                    nomMois = "Mai";
                    break;
                case 5:
                    nomMois = "Juin";
                    break;
                case 6:
                    nomMois = "Juillet";
                    break;
                case 7:
                    nomMois = "Août";
                    break;
                case 8:
                    nomMois = "Septembre";
                    break;
                case 9:
                    nomMois = "Octobre";
                    break;
                case 10:
                    nomMois = "Novembre";
                    break;
                case 11:
                    nomMois = "Decembre";
                    break;
            }
            
            // Récupération de l'année 
            int annee = calendar.get(Calendar.YEAR);
            
            consultationData.addProperty("date", nomJour + " " + jourDuMois + " " + nomMois + " " + annee);
            
            JsonObject mediumData = new JsonObject();
            mediumData.addProperty("denomination", medium.getDenomination());
            mediumData.addProperty("genre", medium.getGenre().toString());
            mediumData.addProperty("id", medium.getId());
            mediumData.addProperty("presentation", medium.getPresentation());
            if (medium instanceof Astrologue) {
                mediumData.addProperty("formation", ((Astrologue)medium).getFormation());
                mediumData.addProperty("promotion", ((Astrologue)medium).getPromotion());
                mediumData.addProperty("type", "Astrologue");
            } else if (medium instanceof Spirite) {
                mediumData.addProperty("support", ((Spirite)medium).getSupport());
                mediumData.addProperty("type", "Spirite");
            } else if (medium instanceof Cartomancien) {
                mediumData.addProperty("type", "Cartomancien");
            }
            consultationData.add("medium", mediumData);
            
            historiqueJson.add(consultationData);
            
        }

        container.add("historique", historiqueJson);

        // L'écrire sur le flux de sortie
        PrintWriter out = this.getWriter(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
        gson.toJson(container, out);
        out.close();

    }

}
