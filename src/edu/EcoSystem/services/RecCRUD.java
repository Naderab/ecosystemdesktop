/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.entities.CurrentUser;
import edu.EcoSystem.entities.Reclamation;
import edu.EcoSystem.entities.User;
import edu.EcoSystem.touls.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author zied
 */
public class RecCRUD {

    public void ajouterReclamation(Reclamation r) throws SQLException {
        String requete = "INSERT INTO reclamation (lib,writer,user,date,texte,etat ) VALUES ('" + r.getRec_lib() + "','" + r.getRec_writer() + "','" + r.getRec_user() + "','" + r.getRec_date() + "','" + r.getRec_text() + "','" + r.getEtat() + "')";
        Statement stm = MyConnection.getInstance().getCnx().createStatement();
        stm.executeUpdate(requete);
    }

    public List<Reclamation> getAllRec() {

        List<Reclamation> listRec = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setRec_id(rs.getInt(1));
                r.setRec_lib(rs.getString(2));
                r.setRec_writer(rs.getInt(3));
                r.setRec_date(rs.getDate(4));
                r.setRec_text(rs.getString(5));
                r.setEtat(rs.getInt(6));
                r.setRec_user(rs.getInt(7));
                listRec.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listRec;
    }
    
    public List<Reclamation> getAllRecTraitées() {

        List<Reclamation> listRec = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation where etat='"+ 1 +"'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setRec_id(rs.getInt(1));
                r.setRec_lib(rs.getString(2));
                r.setRec_writer(rs.getInt(3));
                r.setRec_date(rs.getDate(4));
                r.setRec_text(rs.getString(5));
                r.setEtat(rs.getInt(6));
                r.setRec_user(rs.getInt(7));
                listRec.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listRec;
    }
    
    public List<Reclamation> getAllRecNonTraitées() {

        List<Reclamation> listRec = new ArrayList<>();
        try {
            String requete = "SELECT * FROM reclamation where etat='"+ 0 +"'";
            Statement st = MyConnection.getInstance().getCnx().createStatement();

            ResultSet rs = st.executeQuery(requete);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setRec_id(rs.getInt(1));
                r.setRec_lib(rs.getString(2));
                r.setRec_writer(rs.getInt(3));
                r.setRec_date(rs.getDate(4));
                r.setRec_text(rs.getString(5));
                r.setEtat(rs.getInt(6));
                r.setRec_user(rs.getInt(7));
                listRec.add(r);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listRec;
    }

    public List<Reclamation> getMyRec() throws SQLException {
        //  CurrentUser.id=18;
        List<Reclamation> reclamations = new ArrayList<>();
        String requete = "SELECT * FROM reclamation Where writer=" + CurrentUser.id;
        Statement stm = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = stm.executeQuery(requete);
        while (rs.next()) {
            Reclamation r = new Reclamation();
            r.setRec_id(rs.getInt(1));
            r.setRec_lib(rs.getString(2));
            r.setRec_writer(rs.getInt(3));
            r.setRec_date(rs.getDate(4));
            r.setRec_text(rs.getString(5));
            r.setEtat(rs.getInt(6));
            r.setRec_user(rs.getInt(7));

            reclamations.add(r);
        }
        return reclamations;
    }

    public void modifierEtatRec(int id, int etat) throws SQLException {
        String requete = "UPDATE reclamation SET etat='" + etat + "'" + "WHERE id='" + id + "'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }

    public void supprimerRec(int id) {
        try {
            String requete = "DELETE FROM reclamation WHERE id=?";
            PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public int nbreRec(int id) throws SQLException {
        int nb = 0;
        String requete = "SELECT COUNT(*) AS total FROM reclamation WHERE user = '" + id + "'";
        Statement stm = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = stm.executeQuery(requete);
        if (rs.next())  {
            nb = rs.getInt("total");

        }
        return nb;

    }

}
