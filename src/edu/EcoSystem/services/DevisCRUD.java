/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.entities.Devis;
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

/**
 *
 * @author ramzi
 */
public class DevisCRUD {

    private static DevisCRUD instance;
    private Statement st;
    private ResultSet rs;

    public DevisCRUD() {
        MyConnexion cs = MyConnexion.getInstance();
        try {
            st = cs.getCnx().createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DevisCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static DevisCRUD getInstance() {
        if (instance == null) {
            instance = new DevisCRUD();
        }
        return instance;
    }

    public void ajouterDevis(Devis d) {
        try {

            String req = "INSERT  INTO devis (datedevis,datevalidite,description,libelle,prixunit,qte,totalHT,tva,totalTTC,message,confirmer,offre_id) VALUES"
                    + "('" + d.getDatedevis() + "','" + d.getDatevalidite() + "','" + d.getDescription() + "','" + d.getLibelle() + "','" + d.getPrixunit() + "','" + d.getQte() + "','" + d.getTotalHT() + "','" + d.getTVA() + "','" + d.getTotalTTC() + "','" + d.getMessage() + "','" + d.getConfirmer() + "','" + d.getIdoffre() + "')";

            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            st.executeUpdate(req);
            System.out.println("Devis ajouté");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Devis> afficherDevis() {
        List<Devis> myList = new ArrayList<>();
        try {
            String req = "SELECT * FROM devis WHERE confirmer='non'";
            Statement st = MyConnexion.getInstance().getCnx().createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Devis d = new Devis();
                d.setId(rs.getInt(1));
                d.setDatedevis(rs.getDate(2));
                d.setDatevalidite(rs.getDate(3));
                d.setDescription(rs.getString(4));
                d.setLibelle(rs.getString(5));
                d.setPrixunit(rs.getDouble(6));
                d.setQte(rs.getInt(7));
                d.setTotalHT(rs.getDouble(8));
                d.setTVA(rs.getInt(9));
                d.setTotalTTC(rs.getDouble(10));
                d.setMessage(rs.getString(11));
                d.setConfirmer(rs.getString(12));

                myList.add(d);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Logger.getLogger(OffreCRUD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return myList;
    }

    public void modifierDevis(Devis d, int id) {
        try {
            String req = "UPDATE devis SET datevalidite=?,libelle=?,prixunit=?,qte=?,totalHT=?,TVA=?,totalTTC=?,message=? WHERE id=?";
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(req);
            pst.setDate(1, (Date) d.getDatevalidite());
            pst.setString(2, d.getLibelle());
            pst.setDouble(3, d.getPrixunit());
            pst.setInt(4, d.getQte());
            pst.setDouble(5, d.getTotalHT());
            pst.setInt(6, d.getTVA());
            pst.setDouble(7, d.getTotalTTC());
            pst.setString(8, d.getMessage());

            pst.setInt(9, id);
            pst.executeUpdate();
            System.out.println("Devis modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimerDevis(int id) {
        try {
            String req = "DELETE FROM devis WHERE id=?";
            PreparedStatement pst = MyConnexion.getInstance().getCnx().prepareStatement(req);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Devis supprime");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
