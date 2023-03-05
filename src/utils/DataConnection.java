/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author iheb debbech
 */
public class DataConnection {
     private Connection cnx;
    private static DataConnection instance;
    
    private final String USER = "root";
    private final String PWD = "";
    private final String URL = "jdbc:mysql://localhost:3306/artisty";
    
    private DataConnection() {
        
        try {
            //Class.forName(  "com.mysql.cj.jdbc.Driver");
            cnx = DriverManager.getConnection(URL, USER, PWD);
            System.out.println("Connected !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public Connection getCnx() {
        return cnx;
    }

    public static DataConnection getInstance() {
        if(instance == null)
        {
            instance = new DataConnection();
        }
        return instance;
    }

    
    
}
