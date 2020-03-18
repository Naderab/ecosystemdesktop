/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abir
 */
public class categorie {

    
        
private int id;
  private String libelle;
  private List<sous_categorie> l;
  private int id_SousCategorie;

    public int getId_SousCategorie() {
        return id_SousCategorie;
    }

    public void setId_SousCategorie(int id_SousCategorie) {
        this.id_SousCategorie = id_SousCategorie;
    }
  

    public categorie() {
    }
  
  

    public categorie(int id, String libelle, List<sous_categorie> l) {
        this.id = id;
        this.libelle = libelle;
        this.l = new ArrayList();
        
    }
  
  

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<sous_categorie> getL() {
        return l;
    }

    public void setL(List<sous_categorie> l) {
        this.l = l;
    }

    @Override
    public String toString() {
        return libelle;
    }
    
    
}
