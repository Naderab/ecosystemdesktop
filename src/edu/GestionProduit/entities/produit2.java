/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.entities;

/**
 *
 * @author Abir
 */
public class produit2 {

    private int id ;
    private int id_categorie ;
    private int id_user ;
    private String titre ;
    private String description ;
    private int publie ;
    private int prix ;
    private String image ;
    private int rating ;
    private int id_SousCategorie ;

    
    public produit2() {
    }
    
    
    public produit2(String titre, String description) {
        this.titre = titre;
        this.description = description;
    }

    public produit2(String titre, String description, int prix) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
    }

    public produit2(int id, int id_categorie, int id_user, String titre, String description, int prix, String image, int rating, int id_SousCategorie) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.image = image;
        this.rating = rating;
        this.id_SousCategorie = id_SousCategorie;
    }
    
    
    

    public produit2(int id, int id_categorie, int id_user, String titre, String description, int publie, int prix, String image, int rating, int id_SousCategorie) {
        this.id = id;
        this.id_categorie = id_categorie;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.publie = publie;
        this.prix = prix;
        this.image = image;
        this.rating = rating;
        this.id_SousCategorie = id_SousCategorie;
    }

    public produit2(int id_categorie, int id_user, String titre, String description, int publie, int prix, String image, int rating, int id_SousCategorie) {
        this.id_categorie = id_categorie;
        this.id_user = id_user;
        this.titre = titre;
        this.description = description;
        this.publie = publie;
        this.prix = prix;
        this.image = image;
        this.rating = rating;
        this.id_SousCategorie = id_SousCategorie;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
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

    public int getPublie() {
        return publie;
    }

    public void setPublie(int publie) {
        this.publie = publie;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getId_SousCategorie() {
        return id_SousCategorie;
    }

    public void setId_SousCategorie(int id_SousCategorie) {
        this.id_SousCategorie = id_SousCategorie;
    }

    @Override
    public String toString() {
        return "produit2{" + "id=" + id + ", id_categorie=" + id_categorie + ", id_user=" + id_user + ", titre=" + titre + ", description=" + description + ", publie=" + publie + ", prix=" + prix + ", image=" + image + ", rating=" + rating + ", id_SousCategorie=" + id_SousCategorie + '}';
    }
    
}
