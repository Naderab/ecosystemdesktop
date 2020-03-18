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
import edu.pointcollect.tools.DataSource;
import edu.pointcollect.tools.MyConnection;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.jws.soap.SOAPBinding.Style.RPC;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;








public class AddressPointCollectCrud
{
  Connection connection = null;
  
  public AddressPointCollectCrud() {
    connection = DataSource.getInstance().getConnection();
  }
  
  public void ajouterPointCollect(Markers p, int idTypePointCollect)
  {
    String req = "INSERT INTO markers (type_point_collect_id, name, address, lat, lng, slug) VALUES (?,?,?,?,?,?)";
    
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(req);
      preparedStatement.setInt(1, idTypePointCollect);
      preparedStatement.setString(2, p.getName());
      preparedStatement.setString(3, p.getAddress());
      preparedStatement.setDouble(4, p.getLat().doubleValue());
      preparedStatement.setDouble(5, p.getIng().doubleValue());
      
      preparedStatement.setString(6, p.getSlug());
      preparedStatement.execute();
    } catch (SQLException ex) {
      Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
  }
  
 
  
  public void modifierAdressePointCollect(Markers p, int id)
  {
    try {
      String requete = "UPDATE markers SET type_point_collect_id=?, name=?, address =?, lat =?, lng =? WHERE id=?";
      PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
      pst.setInt(1, p.getTypePointCollect());
      pst.setString(2, p.getName());
      pst.setString(3, p.getAddress());
      pst.setDouble(4, p.getLat().doubleValue());
      pst.setDouble(5, p.getIng().doubleValue());
      
      pst.setInt(6, id);
      pst.executeUpdate();
      System.out.println("Adresse modifie");
    } catch (SQLException e) {
      System.out.println(e.getMessage());
    }
  }
  
  public void supprimerAdressePointCollect(int id)
  {
    try {
      String requete = "DELETE FROM markers WHERE id=?";
      PreparedStatement pst = new MyConnection().getCnx().prepareStatement(requete);
      pst.setInt(1, id);
      pst.executeUpdate();
      System.err.println("Adresse supprime");
    } catch (SQLException e) {
      System.out.print(e.getMessage());
    }
  }
  
  public List<Markers> listerAdressePointCollect()
  {
    List<Markers> t = new ArrayList();
    String req = "select * from markers";
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
        //  String req2 = "select name from type_point_collect where id="+resultSet.getString(7);
        //  ResultSet resultSet2 = statement.executeQuery(req2);
        Markers p = new Markers(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6),resultSet.getString(7));
        
        t.add(p);
      }
    } catch (SQLException ex) {
      Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
    return t;
  }
  
  public List<Markers> listerAdressePointCollectProche(double lat, double lng, double distance, String name)
  {
    List<Markers> l = new ArrayList();
    try {
      String requete = "SELECT m.address,m.lat,m.lng,p.name,m.name, p.name, (6378 * acos(cos(radians(" + lat + ")) * cos(radians(m.lat)) * cos(radians(m.lng) - radians(" + lng + ")) + sin(radians(" + lat + ")) * sin(radians(m.lat)))) AS distance FROM markers m , type_point_collect p where p.id = m.type_point_collect_id and p.name=" + name + " having distance <= " + distance;
      
      Statement st = new MyConnection().getCnx().createStatement();
      st.executeUpdate(requete);
      ResultSet rsl = st.executeQuery(requete);
      while (rsl.next())
      {
        Markers p = new Markers();
        p.setId(rsl.getInt("id"));
        p.setTypePointCollect(rsl.getInt(2));
        p.setName(rsl.getString(3));
        p.setAddress(rsl.getString(4));
        p.setLat(Double.valueOf(rsl.getDouble(5)));
        p.setIng(Double.valueOf(rsl.getDouble(6)));
        l.add(p);
      }
    }
    catch (SQLException e) {
      System.err.println(e.getMessage());
    }
    return l;
  }
  
public void  list() throws IOException
{
     
       JSONArray employeeList = new JSONArray();
      
  List<Markers> t = new ArrayList();
    String req = "select * from markers";
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
        //  String req2 = "select name from type_point_collect where id="+resultSet.getString(7);
        //  ResultSet resultSet2 = statement.executeQuery(req2);
      //  Markers p = new Markers(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6),resultSet.getString(7));
        
      //  t.add(p);
      JSONObject obj = new JSONObject(); 
     int a = resultSet.getInt(1);
      int b =resultSet.getInt(2);
      String c =resultSet.getString(3);
      String d =resultSet.getString(4);
      double e =resultSet.getDouble(5);
      double f = resultSet.getDouble(6);
      String g= resultSet.getString(7);
      
      obj.put("id", a);
       obj.put("idType", b);
        obj.put("name", c);
         obj.put("address", d);
          obj.put("lat", e);
           obj.put("lng", f);
            obj.put("slug", g);
      
     //   obj.put("location", t);
        employeeList.add(obj);
      }
       
        try (FileWriter file = new FileWriter("markers.json")) {
 
            file.write(employeeList.toJSONString());
            file.flush();
 
        } catch (IOException ee) {
            ee.printStackTrace();
        }
    } catch (SQLException ex) {
      Logger.getLogger(TypePointCollectCrud.class.getName()).log(Level.SEVERE, null, ex);
    }
   
}

public static JSONArray convertToJSON(ResultSet resultSet)
            throws Exception {
        JSONArray jsonArray = new JSONArray();
        while (resultSet.next()) {
            int total_rows = resultSet.getMetaData().getColumnCount();
            for (int i = 0; i < total_rows; i++) {
                JSONObject obj = new JSONObject();
                obj.put(resultSet.getMetaData().getColumnLabel(i + 1)
                        .toLowerCase(), resultSet.getObject(i + 1));
                jsonArray.add(obj);
            }
        }
        return jsonArray;
    }
  
   public int selectSlug() {
        int a = 0;
        String req = "select count(id) from markers";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(req);
            while (resultSet.next()) {

                Markers p = new Markers();
                p.setId(resultSet.getInt(1));

                a = p.getId()+1;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieTrieCrud.class.getName()).log(Level.SEVERE, null, ex);
        }
        return a;
    }
  
   public boolean SearchAdressePointCollect(String address)
  {
      boolean test = false;
    List<Markers> t = new ArrayList();
    String req = "select * from markers where address ='"+address+"'";
    try {
      Statement statement = connection.createStatement();
      ResultSet resultSet = statement.executeQuery(req);
      while (resultSet.next()) {
        //  String req2 = "select name from type_point_collect where id="+resultSet.getString(7);
        //  ResultSet resultSet2 = statement.executeQuery(req2);
        Markers p = new Markers(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getString(4), resultSet.getDouble(5), resultSet.getDouble(6),resultSet.getString(7));
        
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