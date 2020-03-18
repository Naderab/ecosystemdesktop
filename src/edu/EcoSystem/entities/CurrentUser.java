/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

/**
 *
 * @author zied
 */
public class CurrentUser {
     public static int id;
    public static String username;
    public static String email;
    public static int enable;
    public static String password;
    public static String roles;
    public static String avatar;

    public CurrentUser() {
    }

    @Override
    public String toString() {
        return "CurrentUser{" + "id=" + id + ", username=" + username + ", email=" + email + ", enable=" + enable + ", password=" + password + ", roles=" + roles + ", avatar=" + avatar + '}';
    }
    
    

    public CurrentUser(User u) {
        id= u.getId();
        username= u.getUsername();
        email= u.getEmail();
        enable=u.getEnable();
        password= u.getPassword();
        roles= u.getRoles();
        avatar= u.getAvatar();
    }
    
    
  
}
