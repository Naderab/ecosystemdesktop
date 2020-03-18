/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

import java.sql.Date;

/**
 *
 * @author Nader
 */
public class Review {
    private int idCmt;
    private String cmt;
    private Date date;
    private Evenement id_event;
    private int iduser;

    public Review() {
    }

    public Review(String cmt, Date date, Evenement id_event) {
        this.cmt = cmt;
        this.date = date;
        this.id_event = id_event;
    }

    public Review(String cmt, Date date, Evenement id_event, int iduser) {
        this.cmt = cmt;
        this.date = date;
        this.id_event = id_event;
        this.iduser = iduser;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }
    

    public int getIdCmt() {
        return idCmt;
    }

    public void setIdCmt(int idCmt) {
        this.idCmt = idCmt;
    }
   

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Evenement getId_event() {
        return id_event;
    }

    public void setId_event(Evenement id_event) {
        this.id_event = id_event;
    }
    
    
}