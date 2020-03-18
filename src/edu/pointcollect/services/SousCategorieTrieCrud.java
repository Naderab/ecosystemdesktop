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
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TreeItem;










public class SousCategorieTrieCrud
{
  Connection connection = null;
  

  public SousCategorieTrieCrud()
  {
    connection = DataSource.getInstance().getConnection();
  }
  
  public void ajouterCategorie(SousCategorieTrie p, int id) throws FileNotFoundException {
    String req = "INSERT INTO sous_categorie_trie(name, description,img,id_categorie) VALUES (?,?,?,?)";
   
   
    try
    {
     
      PreparedStatement preparedStatement = connection.prepareStatement(req);
   
      preparedStatement.setString(1, p.getName());
      preparedStatement.setString(2, p.getDescription());
      preparedStatement.setString(3, p.getImg());
      preparedStatement.setInt(4,id);
      
      preparedStatement.execute();
    } catch (SQLException ex) {
      Logger.getLogger(SousCategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
  public void modifierCategorie(SousCategorieTrie p, int id)
  {
    try {
      String requete = "UPDATE sous_categorie_trie SET name=?,description=?, img=? ,id_categorie= ? WHERE id=?";
      PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
      pst.setString(1, p.getName());
      pst.setString(2, p.getDescription());
      pst.setString(3, p.getImg());
      pst.setInt(4, p.getIdCat());
      pst.setInt(5, id);
      pst.executeUpdate();
      System.out.println("Categorie modifie");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public void supprimerCategorie(int id)
  {
    try {
      String requete = "DELETE FROM sous_categorie_trie WHERE id=?";
      PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
      pst.setInt(1, id);
      pst.executeUpdate();
      System.err.println("sous_categorie_trie supprime");
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }
  }
  

  public ObservableList<SousCategorieTrie> listerCategorie(int id)
  {
          ObservableList<SousCategorieTrie> t = FXCollections.observableArrayList();
   String req = "select * from sous_categorie_trie  where id_categorie ='"+ id +"'" ;
   try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next())
      {
          SousCategorieTrie sc=new SousCategorieTrie();
          sc.setName(resultSet.getString("name"));
          sc.setImg(resultSet.getString("img"));
          t.add(sc);
      }
    }
    catch (SQLException ex) {
      Logger.getLogger(SousCategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
  }
  
   public boolean SearchSousCategorie(String name, int id)
  {
      boolean test = true;
    List<SousCategorieTrie> t = new ArrayList();
    String req = "select * from sous_categorie_trie where name ='"+name+"' and id_categorie ='"+ id +"'" ;
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
        //  String req2 = "select name from type_point_collect where id="+resultSet.getString(7);
        //  ResultSet resultSet2 = statement.executeQuery(req2);
    
          SousCategorieTrie sc=new SousCategorieTrie();
          sc.setName(resultSet.getString("name"));
          sc.setImg(resultSet.getString("img"));
          t.add(sc);
      
        if (! t.isEmpty())
        {
            test=false;
        }
        
      }
    } catch (SQLException ex) {
      Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return  test;
  } 
  
 
  
}