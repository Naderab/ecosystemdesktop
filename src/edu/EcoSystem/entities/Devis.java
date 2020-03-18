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
public class Devis {

    private int id;
    private String libelle;
    private String description;
    private String message;
    private Date datedevis;
    private Date datevalidite;
    private double prixunit;
    private int qte;
    private double totalHT;
    private int TVA;
    private double totalTTC;
    private String confirmer;
    private int idoffre;

    public Devis() {
    }

    public Devis(int id, String libelle, String description, String message, Date datedevis, Date datevalidite, double prixunit, int qte, double totalHT, int TVA, double totalTTC, String confirmer, int idoffre) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.message = message;
        this.datedevis = datedevis;
        this.datevalidite = datevalidite;
        this.prixunit = prixunit;
        this.qte = qte;
        this.totalHT = totalHT;
        this.TVA = TVA;
        this.totalTTC = totalTTC;
        this.confirmer = confirmer;
        this.idoffre = idoffre;
    }

    public Devis(String libelle, String description, String message, Date datedevis, Date datevalidite, double prixunit, int qte, double totalHT, int TVA, double totalTTC, String confirmer, int idoffre) {
        this.libelle = libelle;
        this.description = description;
        this.message = message;
        this.datedevis = datedevis;
        this.datevalidite = datevalidite;
        this.prixunit = prixunit;
        this.qte = qte;
        this.totalHT = totalHT;
        this.TVA = TVA;
        this.totalTTC = totalTTC;
        this.confirmer = confirmer;
        this.idoffre = idoffre;
    }

    public Devis(String libelle, String description, String message, Date datedevis, Date datevalidite, double prixunit, int qte, double totalHT, int TVA, double totalTTC, String confirmer) {
        this.libelle = libelle;
        this.description = description;
        this.message = message;
        this.datedevis = datedevis;
        this.datevalidite = datevalidite;
        this.prixunit = prixunit;
        this.qte = qte;
        this.totalHT = totalHT;
        this.TVA = TVA;
        this.totalTTC = totalTTC;
        this.confirmer = confirmer;
    }

    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDatedevis() {
        return datedevis;
    }

    public void setDatedevis(Date datedevis) {
        this.datedevis = datedevis;
    }

    public Date getDatevalidite() {
        return datevalidite;
    }

    public void setDatevalidite(Date datevalidite) {
        this.datevalidite = datevalidite;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrixunit() {
        return prixunit;
    }

    public void setPrixunit(double prixunit) {
        this.prixunit = prixunit;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getTotalHT() {
        return totalHT;
    }

    public void setTotalHT(double totalHT) {
        this.totalHT = totalHT;
    }

    public int getTVA() {
        return TVA;
    }

    public void setTVA(int TVA) {
        this.TVA = TVA;
    }

    public double getTotalTTC() {
        return totalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        this.totalTTC = totalTTC;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getConfirmer() {
        return confirmer;
    }

    public void setConfirmer(String confirmer) {
        this.confirmer = confirmer;
    }

    public int getIdoffre() {
        return idoffre;
    }

    public void setIdoffre(int idoffre) {
        this.idoffre = idoffre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.id;
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
        final Devis other = (Devis) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Devis{" + "id=" + id + ", datedevis=" + datedevis + ", datevalidite=" + datevalidite + ", description=" + description + ", libelle=" + libelle + ", prixunit=" + prixunit + ", qte=" + qte + ", totalHT=" + totalHT + ", TVA=" + TVA + ", totalTTC=" + totalTTC + ", message=" + message + ", confirmer=" + confirmer + ", idoffre=" + idoffre + '}';
    }

}
