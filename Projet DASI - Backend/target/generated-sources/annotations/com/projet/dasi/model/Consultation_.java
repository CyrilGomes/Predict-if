package com.projet.dasi.model;

import com.projet.dasi.model.Client;
import com.projet.dasi.model.Employe;
import com.projet.dasi.model.Etat;
import com.projet.dasi.model.Medium;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-04T14:51:24")
@StaticMetamodel(Consultation.class)
public class Consultation_ { 

    public static volatile SingularAttribute<Consultation, Date> dateDebut;
    public static volatile SingularAttribute<Consultation, Employe> employe;
    public static volatile SingularAttribute<Consultation, Client> client;
    public static volatile SingularAttribute<Consultation, Long> id;
    public static volatile SingularAttribute<Consultation, Date> dateFin;
    public static volatile SingularAttribute<Consultation, Medium> medium;
    public static volatile SingularAttribute<Consultation, Etat> etat;
    public static volatile SingularAttribute<Consultation, String> commentaire;

}