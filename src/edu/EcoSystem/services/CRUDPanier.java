/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.tools.MyDB;
import edu.EcoSystem.entities.Fos_User;
import java.sql.*;

import edu.EcoSystem.entities.Panier;
import edu.EcoSystem.entities.Produit;
import Utils.UserAccess;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MEGAJAS
 */
public class CRUDPanier  {
    
     static Connection cnx ;

    public CRUDPanier() throws SQLException {
        this.cnx =MyDB.getInstance().getConnexion();
    }
     
    
     
     public void ajouterPanier(Panier p){
          String query="INSERT INTO panier VALUES ('"+p.getId()+"','"+p.getPrix()+"','"+p.getDate()+"','"+p.getNom()+"','"+p.getId_client()+"','"+p.getQuantite()+"','"+p.getId_produit()+"')";
             
    try {
             
           Statement st=cnx.createStatement();
           st.executeUpdate(query);
           System.out.println("bien ajouté");
          
         }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
         
     }
     
     public void ajouterPanierFront(Panier p){
          String query="INSERT INTO panier VALUES ('"+p.getId()+"','"+p.getPrix()+"','"+p.getDate()+"','"+p.getNom()+"','"+p.getId_client()+"','"+p.getQuantite()+"','"+p.getId_produit()+"')";
             
    try {
             
           Statement st=cnx.createStatement();
           st.executeUpdate(query);
           System.out.println("bien ajouté");
          
         }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
         
     }
     
     
     public ArrayList<Panier> afficherPanier(){
          ArrayList<Panier> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from panier";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              Panier p = new Panier(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),rs.getString(4)
                      ,rs.getInt(5));
            list.add(p);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
     
     public   void modifierPanier(Panier s){
      
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update panier set nom= ?, prix=?  where id=? ");
            pt.setString(2, s.getPrix());
            pt.setString(1, s.getNom());
         
            pt.setInt(3, s.getId());
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     
      public   void modifierPanierNom(Panier s){
      
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update panier set   quantite=? where id=? ");
            
            pt.setInt(1, s.getQuantite());
         
            pt.setInt(2, s.getId());
            
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     
      public  void supprimerPanier(int id){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from panier where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
      
        public  void supprimerPanierNom(String nom){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from panier where nom=? ");
            pt.setString(1, nom);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
        } 
     
      
      
      
     
      
      
         public  ArrayList<Panier> recherchePanier(String motClef){
     ArrayList<Panier> list =new ArrayList<>();

         try {
            
            Statement st=cnx.createStatement();
            String req="Select * from panier WHERE `nom` LIKE '%"+motClef+"%'";
            ResultSet rs = st.executeQuery(req);
             int i = 0;
            while(rs.next()){
                   i++;
             Panier p = new Panier(rs.getInt(1),rs.getString(2),
                      rs.getDate(3).toLocalDate(),
                      rs.getString(4),
                      rs.getInt(5));
            list.add(p);            
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
   }
      
      public List<Panier> TrierPrixPanier() {
        List<Panier> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from panier  order by prix DESC ";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {

                                Panier p;
               p= new Panier(rs2.getInt(1),rs2.getString(2),
                        rs2.getDate(3).toLocalDate(),rs2.getString(4),rs2.getInt(5));
                                list.add(p);
                               
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
    }
       
          public List<Panier> TrierDatePanier() {
        List<Panier> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from panier  order by date  ";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {
                        Panier p;
               p= new Panier(rs2.getInt(1),rs2.getString(2),
                        rs2.getDate(3).toLocalDate(),rs2.getString(4),rs2.getInt(5));
                                list.add(p);
                               
                                                              
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
    }
         
      public void ajouterProduitPanier(Produit p , Panier pa){
          String query="INSERT INTO produit,panier VALUES ('"+p.getId()+"','"+p.getTitre()+"','"+p.getDescription()+"','"
                  +p.getCategorie()+"','"+
                  p.getSous_categorie()+"','"+
                  p.getPrix()+
                  "','"+
                  p.getImage()+"','"+
                  p.getId_panier()+
                  "','"+p.getQuantite()+
                  "','"+p.getId_commande()+"') where p.getId_panier()=pa.getId() ";
             
    try {
             
           Statement st=cnx.createStatement();
           st.executeUpdate(query);
           System.out.println("bien ajouté");
          
         }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
         
     }
  
   public   void modifiernbre(int id,int n){
      
     
         try {
             cnx=MyDB.getInstance().getConnexion();
             PreparedStatement pt=cnx.prepareStatement("Update panier set quantite = quantite-"+n+" where id="+id+" ");

             pt.executeUpdate();
             System.out.println("hello");
         } catch (SQLException ex) {
             Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
         }
        }   
      
      
      
      
      
     
     
     
      

}
