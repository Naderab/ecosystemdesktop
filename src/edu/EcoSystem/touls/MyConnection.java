/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.touls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author zied
 */
public class MyConnection {
    String url="jdbc:mysql://localhost:3306/ecosystem";
    String login="root";
    String mdp="";
    Connection cnx;
    public static MyConnection instance;

    private MyConnection() {
        try {
            cnx=DriverManager.getConnection(url,login,mdp);
            System.out.println("Connexion établie");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
           
    }

    public Connection getCnx() {
        return cnx;
    }
    
    public static MyConnection getInstance(){
        if(instance == null){
            instance = new MyConnection();
        }
        return instance;
    }
    

}
