/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.entities.Evenement;
import edu.EcoSystem.entities.Participation;
import edu.EcoSystem.tools.MyConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Nader
 */
public class EventCRUD {

    Connection connection = null;

    public EventCRUD() {
        connection = MyConnection.getInstance().getCnx();
    }

    public void addEvent(Evenement event) throws IOException {
        try {

            String requete
                    = "INSERT INTO evenement (nom,date_debut,adresse,min_participants,maxParticipants,image,date_fin,categorie,prix,description,id_user,publie,rating,nombrevu,nombretickets) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(requete);
            st.setString(1, event.getNom());
            st.setDate(2, event.getDateDebut());
            st.setString(3, event.getAdresse());
            st.setInt(4, event.getMinParticipants());
            st.setInt(5, event.getMaxParticipants());
            st.setString(6, event.getImage());
            st.setDate(7, event.getDateFin());
            st.setString(8, event.getCategorie());
            st.setFloat(9, event.getPrix());
            st.setString(10, event.getDescription());
            st.setInt(11, event.getId_user());
            st.setInt(12, event.getPublie());
            st.setInt(13, event.getRating());
            st.setInt(14, event.getNombreVu());
            st.setInt(15, event.getNombreTickets());
            st.executeUpdate();
            System.out.println("event ajoutée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }

    }

    public void deleteEvent(int id) throws SQLException {
        String req = "DELETE FROM evenement WHERE id='" + id + "'";
        PreparedStatement ste = connection.prepareStatement(req);
        ste.executeUpdate();
    }

    public void updateEvent(Evenement event, int id) throws SQLException, IOException {
        String req = "UPDATE evenement SET "
                + "nom = ?,"
                + "date_debut = ?,"
                + "adresse = ?,"
                + "min_participants = ?,"
                + "maxParticipants = ?,"
                + "image = ?,"
                + "date_fin = ?,"
                + "categorie = ?,"
                + "prix = ?,"
                + "publie = ?,"
                + "description = ? where id=?";

        PreparedStatement st = connection.prepareStatement(req);

        st.setString(1, event.getNom());
        st.setDate(2, event.getDateDebut());
        st.setString(3, event.getAdresse());
        st.setInt(4, event.getMinParticipants());
        st.setInt(5, event.getMaxParticipants());
        st.setString(6, event.getImage());
        st.setDate(7, event.getDateFin());
        st.setString(8, event.getCategorie());
        st.setFloat(9, event.getPrix());
        st.setInt(10, event.getPublie());
        st.setString(11, event.getDescription());
        st.setInt(12, id);
        st.executeUpdate();
        System.out.println("event modifié");

    }

    public ObservableList<Evenement> displayALLEvent() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public Evenement displayEventByID(int id) {
        Evenement p = new Evenement();
        try {
            String req = "SELECT * FROM evenement where id='" + id + "'";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {

                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return p;
    }

    public ObservableList<Evenement> displayEventPublie() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where date_debut >= SYSDATE() and publie='" + 1 + "'";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> displayEventNonTraités() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where date_debut >= SYSDATE() and publie='" + 0 + "'";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> displayEventOrderByPrixCroissant() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where date_debut >= SYSDATE() and publie='" + 1 + "' ORDER BY prix ASC";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> displayEventOrderByPrixdécroissant() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where date_debut >= SYSDATE() and publie='" + 1 + "' ORDER BY prix DESC";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> displayEventOrderByNombreVu() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where date_debut >= SYSDATE() and publie='" + 1 + "' ORDER BY nombrevu DESC";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> displayEventOrderByRating() {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where date_debut >= SYSDATE() and publie='" + 1 + "' ORDER BY rating";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> displayEventByCategorie(String Categorie) {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where categorie ='" + Categorie + "' AND date_debut >= SYSDATE() AND publie='" + 1 + "'";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public ObservableList<Evenement> displayMyEvent(int idUser) {
        ObservableList<Evenement> myList = FXCollections.observableArrayList();
        try {
            String req = "SELECT * FROM evenement where id_user ='" + idUser + "'";
            Statement pst = connection.createStatement();
            ResultSet rs = pst.executeQuery(req);
            while (rs.next()) {
                Evenement p = new Evenement();
                p.setId_event(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setDateDebut(rs.getDate("date_debut"));
                p.setAdresse(rs.getString("adresse"));
                p.setMinParticipants(rs.getInt("min_participants"));
                p.setMaxParticipants(rs.getInt("maxParticipants"));
                p.setPublie(rs.getInt("publie"));
                p.setImage(rs.getString("image"));
                p.setDateFin(rs.getDate("date_fin"));
                p.setCategorie(rs.getString("categorie"));
                p.setPrix(rs.getFloat("prix"));
                p.setDescription(rs.getString("description"));
                p.setRating(rs.getInt("rating"));
                p.setNombreVu(rs.getInt("nombrevu"));
                p.setNombreTickets(rs.getInt("nombretickets"));
                p.setId_user(rs.getInt("id_user"));

                myList.add(p);
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());

        }
        return myList;
    }

    public void incrementNombreVu(Evenement e) throws SQLException {
        String req = "UPDATE evenement SET nombrevu=? where id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, e.getNombreVu() + 1);
        pre.setInt(2, e.getId_event());
        pre.executeUpdate();
    }

    public void incrementNombreTickets(Evenement e, int nbPlace) throws SQLException {
        String req = "UPDATE evenement SET nombretickets=? where id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, e.getNombreTickets() + nbPlace);
        pre.setInt(2, e.getId_event());
        pre.executeUpdate();
    }

    public void incrementRating(Evenement e, int nombre) throws SQLException {
        String req = "UPDATE evenement SET rating=? where id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, e.getRating() + nombre);
        pre.setInt(2, e.getId_event());
        pre.executeUpdate();
    }

    public void traiterEvent(Evenement e) throws SQLException {
        String req = "UPDATE evenement SET publie=? where id=?";
        PreparedStatement pre = connection.prepareStatement(req);
        pre.setInt(1, 1);
        pre.setInt(2, e.getId_event());
        pre.executeUpdate();
    }

    public List<Evenement> chercher(String s) throws SQLException {
        List<Evenement> event = new ArrayList<>();
        String rq = "select * from evenement where (date_debut >= SYSDATE() and publie='" + 1 + "') and (nom like'" + s + "%' or adresse like'" + s + "%' or categorie like'" + s + "%')";

        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(rq);

        while (rs.next()) {
            Evenement p = new Evenement();
            p.setId_event(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setDateDebut(rs.getDate("date_debut"));
            p.setAdresse(rs.getString("adresse"));
            p.setMinParticipants(rs.getInt("min_participants"));
            p.setMaxParticipants(rs.getInt("maxParticipants"));
            p.setPublie(rs.getInt("publie"));
            p.setImage(rs.getString("image"));
            p.setDateFin(rs.getDate("date_fin"));
            p.setCategorie(rs.getString("categorie"));
            p.setPrix(rs.getFloat("prix"));
            p.setDescription(rs.getString("description"));
            p.setRating(rs.getInt("rating"));
            p.setNombreVu(rs.getInt("nombrevu"));
            p.setNombreTickets(rs.getInt("nombretickets"));
            p.setId_user(rs.getInt("id_user"));

            event.add(p);
        }

        return event;
    }

    public List<Evenement> chercherNonTraités(String s) throws SQLException {
        List<Evenement> event = new ArrayList<>();
        String rq = "select * from evenement where (date_debut >= SYSDATE() and publie='" + 0 + "') and (nom like'" + s + "%' or adresse like'" + s + "%' or categorie like'" + s + "%')";

        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(rq);

        while (rs.next()) {
            Evenement p = new Evenement();
            p.setId_event(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setDateDebut(rs.getDate("date_debut"));
            p.setAdresse(rs.getString("adresse"));
            p.setMinParticipants(rs.getInt("min_participants"));
            p.setMaxParticipants(rs.getInt("maxParticipants"));
            p.setPublie(rs.getInt("publie"));
            p.setImage(rs.getString("image"));
            p.setDateFin(rs.getDate("date_fin"));
            p.setCategorie(rs.getString("categorie"));
            p.setPrix(rs.getFloat("prix"));
            p.setDescription(rs.getString("description"));
            p.setRating(rs.getInt("rating"));
            p.setNombreVu(rs.getInt("nombrevu"));
            p.setNombreTickets(rs.getInt("nombretickets"));
            p.setId_user(rs.getInt("id_user"));

            event.add(p);
        }

        return event;
    }

    public List<Evenement> chercherMyEvent(String s, int idUser) throws SQLException {
        List<Evenement> event = new ArrayList<>();
        String rq = "select * from evenement where id_user ='" + idUser + "' and (nom like'" + s + "%' or adresse like'" + s + "%' or categorie like'" + s + "%')";

        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(rq);

        while (rs.next()) {
            Evenement p = new Evenement();
            p.setId_event(rs.getInt("id"));
            p.setNom(rs.getString("nom"));
            p.setDateDebut(rs.getDate("date_debut"));
            p.setAdresse(rs.getString("adresse"));
            p.setMinParticipants(rs.getInt("min_participants"));
            p.setMaxParticipants(rs.getInt("maxParticipants"));
            p.setPublie(rs.getInt("publie"));
            p.setImage(rs.getString("image"));
            p.setDateFin(rs.getDate("date_fin"));
            p.setCategorie(rs.getString("categorie"));
            p.setPrix(rs.getFloat("prix"));
            p.setDescription(rs.getString("description"));
            p.setRating(rs.getInt("rating"));
            p.setNombreVu(rs.getInt("nombrevu"));
            p.setNombreTickets(rs.getInt("nombretickets"));
            p.setId_user(rs.getInt("id_user"));

            event.add(p);
        }

        return event;
    }

    public String GetUserName(int id) throws SQLException {
        String name = "";
        String reqUser = "SELECT * FROM fos_user where id='" + id + "'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(reqUser);

        while (rs.next()) {
            name = rs.getString("username");
        }

        return name;
    }

}
