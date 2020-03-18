/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.entities.Offre;
import edu.EcoSystem.tools.MyConnexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ramzi
 */
public class OffreCRUD {

    private static OffreCRUD instance;
    private Statement st;
    private ResultSet rs;

    public OffreCRUD() {

        MyConnexion cs = MyConnexion.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static OffreCRUD getInstance() {
        if (instance == null) {
            instance = new OffreCRUD();
        }
        return instance;
    }

    public void ajouterOffre(Offre o) {
        try {

            String req = "INSERT  INTO offre (titre,type,description,dateoffre,datevalidite,etat,demande,archiver) VALUES"
                    + "('" + o.getTitre() + "','" + o.getType() + "','" + o.getDescription() + "','" + o.getDateoffre() + "','" + o.getDatevalidite() + "','" + o.getEtat() + "','" + o.getDemande() + "','" + o.getArchiver() + "')";

            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Offre ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    //LES OFFRES 

    public List<Offre> afficherOffre() {
        List<Offre> myList = new ArrayList<>();
        try {
            String req = "SELECT * FROM offre WHERE demande='non' AND etat='non' AND archiver='non'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Offre o = new Offre();
                o.setId(rs.getInt(1));
                o.setTitre(rs.getString(2));
                o.setType(rs.getString(3));
                o.setDescription(rs.getString(4));
                o.setDateoffre(rs.getDate(5));
                o.setDatevalidite(rs.getDate(6));
                o.setEtat(rs.getString(7));
                o.setDemande(rs.getString(8));
                o.setArchiver(rs.getString(9));

                myList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }

    //LES Demandes DEVIS
    public List<Offre> afficherOffreDemander() {
        List<Offre> myList = new ArrayList<>();
        try {
            String req = "SELECT * FROM offre WHERE demande='oui' AND etat='non' AND archiver='non'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Offre o = new Offre();
                o.setId(rs.getInt(1));
                o.setTitre(rs.getString(2));
                o.setType(rs.getString(3));
                o.setDescription(rs.getString(4));
                o.setDateoffre(rs.getDate(5));
                o.setDatevalidite(rs.getDate(6));
                o.setEtat(rs.getString(7));
                o.setDemande(rs.getString(8));
                o.setArchiver(rs.getString(9));

                myList.add(o);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }

    public void modifierOffre(Offre o, int id) {
        try {
            String req = "UPDATE offre SET titre=?,type=?,description=?,dateoffre=?,datevalidite=? WHERE id=?";
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(req);
            pst.setString(1, o.getTitre());
            pst.setString(2, o.getType());
            pst.setString(3, o.getDescription());
            pst.setDate(4, (Date) o.getDateoffre());
            pst.setDate(5, (Date) o.getDatevalidite());
            pst.setInt(6, id);
            pst.executeUpdate();
            System.out.println("Offre modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerOffre(int id) {
        try {
            String req = "DELETE FROM offre WHERE id=?";
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Offre supprime");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void demande(int id) {
        try {
            String req = "UPDATE offre SET demande=? WHERE id=?";
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(req);
            pst.setString(1,"oui");
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("demande envoyé");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
     public void Annulerdemande(int id) {
        try {
            String req = "UPDATE offre SET demande=? WHERE id=?";
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(req);
            pst.setString(1,"non");
            pst.setInt(2, id);
            pst.executeUpdate();
            System.out.println("demande annuler");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
