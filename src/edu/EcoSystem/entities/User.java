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
public class User {
    
    private int id;
    private String username;
    private String email;
    private int enable;
    private String password;
    private String roles;
    private String avatar;

    public User() {
    }

    public User(int id, String username, String email, int enable, String password, String roles, String avatar) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enable = enable;
        this.password = password;
        this.roles = roles;
        this.avatar = avatar;
    }

    public User(int id, String username, String email, int enable, String roles) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.enable = enable;
        this.roles = roles;
    }
    
    
    

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User(String username, String email, String password, String avatar) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public User(int b, String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

  
   
    
    
    
    
    
    
    
}
