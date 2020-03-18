/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.entities;

import java.util.Objects;

/**
 *
 * @author Abir
 */
public class Avis {
 
    private int id;
    private int id_user;
    private String monAvis;

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
    
    public Avis(){}

    public Avis(int id_user) {
        this.id_user = id_user;
    }

    public Avis(int id, String monAvis) {
        this.id = id;
        this.monAvis = monAvis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonAvis() {
        return monAvis;
    }

    public void setMonAvis(String monAvis) {
        this.monAvis = monAvis;
    }

    @Override
    public String toString() {
        return "Avis{" + "id=" + id + ", monAvis=" + monAvis + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Avis other = (Avis) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.monAvis, other.monAvis)) {
            return false;
        }
        return true;
    }
    
    
}
