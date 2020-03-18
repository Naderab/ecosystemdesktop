/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;
import edu.EcoSystem.tools.MyDB;
import java.sql.*;

import edu.EcoSystem.entities.Commande;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author MEGAJAS
 */
public class CRUDCommande {
    static Connection cnx ;

    public CRUDCommande() throws SQLException {
        this.cnx =MyDB.getInstance().getConnexion();
    }
    public void ajouterCommande(Commande c)
    {
          String query="INSERT INTO commande VALUES ('"+c.getId()+"','"+c.getQuantite()+"','"+c.getPrix_total()+"','"
                  +c.getPrix_commande()+"','"+
                  c.getId_client()+"','"+
                  c.getDateCommande()+
                  "','"+c.getEtat()+
                  "','"+c.getId_panier()+"')";
             
    try {
             
           Statement st=cnx.createStatement();
           st.executeUpdate(query);
           System.out.println("bien ajouté");
          
         }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
         
     }
    

     
     public ArrayList<Commande> afficherCommande(){
          ArrayList<Commande> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from commande";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              Commande c = new Commande(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDate(6).toLocalDate(),rs.getString(7)
                      ,rs.getInt(8));
            list.add(c);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
     
     public   void modifierCommande(Commande s){
         
      Date  Date1;
        try {
           
            PreparedStatement pt=cnx.prepareStatement("Update commande set quantite= ?, prix_total= ? , prix_commande= ? , dateCommande= ? ,etat=?  where id=? ");
            
           
            pt.setInt(1, s.getQuantite());
            pt.setInt(2, s.getPrix_total());
            pt.setInt(3, s.getPrix_commande());
            pt.setDate(4, Date.valueOf(s.getDateCommande()));
            pt.setString(5, s.getEtat());
            pt.setInt(6, s.getId());
            
            pt.executeUpdate();
            //System.out.println(pt);
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
     
      public  void supprimerCommande(int id){
        
        try {
            PreparedStatement pt=cnx.prepareStatement("Delete from commande where id=? ");
            pt.setInt(1, id);
            pt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
} 
      
     public  ArrayList<Commande> rechercheCommande(String motClef){
     ArrayList<Commande> list =new ArrayList<>();

         try {
            
            Statement st=cnx.createStatement();
            String req="Select * from commande WHERE `etat` LIKE '%"+motClef+"%'";
            ResultSet rs = st.executeQuery(req);
             int i = 0;
            while(rs.next()){
                   i++;
             Commande c = new Commande(rs.getInt(1),rs.getInt(2)
                      ,rs.getInt(3),rs.getInt(4),rs.getInt(5),
                      rs.getDate(6).toLocalDate(),
                      rs.getString(7),
                      rs.getInt(8));
            list.add(c);            
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
   }
     
     public List<Commande> TrierQuantite() {
        List<Commande> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from commande where etat='validée' order by quantite DESC ";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {

                                Commande c;
               c = new Commande(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getInt(5),
                        rs2.getDate(6).toLocalDate(),rs2.getString(7),rs2.getInt(8));
                                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
    }
     
       public List<Commande> TrierPrixTotal() {
        List<Commande> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from commande where etat='encours' order by prix_total DESC ";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {

                                Commande c;
               c = new Commande(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getInt(5),
                        rs2.getDate(6).toLocalDate(),rs2.getString(7),rs2.getInt(8));
                                list.add(c);
                               
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
    }
     
        public List<Commande> TrierPrixCommande() {
        List<Commande> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from commande where etat='validée' order by prix_commande DESC ";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {

                                Commande c;
               c = new Commande(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getInt(5),
                        rs2.getDate(6).toLocalDate(),rs2.getString(7),rs2.getInt(8));
                                list.add(c);
                               
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
    }
       
          public List<Commande> TrierDateCommande() {
        List<Commande> list = new ArrayList<>();
        try {
            String sql2 = "SELECT * from commande where etat='validée' order by dateCommande  ";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            while (rs2.next()) {

                                Commande c;
               c = new Commande(rs2.getInt(1),rs2.getInt(2),rs2.getInt(3),rs2.getInt(4),rs2.getInt(5),
                        rs2.getDate(6).toLocalDate(),rs2.getString(7),rs2.getInt(8));
                                list.add(c);
                               
            }
            
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return list;
    }
           public int Notif() {
        try {
            String sql2 = "SELECT count(*) FROM `commande` WHERE dateCommande = CURRENT_DATE and etat='validée'  ";
            Statement st2 = cnx.createStatement();
            ResultSet rs2 = st2.executeQuery(sql2);
            
            while(rs2.next())
            {
                //return 0;
                return rs2.getInt(1);            
                //System.out.println(rs2.getInt(1)); 
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }

        return -1;
         
       
           }  
     
     
   public void Archiver(Commande c,int id) {
        
        String sql = "UPDATE `commande` SET `etat`='validée'  WHERE id =" + id + ";";
        try {
            Statement stl = cnx.createStatement();
            stl.executeUpdate(sql);
            System.out.println("Update Commande done");
            //System.out.println(sql);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
            System.err.println("SQLState: " + ex.getSQLState());
            System.err.println("VendorError: " + ex.getErrorCode());
        }
    
    }
     public ArrayList<Commande> afficherCommandeArchive(){
          ArrayList<Commande> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from commande where etat='encours'";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              Commande c = new Commande(rs.getInt(1),rs.getInt(2),rs.getInt(3),rs.getInt(4),rs.getInt(5),rs.getDate(6).toLocalDate(),rs.getString(7)
                      ,rs.getInt(8));
            list.add(c);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
     
     
     
     
}
