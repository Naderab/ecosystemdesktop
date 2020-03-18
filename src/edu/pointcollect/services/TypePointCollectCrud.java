/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.services;

/**
 *
 * @author Khouloud
 */
import edu.pointcollect.entities.Markers;
import edu.pointcollect.entities.TypePoint;
import edu.pointcollect.tools.DataSource;
import edu.pointcollect.tools.MyConnection;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TypePointCollectCrud {

    Connection connection = null;

    public TypePointCollectCrud() {
        connection = DataSource.getInstance().getConnection();
    }

    public void ajouterPointCollect(TypePoint p) {
        String req = "INSERT INTO type_point_collect(name, description) VALUES (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(req);
            preparedStatement.setString(1, p.getName());
            preparedStatement.setString(2, p.getDescription());
            preparedStatement.execute();
        } catch (SQLException ex) {
            //Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifierPointCollect(TypePoint p, int id) {
        try {
            String requete = "UPDATE type_point_collect SET name=?,description=? WHERE id=?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setString(1, p.getName());
            pst.setString(2, p.getDescription());
            pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("type_point_collect modifie");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void supprimerPointCollect(int id) {
        try {
            String requete = "DELETE FROM type_point_collect WHERE id=?";
            PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.err.println("type_point_collect supprime");
        } catch (SQLException e) {
            System.out.print(e.getMessage());
        }
    }

    public List<TypePoint> listerPointCollect() {
        List<TypePoint> t = new ArrayList();
        String req = "select * from type_point_collect";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                TypePoint p = new TypePoint(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
                t.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public List<String> listerPointCollectName() {
        List<String> t = new ArrayList();
        String req = "select name from type_point_collect";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {
                TypePoint p = new TypePoint();

                p.setName(resultSet.getString(1));

                t.add(p.getName());
            }
        } catch (SQLException ex) {
            Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return t;
    }

    public int selectIdName(String name) {
        int a = 0;
        String req = "select id from type_point_collect where name ='" + name + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {

                TypePoint p = new TypePoint();
                p.setId(resultSet.getInt(1));

                a = p.getId();

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
}
