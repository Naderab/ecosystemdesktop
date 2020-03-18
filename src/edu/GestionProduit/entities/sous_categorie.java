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
public class sous_categorie {
    private int id ;
    private String lib ;

    public sous_categorie(int id, String lib) {
        this.id = id;
        this.lib = lib;
    }

    public sous_categorie() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    @Override
    public String toString() {
        return lib;
    }
    
}
