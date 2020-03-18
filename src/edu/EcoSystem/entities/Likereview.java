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
public class Likereview {
    private int id;
    private Review idCmt;
    private int iduser;

    public Likereview() {
    }

    public Likereview(int id, Review idCmt, int iduser) {
        this.id = id;
        this.idCmt = idCmt;
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

   

    public Review getIdCmt() {
        return idCmt;
    }

    public void setIdCmt(Review idCmt) {
        this.idCmt = idCmt;
    }

   

   

    public Likereview(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
