/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Events;
import edu.esprit.services.ServiceEvents;
import edu.esprit.utils.MyConnection;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class MainClass {
    public static void main (String [] args){
    MyConnection myconnection = new MyConnection();
       
        Date  Date = null ; 
        // Events e1 = new Events ("Music", Date,"Bardo");
          ServiceEvents se = new ServiceEvents();   
     //     se.ajouter(e1);
}
}