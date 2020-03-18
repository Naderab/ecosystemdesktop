/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nader
 */
public class MyConnection {
    public static MyConnection instance;
    String url="jdbc:mysql://localhost:3306/ecosystem";
    String login="root";
    String mdp="";
    Connection cnx;
    
    private MyConnection()
    {
        try {
            cnx= DriverManager.getConnection(url,login, mdp);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance()
    {
        if (instance == null)
        {
            instance= new MyConnection();
        }
        return instance;
    }
}
