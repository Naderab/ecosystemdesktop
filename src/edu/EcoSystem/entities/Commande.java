/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

import java.time.LocalDate;

import java.sql.Date;

/**
 *
 * @author MEGAJAS
 */
public class Commande {
    
    private int id;
    private int quantite;
    private int prix_total;
    private int prix_commande;
    private int id_client;
    private LocalDate dateCommande;
    private String etat;
    private int id_panier;

    public Commande(int id, int quantite, int prix_total, int prix_commande, int id_client, LocalDate dateCommande, String etat, int id_panier) {
        this.id = id;
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.prix_commande = prix_commande;
        this.id_client = id_client;
        this.dateCommande = dateCommande;
        this.etat = etat;
        this.id_panier = id_panier;
    }

    public Commande(int quantite, int prix_total, int prix_commande, int id_client, LocalDate dateCommande, String etat, int id_panier) {
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.prix_commande = prix_commande;
        this.id_client = id_client;
        this.dateCommande = dateCommande;
        this.etat = etat;
        this.id_panier = id_panier;
    }
    
     public Commande(int quantite, int prix_total, int prix_commande, int id_client, LocalDate dateCommande, String etat) {
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.prix_commande = prix_commande;
        this.id_client = id_client;
        this.dateCommande = dateCommande;
        this.etat = etat;
    }

      public Commande(int quantite, int prix_total, int prix_commande,  LocalDate dateCommande, String etat,int id) {
        this.quantite = quantite;
        this.prix_total = prix_total;
        this.prix_commande = prix_commande;
        this.dateCommande = dateCommande;
        this.etat = etat;
        this.id=id;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getPrix_total() {
        return prix_total;
    }

    public void setPrix_total(int prix_total) {
        this.prix_total = prix_total;
    }

    public int getPrix_commande() {
        return prix_commande;
    }

    public void setPrix_commande(int prix_commande) {
        this.prix_commande = prix_commande;
    }

    public int getId_client() {
        return id_client;
    }

    public void setId_client(int id_client) {
        this.id_client = id_client;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(LocalDate dateCommande) {
        this.dateCommande = dateCommande;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }
    
}
