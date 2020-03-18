/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;



/**
 *
 * @author MEGAJAS
 */
public class Produit {
    
    private int id;
    private String titre ;
    private String description;
    private String categorie;
    private String sous_categorie;
    private int prix;
    private String image;
    private int id_panier;
    private int quantite ;
    private int id_commande;
    

    public Produit(String titre, int prix, int quantite) {
        this.titre = titre;
        this.prix = prix;
        this.quantite = quantite;
    }

    public Produit(int id, int prix, int quantite) {
        this.id = id;
        this.prix = prix;
        this.quantite = quantite;
    }

    
    
    public Produit(int id, String titre, String description, String categorie, String sous_categorie, int prix, String image, int id_panier, int quantite, int id_commande) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.categorie = categorie;
        this.sous_categorie = sous_categorie;
        this.prix = prix;
        this.image = image;
        this.id_panier = id_panier;
        this.quantite = quantite;
        this.id_commande = id_commande;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSous_categorie() {
        return sous_categorie;
    }

    public void setSous_categorie(String sous_categorie) {
        this.sous_categorie = sous_categorie;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getId_panier() {
        return id_panier;
    }

    public void setId_panier(int id_panier) {
        this.id_panier = id_panier;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }
   
    
    
    
    
    
    
}
