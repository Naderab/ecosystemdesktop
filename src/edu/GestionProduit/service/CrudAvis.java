/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.GestionProduit.service;

import edu.GestionProduit.entities.Avis;
import edu.GestionProduit.tools.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Abir
 */
public class CrudAvis {
     Connection con ;
     public CrudAvis() {
    con=MyConnection.getInstance().getCnx();
    }
    
   
    public void insertAvis(Avis avis) throws SQLException {
 
        String req= "INSERT INTO `Avis`(`id`, `id_user, `monAvis`) VALUES (?,?,?)";
        PreparedStatement ste = con.prepareStatement(req);
        ste.setInt(1, avis.getId());
        ste.setString(4, avis.getMonAvis());
        ste.executeUpdate();         
        
    }

   
    public void deleteAvis(int id) {
        try {
            String req= "DELETE FROM `Avis` WHERE id=?";
            PreparedStatement ste = con.prepareStatement(req);
            ste.setInt(1, id);
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            System.err.println("Error !! ");
        }

        
               
        
    }

 
    
    public Avis findById(int id) {
        String req="SELECT * FROM Avis WHERE id='"+id+"'" ;
        ResultSet rs;
        Avis a=null;
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
           a=new Avis();
            
                  a.setId(rs.getInt("id"));
                  a.setId_user(rs.getInt("id_user"));
                 
                 
                  a.setMonAvis(rs.getString("monAvis"));
                 

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return a;
    }

    
    public List<Avis> findByParent(int parent) {
        List<Avis> listeAvis = new ArrayList();
          String req="SELECT * FROM Avis WHERE id_parent_id='"+parent+"'" ;
        ResultSet rs;
        
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
          Avis a = new Avis();
          a.setId_user(rs.getInt("id_user"));
          a.setMonAvis(rs.getString("monAvis"));
          listeAvis.add(a);

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return listeAvis;
       

    }
    public List<Avis> findByBabysitter(int babysitter) {
        List<Avis> listeAvis = new ArrayList();
        String req="SELECT * FROM Avis WHERE id_babysitter_id='"+babysitter+"'" ;
        ResultSet rs;
        
		try {
			rs = con.createStatement().executeQuery(req);
		
       
        while(rs.next()){
          Avis a = new Avis();
          a.setId(rs.getInt("id"));
          a.setId_user(rs.getInt("id_user"));
          a.setMonAvis(rs.getString("monAvis"));
          listeAvis.add(a);

        }
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return listeAvis;
       

    }

   
    public void updateAvis(Avis avis, int id) throws SQLException {
        
        String req= "Update `Avis` Set monAvis=? WHERE id='"+id+"'";
        PreparedStatement ste = con.prepareStatement(req);
        
        ste.setString(1, avis.getMonAvis());
       
        ste.executeUpdate();
    }
}

   
