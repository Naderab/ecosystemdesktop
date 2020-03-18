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
public class wishlist_produit {
    private int id;
    private int id_user;
    private int id_produit;

    public wishlist_produit(int id, int id_user, int id_produit) {
        this.id = id;
        this.id_user = id_user;
        this.id_produit = id_produit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_produit() {
        return id_produit;
    }

    public void setId_produit(int id_produit) {
        this.id_produit = id_produit;
    }

    @Override
    public String toString() {
        return "wishlist_produit{" + "id=" + id + ", id_user=" + id_user + ", id_produit=" + id_produit + '}';
    }
    
}
