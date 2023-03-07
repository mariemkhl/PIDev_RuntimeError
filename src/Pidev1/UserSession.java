/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

/**
 *
 * @author lenovo
 */
public class UserSession {
    private static String name;
    private static int id;

    public UserSession() {
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        UserSession.name = name;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        UserSession.id = id;
    }

    

    
}