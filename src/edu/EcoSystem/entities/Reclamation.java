/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.util.Date;

/**
 *
 * @author zied
 */
public class Reclamation {
    private int rec_id;
    private String rec_lib;
    private int rec_writer;
    private int rec_user;
    private Date rec_date;
    private String rec_text;
    private int etat;

    public Reclamation() {
    }

    public Reclamation(int rec_id, String rec_lib, int rec_writer, int rec_user, Date rec_date, String rec_text, int etat) {
        this.rec_id = rec_id;
        this.rec_lib = rec_lib;
        this.rec_writer = rec_writer;
        this.rec_user = rec_user;
        this.rec_date = rec_date;
        this.rec_text = rec_text;
        this.etat = etat;
    }

    public Reclamation(int rec_id, String rec_lib, int rec_writer, String rec_text, int etat) {
        this.rec_id = rec_id;
        this.rec_lib = rec_lib;
        this.rec_writer = rec_writer;
        this.rec_text = rec_text;
        this.etat = etat;
    }

    
    
    public Reclamation(String rec_lib, int rec_user, String rec_text) {
        this.rec_lib = rec_lib;
        this.rec_user = rec_user;
        this.rec_text = rec_text;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "rec_id=" + rec_id + ", rec_lib=" + rec_lib + ", rec_writer=" + rec_writer + ", rec_user=" + rec_user + ", rec_date=" + rec_date + ", rec_text=" + rec_text + ", etat=" + etat + '}';
    }
    
    
    

    public int getRec_id() {
        return rec_id;
    }

    public void setRec_id(int rec_id) {
        this.rec_id = rec_id;
    }

    public String getRec_lib() {
        return rec_lib;
    }

    public void setRec_lib(String rec_lib) {
        this.rec_lib = rec_lib;
    }

    public int getRec_writer() {
        return rec_writer;
    }

    public void setRec_writer(int rec_writer) {
        this.rec_writer = rec_writer;
    }

    public int getRec_user() {
        return rec_user;
    }

    public void setRec_user(int rec_user) {
        this.rec_user = rec_user;
    }

    public Date getRec_date() {
        return rec_date;
    }

    public void setRec_date(Date rec_date) {
        this.rec_date = rec_date;
    }

    public String getRec_text() {
        return rec_text;
    }

    public void setRec_text(String rec_text) {
        this.rec_text = rec_text;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    
    
 
   
    
    
}
