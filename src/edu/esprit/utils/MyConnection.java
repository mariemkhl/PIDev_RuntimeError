/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author artisty
 */
public class MyConnection {
    
   private Connection cnx;  
   private static MyConnection instance;
  
    public final String USER="root";
    public final String PWD ="";
     public final String URL="jdbc:mysql://localhost:3306/artisty";
   
    

    public MyConnection(){
        try{
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected To DB");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }
    public static MyConnection getInstance(){
        if(instance == null)
            instance = new MyConnection();
        return instance;
    }
    public Connection getCnx(){
        return cnx;
    }
    
}