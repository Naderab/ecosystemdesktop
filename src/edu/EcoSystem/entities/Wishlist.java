/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

/**
 *
 * @author Nader
 */
public class Wishlist {
    private int id_wishlist;  
    private int idEvent;
    private int id_user;

    public int getId_wishlist() {
        return id_wishlist;
    }

    public void setId_wishlist(int id_wishlist) {
        this.id_wishlist = id_wishlist;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Wishlist(int idEvent, int id_user) {
        this.idEvent = idEvent;
        this.id_user = id_user;
    }

    public Wishlist() {
    }
    
    
}
