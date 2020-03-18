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
import edu.pointcollect.entities.CategorieTrie;
import edu.pointcollect.entities.SousCategorieTrie;
import edu.pointcollect.tools.DataSource;
import edu.pointcollect.tools.MyConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;

public class CategorieTrieCrud {
Connection connection = null;
  
  private List<SousCategorieTrie> l = new ArrayList();
  
  public CategorieTrieCrud() {
    connection = DataSource.getInstance().getConnection();
  }
  
  public void ajouterCategorie(CategorieTrie p, String path) throws FileNotFoundException {
    String req = "INSERT INTO categorie_trie(name, description,img) VALUES (?,?,?)";
    
   
    try
    {
     
      PreparedStatement preparedStatement = connection.prepareStatement(req);
      preparedStatement.setString(1, p.getName());
      preparedStatement.setString(2, p.getDescription());
      preparedStatement.setString(3, p.getImg());
      
      preparedStatement.execute();
      System.err.println("succees");
    } catch (SQLException ex) {
      Logger.getLogger(CategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void modifierCategorie(CategorieTrie p, int id)
  {
    try {
      String requete = "UPDATE categorie_trie SET name=?,description=?, img=? WHERE id=?";
      PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
      pst.setString(1, p.getName());
      pst.setString(2, p.getDescription());
      pst.setString(3, p.getImg());
      pst.setInt(4, id);
      pst.executeUpdate();
      System.out.println("Categorie modifie");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public void supprimerCategorie(int id)
  {
    try {
      String requete = "DELETE FROM categorie_trie WHERE id=?";
      PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
      pst.setInt(1, id);
      pst.executeUpdate();
      System.err.println("categorie_trie supprime");
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }
  }
  

  public ObservableList<String> listerCategorie()
  {
      
      
    ObservableList<String> t = FXCollections.observableArrayList();
    String req = "select name from categorie_trie";    
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
           
        CategorieTrie p = new CategorieTrie();
         p.setName(resultSet.getString(1));
        
        t.add(p.getName());
      }
    } catch (SQLException ex) {
      Logger.getLogger(CategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
  }
  
  
  
  
   public ObservableList<CategorieTrie> listeCategorie()
  {
    ObservableList<CategorieTrie> t = FXCollections.observableArrayList();
    String req = "select * from categorie_trie";
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
        
         CategorieTrie p = new CategorieTrie();
         p.setId(resultSet.getInt("id"));
         p.setName(resultSet.getString("name"));
         p.setImg(resultSet.getString("img"));
        
        t.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(CategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
  }
  
    public int selectIdName(String name)
  {
  int a = 0;
    String req = "select id from categorie_trie where name ='"+name+"'";
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
        
         CategorieTrie p = new CategorieTrie();
         p.setId(resultSet.getInt(1));
        
          a=p.getId();
          return a;
      }
    } catch (SQLException ex) {
      Logger.getLogger(CategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
   return a;
  }
    
    public boolean SearchCategorie(String name)
  {
      boolean test = false;
    List<CategorieTrie> t = new ArrayList();
    String req = "select * from categorie_trie where name ='"+name+"'";
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
        //  String req2 = "select name from type_point_collect where id="+resultSet.getString(7);
        //  ResultSet resultSet2 = statement.executeQuery(req2);
        CategorieTrie p = new CategorieTrie(resultSet.getString(2), resultSet.getString(3));
        
        t.add(p);
        if (! t.isEmpty())
        {
            test=true;
        }
        
      }
    } catch (SQLException ex) {
      Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return  test;
  }
    
    
}