/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.services;

import edu.EcoSystem.entities.User;
import edu.EcoSystem.touls.BCrypt;
import edu.EcoSystem.touls.MyConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zied
 */
public class UserCRUD {
    
    public boolean login(String username, String password ) throws SQLException {
        if (!username.isEmpty() && !password.isEmpty() ) {
            String requete = "SELECT password FROM fos_user WHERE username = '" + username +"'";
            Statement s = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            String pw = rs.getString(1);
                     return (BCrypt.checkpw(password, pw));}
            else return false ;
        }  
        else {
            return false;
        }

    }
    
    
    public boolean bloqué( int enable){
        if(enable==1){
            return true;
        }
        return false;
    }
    
    
    
    
    public void ajoutUser(User u, String password ){
        try {
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String requete = "INSERT INTO fos_user (username,email,password,roles,avatar) VALUES "
                + "('" + u.getUsername() + "','" + u.getEmail() + "','" + hashedpw + "','" +"abonner"+ "','" + u.getAvatar() + "')";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
    
    
   public void ajoutAdmin(User u, String password ){
        try {
            String hashedpw = BCrypt.hashpw(password, BCrypt.gensalt(12));
        String requete = "INSERT INTO fos_user (username,email,password,roles,avatar) VALUES "
                + "('" + u.getUsername() + "','" + u.getEmail() + "','" + hashedpw + "','" +u.getRoles()+ "','" + u.getAvatar() + "')";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
    }
   
   
 
    public List<User> chercher(String s) throws SQLException {
		List<User> users = new ArrayList<>();
String rq = "select * from fos_user where username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%'" ;
			
			Statement st = MyConnection.getInstance().getCnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    public List<User> chercherEnabled(String s) throws SQLException {
		List<User> users = new ArrayList<>();
String rq = "select * from fos_user where enabled ='"+ 1 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st = MyConnection.getInstance().getCnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    public List<User> chercherDisabled(String s) throws SQLException {
		List<User> users = new ArrayList<>();
String rq = "select * from fos_user where enabled ='"+ 0 +"' and (username like'"+s+"%' or email like'"+s+"%' or roles like'"+s+"%')" ;
			
			Statement st = MyConnection.getInstance().getCnx().createStatement();
			ResultSet rst = st.executeQuery(rq);

			while (rst.next()) {
				User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
                                users.add(u);
			}
		
		return users;
	}
    
    
    
     public List<User> getAllUsers() throws SQLException
    {   
        List <User> products= new ArrayList<>();
        String req="SELECT * FROM fos_user";
        Statement stm=MyConnection.getInstance().getCnx().createStatement();
        ResultSet rst=stm.executeQuery(req);
        
        while (rst.next()){
              User u=new User(rst.getInt("id"),rst.getString("username"),rst.getString("email"),rst.getInt("enabled"),rst.getString("roles"));
            products.add(u);
        }
        return products;
    }

    
    
    
    
    
    
    
    
    
    public boolean modifierUser(User u){
        try{
            String cpass=BCrypt.hashpw(u.getPassword(), BCrypt.gensalt(12));
        String requete = "UPDATE fos_user SET "
                + "username = ?,"
                + "email = ?,"
                + "password = ?"
                + "WHERE id=?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setString(1, u.getUsername());
        pst.setString(2, u.getEmail());
        pst.setString(3, cpass);
        pst.setInt(4, u.getId());
        pst.executeUpdate();
        System.out.println(u.getEmail()+ u.getUsername());
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return true;
}
    
    
    
    public void modifierUsername(String email, String username) throws SQLException{
        String requete= "UPDATE fos_user SET username='"+ username +"'"+"WHERE email='"+ email+"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierEmail(String email, String mail) throws SQLException{
        String requete= "UPDATE fos_user SET email='"+ mail +"'"+"WHERE email='"+ email+"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierPassword(String email, String pass) throws SQLException{
       String cpass=BCrypt.hashpw(pass, BCrypt.gensalt(12));
        String requete= "UPDATE fos_user SET password='"+ cpass +"'"+"WHERE email='"+ email+"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierRole(String email, String role) throws SQLException{
        String requete= "UPDATE fos_user SET roles='"+ role +"'"+"WHERE email='"+ email+"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierAvatar(String email, String avatars) throws SQLException{
        String requete= "UPDATE fos_user SET avatar='"+ avatars +"'"+"WHERE email='"+ email+"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    public void modifierEtat(int id, int enable ) throws SQLException{
        System.out.println(id);
        String requete= "UPDATE fos_user SET enabled='"+ enable +"'"+"WHERE id='"+ id +"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        st.executeUpdate(requete);
    }
    
    
    
    public void supprimerUser(int id ){
        try{
        String requete = "DELETE FROM fos_user WHERE id=?";
        PreparedStatement pst = MyConnection.getInstance().getCnx().prepareStatement(requete);
        pst.setInt(1, id);
        pst.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        
    }
    
    
    public List<User> afficherUser() {
        
        List<User> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    public List<User> afficherUserEnabled() {
        
        List<User> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user where enabled='"+ 1 +"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    public List<User> afficherUserDisabled() {
        
        List<User> listUser = new ArrayList<>();
        try {
        String requete ="SELECT * FROM fos_user where enabled='"+ 0 +"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        
            ResultSet rs = st.executeQuery(requete);
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));
                listUser.add(u);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return listUser;
    }
    
    
    
    public User getUserByUsername(String username) throws SQLException{
        String requete="SELECT * FROM fos_user WHERE username='"+username+"'";
        Statement st = MyConnection.getInstance().getCnx().createStatement();
        ResultSet rs = st.executeQuery(requete);
        User u = new User();
       while(rs.next()){
                u.setId(rs.getInt(1));
                u.setUsername(rs.getString(2));
                u.setEmail(rs.getString(4));
                u.setRoles(rs.getString(12));
                u.setEnable(rs.getInt(6));  
            }
       return u ;
    }
    
    
    
    
     public boolean existUser (String email) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE email = '" + email +"'";
            Statement s = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
    
     
     public boolean existUsername (String username) throws SQLException {
        
          String requete = "SELECT * FROM fos_user WHERE username = '" + username +"'";
            Statement s = MyConnection.getInstance().getCnx().createStatement();
            ResultSet rs = s.executeQuery(requete);
            if (rs.next()){
            return true ; }
            else 
                return false ;
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
