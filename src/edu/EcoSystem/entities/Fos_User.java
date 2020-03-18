/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.EcoSystem.entities;

import java.time.LocalDate;

/**
 *
 * @author MEGAJAS
 */
public class Fos_User {
    
    private int id;
    private String username;
    private String username_canonical;
    private String email;
    private String email_canonical;
    private int enabled ;
    private String salt ;
    private String password;
    private LocalDate last_login;
    private String confirmation_token ;
    private LocalDate password_requested_at;
    private String roles;
    private String nom;

    public Fos_User(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, String confirmation_token, String roles, String nom) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.confirmation_token = confirmation_token;
        this.roles = roles;
        this.nom = nom;
    }
    

    public Fos_User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
   

    public Fos_User(int id, String username, String username_canonical, String email, String email_canonical, int enabled, String salt, String password, LocalDate last_login, String confirmation_token, LocalDate password_requested_at, String roles, String nom) {
        this.id = id;
        this.username = username;
        this.username_canonical = username_canonical;
        this.email = email;
        this.email_canonical = email_canonical;
        this.enabled = enabled;
        this.salt = salt;
        this.password = password;
        this.last_login = last_login;
        this.confirmation_token = confirmation_token;
        this.password_requested_at = password_requested_at;
        this.roles = roles;
        this.nom = nom;
    }

    public Fos_User(int id, String username, String password, String roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Fos_User() {
       
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

    public String getUsername_canonical() {
        return username_canonical;
    }

    public void setUsername_canonical(String username_canonical) {
        this.username_canonical = username_canonical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_canonical() {
        return email_canonical;
    }

    public void setEmail_canonical(String email_canonical) {
        this.email_canonical = email_canonical;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getLast_login() {
        return last_login;
    }

    public void setLast_login(LocalDate last_login) {
        this.last_login = last_login;
    }

    public String getConfirmation_token() {
        return confirmation_token;
    }

    public void setConfirmation_token(String confirmation_token) {
        this.confirmation_token = confirmation_token;
    }

    public LocalDate getPassword_requested_at() {
        return password_requested_at;
    }

    public void setPassword_requested_at(LocalDate password_requested_at) {
        this.password_requested_at = password_requested_at;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
