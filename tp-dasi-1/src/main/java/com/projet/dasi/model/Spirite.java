package com.projet.dasi.model;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("Spirite")
public class Spirite extends Medium implements Serializable {

    /* Attributes */
    private static final long serialVersionUID = 1L;
    
    private String support;
    
    /* Constructors */
    public Spirite(String denomination, String presentation, String support) {
        super(denomination, presentation);
        this.support = support;
    }
    public Spirite() {
    }
    
    /* Getters / Setters */
    
    
    /* Overrided methods */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Spirite)) {
            return false;
        }
        Spirite other = (Spirite) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.toString() + ", support=" + support;
    }

}
