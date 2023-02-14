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
       
       java.sql.Date date= new java.sql.Date(120, 01, 21);
       Events e1 = new Events ("Music",date,"Bardo",5,"online");
       Events e2 = new Events ("theatre",date,"Centre ville",7,"theatre");
       Events e3 = new Events ("Paint",date,"Lac", 10, "salle_exposition_des_tableaux");
          ServiceEvents se = new ServiceEvents();   
      // se.ajouter(e1);
       //se.ajouter(e2);
           //se.ajouter(e3);

        //se.supprimer(5);
     //se.modifier(e2,4);
       //  System.out.println(se.getAll());
        System.out.println(se.getOneById(6));
}
}