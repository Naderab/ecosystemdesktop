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
 * @author ramzi
 */
public class MyConnexion {

    
    
    String url="jdbc:mysql://localhost:3306/ecosystem";
    String login="root";
    String mdp="";
    
    Connection cnx;    //conn ctr espace java sql pour avoir le use automatiquement
    public static MyConnexion instance;
    
    private  MyConnexion() {
       
        
        try {
            cnx = DriverManager.getConnection(url,login,mdp);
            System.out.println("Connexion Etablie!");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        
    }
    
    public Connection getCnx()
    {
        return cnx;
    }

    public static MyConnexion getInstance() {
       if(instance == null){
           instance = new MyConnexion();
       }
        return instance;
    }
    
  
}
