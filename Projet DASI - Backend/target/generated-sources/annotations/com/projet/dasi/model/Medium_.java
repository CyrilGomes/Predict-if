package com.projet.dasi.model;

import com.projet.dasi.model.Genre;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.7.v20200504-rNA", date="2021-05-04T14:51:24")
@StaticMetamodel(Medium.class)
public class Medium_ { 

    public static volatile SingularAttribute<Medium, String> presentation;
    public static volatile SingularAttribute<Medium, String> typeMedium;
    public static volatile SingularAttribute<Medium, Genre> genre;
    public static volatile SingularAttribute<Medium, Long> id;
    public static volatile SingularAttribute<Medium, String> denomination;

}