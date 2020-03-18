/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

import java.util.Date;

/**
 *
 * @author ramzi
 */
public class Offre {
    private int id;
    private String titre;
    private String type;
    private String description;
    private Date dateoffre;
    private Date datevalidite;
    private String etat;
    private String demande;
    private String archiver;

    public Offre() {
    }

    public Offre(int id, String titre, String type, String description, Date dateoffre, Date datevalidite, String etat, String demande, String archiver) {
        this.id = id;
        this.titre = titre;
        this.type = type;
        this.description = description;
        this.dateoffre = dateoffre;
        this.datevalidite = datevalidite;
        this.etat = etat;
        this.demande = demande;
        this.archiver = archiver;
    }

    public Offre(String titre, String type, String description, Date dateoffre, Date datevalidite, String etat, String demande, String archiver) {
        this.titre = titre;
        this.type = type;
        this.description = description;
        this.dateoffre = dateoffre;
        this.datevalidite = datevalidite;
        this.etat = etat;
        this.demande = demande;
        this.archiver = archiver;
    }

       
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateoffre() {
        return dateoffre;
    }

    public void setDateoffre(Date dateoffre) {
        this.dateoffre = dateoffre;
    }

    public Date getDatevalidite() {
        return datevalidite;
    }

    public void setDatevalidite(Date datevalidite) {
        this.datevalidite = datevalidite;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDemande() {
        return demande;
    }

    public void setDemande(String demande) {
        this.demande = demande;
    }

    public String getArchiver() {
        return archiver;
    }

    public void setArchiver(String archiver) {
        this.archiver = archiver;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Offre other = (Offre) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Offre{" + "id=" + id + ", titre=" + titre + ", type=" + type + ", description=" + description + ", dateoffre=" + dateoffre + ", datevalidite=" + datevalidite + ", etat=" + etat + ", demande=" + demande + ", archiver=" + archiver + '}';
    }
    
    
    
}
