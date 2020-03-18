/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.tools.MyDB;
import edu.EcoSystem.entities.Fos_User;
import edu.EcoSystem.entities.Panier;
import edu.EcoSystem.entities.Produit;
import Utils.UserAccess;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MEGAJAS
 */
public class CRUDProduit {
    static Connection cnx ;
    public CRUDProduit() throws SQLException {
        this.cnx =MyDB.getInstance().getConnexion();
    }
     public ArrayList<Produit> afficherProduit(){
          ArrayList<Produit> list = new ArrayList<>() ;

         try {
            Statement st=cnx.createStatement();
            String req="Select * from produit ";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              Produit pt = new Produit(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getString(7)
                      ,rs.getInt(8),rs.getInt(9),rs.getInt(10));
            list.add(pt);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
     public void ajouterProduit(Produit p){
          String query="INSERT INTO produit VALUES ('"+p.getId()+"','"+p.getTitre()+"','"+p.getDescription()+"','"
                  +p.getCategorie()+"','"+
                  p.getSous_categorie()+"','"+
                  p.getPrix()+
                  "','"+
                  p.getImage()+"','"+
                  p.getId_panier()+
                  "','"+p.getQuantite()+
                  "','"+p.getId_commande()+"')";
             
    try {
             
           Statement st=cnx.createStatement();
           st.executeUpdate(query);
           System.out.println("bien ajouté");
          
         }catch (SQLException e)
         {
             System.out.println(e.getMessage());
         }
         
     }
     
      public ArrayList<Panier> afficherProduitPanier() throws SQLException{
          ArrayList<Panier> list = new ArrayList<>() ;
          CRUDFos_User US = new CRUDFos_User();
        Fos_User u1 = US.Get_Single_User(UserAccess.getUser_ID());
        int idUser = u1.getId();

         try {
            Statement st=cnx.createStatement();
            String req="Select * from panier p join fos_user u on u.id=p.id_client ";
            ResultSet rs = st.executeQuery(req);
            while(rs.next()){
              Panier p = new Panier(rs.getInt(1),rs.getString(2),rs.getDate(3).toLocalDate(),rs.getString(4)
                      ,rs.getInt(5),rs.getInt(6));
            list.add(p);            
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(CRUDPanier.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list ;
         
     }
        
    
    
}
