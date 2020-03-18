/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.sql.Date;

/**
 *
 * @author Nader
 */
public class Evenement extends RecursiveTreeObject<Evenement>{
    private int id_event;
    private String nom;
    private Date dateDebut;
    private Date dateFin;
    private String description ;
    private String adresse ;
    private int minParticipants;
    private int maxParticipants;
    private String categorie ;
    private float prix ;
    private int publie ;
    private int nombreTickets;
    private int nombreVu;
    private int rating;
    private String image;
    private int id_user;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public int getPublie() {
        return publie;
    }

    public void setPublie(int publie) {
        this.publie = publie;
    }

    public int getNombreTickets() {
        return nombreTickets;
    }

    public void setNombreTickets(int nombreTickets) {
        this.nombreTickets = nombreTickets;
    }

    public int getNombreVu() {
        return nombreVu;
    }

    public void setNombreVu(int nombreVu) {
        this.nombreVu = nombreVu;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Evenement(String nom, Date dateDebut, Date dateFin, String description, String adresse, int minParticipants, int maxParticipants, String categorie, float prix, String image,int idUser) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.description = description;
        this.adresse = adresse;
        this.minParticipants = minParticipants;
        this.maxParticipants = maxParticipants;
        this.categorie = categorie;
        this.prix = prix;
        this.image = image;
        this.nombreVu=0;
        this.nombreTickets=0;
        this.rating=0;
        this.id_user=idUser;
        
        
    }

    public Evenement() {
        this.nombreVu=0;
        this.nombreTickets=0;
        this.publie=0;
        this.rating=0;
    }
    
    
    
    
}
