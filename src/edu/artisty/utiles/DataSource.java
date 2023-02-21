/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.utiles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Nour Benkairia
 */
public class DataSource {
    private Connection cnx;
    
    private static DataSource instance;
    
    private final String USER="root";
    private final String PWD="";
    private final String URL="jdbc:mysql://localhost:3306/artisty";

    public Connection getCnx() {
        return cnx;
    }

    public DataSource() {
        try {
            cnx= (Connection)DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected to DB");
        } catch (SQLException ex) {
           System.err.println(ex.getMessage());
        }
    }
    
    public static DataSource getInstance(){
        if(instance==null){
            instance= new DataSource();
        }
        return instance;
    }
    
}
