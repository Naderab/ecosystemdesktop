/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.tools;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MEGAJAS
 */
public class MyDB {
      Connection connexion;
    final  String url = "jdbc:mysql://localhost/ecosystem";
    final  String user = "root";
    final  String password = "";
    private static MyDB instance=null;
    
    private MyDB() {
        
          try {
              connexion = (Connection) DriverManager.getConnection(url, user, password);
          } catch (SQLException ex) {
              Logger.getLogger(MyDB.class.getName()).log(Level.SEVERE, null, ex);
          }
            System.out.println("\nConnexion établie");
        
    }
    
    public static MyDB getInstance() throws SQLException{
        if( instance == null)
            instance = new MyDB();
        
        return instance;
    }
    
    public Connection getConnexion() {
        return connexion;
    }
    
}
