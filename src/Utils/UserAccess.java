/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import edu.EcoSystem.entities.Fos_User;

/**
 *
 * @author Laptop Center
 */
public class UserAccess {
    
    private static int User_ID ; 
    public static Fos_User User ; 
    private static String Username ; 

    public static int getUser_ID() {
        return User_ID;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUser_ID(int User_ID) {
        UserAccess.User_ID = User_ID;
    }

    public static void setUsername(String Username) {
        UserAccess.Username = Username;
    }

    public static Fos_User getUser() {
        return User;
    }

    public static void setUser(Fos_User User) {
        UserAccess.User = User;
    }
    
    
    
}
