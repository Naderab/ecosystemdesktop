/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

import java.sql.Date;
import java.time.LocalDate;

/**
 *
 * @author MEGAJAS
 */
public class Panier {
    
    private int id;
    private String prix;
    private String nom;
    private LocalDate date;
    private int id_client;
    private int quantite;
    private int id_produit;

    public Panier(String prix, int quantite) {
        this.prix = prix;
        this.quantite = quantite;
    }

    public Panier(int id, String prix, String nom, LocalDate date, int id_client) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.date = date;
        this.id_client = id_client;
    }

     public Panier( String prix, LocalDate date, String nom, int id_client) {
       
        this.prix = prix;
        this.date = date;
        this.nom = nom;
        this.id_client = id_client;
    }

    public Panier() {
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Panier(String prix, String nom, LocalDate date, int id_client, int quantite) {
        this.prix = prix;
        this.nom = nom;
        this.date = date;
        this.id_client = id_client;
        this.quantite = quantite;
    }

   
    
    public Panier(String nom, String prix ,int quantite,int id) {
        this.nom = nom;
        this.prix = prix;
        this.quantite=quantite;
        this.id=id;
    }
    
    public Panier(int id ,String nom, String prix ,int quantite) {
        this.nom = nom;
        this.prix = prix;
        this.quantite=quantite;
        this.id=id;
    }
 
     

    public Panier(int id ,String prix,LocalDate date, String nom,  int id_client ) {
        this.prix = prix;
        this.nom = nom;
        this.date = date;
        this.id_client = id_client;
        this.id=id;
        
    }

    public Panier(int id, String prix, String nom) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
    }

    public Panier(int id_produit,String prix,LocalDate date, String nom,  int id_client ,int quantite) {
        this.id_produit=id_produit;
        this.prix = prix;
        this.nom = nom;
        this.date = date;
        this.id_client = id_client;
        this.quantite=quantite;
    }
    public Panier(String prix,LocalDate date, String nom,  int id_client ,int quantite) {
       
        this.prix = prix;
        this.nom = nom;
        this.date = date;
        this.id_client = id_client;
        this.quantite=quantite;
    }
     

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
    
    
    

    public String getPrix() {
        return prix;
    }

    public String getNom() {
        return nom;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getId_client() {
        return id_client;
    }

    public void setPrix(String prix) {
        this.prix = prix;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    
    
    
    
    

    
}
