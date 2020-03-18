/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.pointcollect.tools;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;









public class MyConnection
{
  String url = "jdbc:mysql://localhost:3306/ecosystem";
  String login = "root";
  String mdp = "";
  Connection cnx;
  
  public MyConnection() {
    try {
      cnx = DriverManager.getConnection(url, login, mdp);
      System.err.println("Connexion etablie");
    }
    catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  


  public String getUrl()
  {
    return url;
  }
  
  public void setUrl(String url) {
    this.url = url;
  }
  
  public String getLogin() {
    return login;
  }
  
  public void setLogin(String login) {
    this.login = login;
  }
  
  public String getMdp() {
    return mdp;
  }
  
  public void setMdp(String mdp) {
    this.mdp = mdp;
  }
  
  public Connection getCnx() {
    return cnx;
  }
  
  public void setCnx(Connection cnx) {
    this.cnx = cnx;
  }
}
