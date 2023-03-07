/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.tests;

import edu.esprit.entities.Events;
import edu.esprit.entities.Reservation;
import edu.esprit.utils.SendEmail;
import edu.esprit.services.ServiceEvents;
import edu.esprit.services.ServiceReservation;
import edu.esprit.utils.MyConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class MainClass {

    public static void main(String[] args) {
        MyConnection myconnection = MyConnection.getInstance();

         java.sql.Date date = new java.sql.Date(123, 02, 25);
//        Events e1 = new Events("Music", date, "Bardo", 5, "online",50);
//        Events e2 = new Events("theatre", date, "Centre ville", 7, "theatre",50);
//        Events e3 = new Events("Paint", date, "Lac", 10, "salle exposition des tableaux",50);
       // Events e4 = new Events("Gallerie", date, "Lac", 10, "salle exposition des tableaux",50);
        ServiceEvents se = new ServiceEvents();
        
      //  se.participer(10, 6, 54);
        
    //     se.ajouter(e4);
        // se.ajouter(e2);
        //se.ajouter(e3);
       // se.supprimer(4);
        //se.supprimer(5);
       //2 se.modifier(e2,5);
        //   System.out.println(se.getAll());
        //System.out.println(se.getOneById(6));
        // List<Events> liste =se.getAll();
//        List<Events> findeventsByLocation = se.findeventsByLocation("Lac");
//        System.out.println("events is : " + findeventsByLocation);

        //****************************************************************************************************// 
       
        
//        Reservation r2 = new Reservation(4, 2, "mouhamed");
//        Reservation r3 = new Reservation(2, 4, "chaima");
//        Reservation r4 = new Reservation(2, 4, "dhia");
//        Reservation r5 = new Reservation(8, 4, "safae");
//        Reservation r6 = new Reservation(5, 4, "narjis");
        //   System.out.println(e4.getId_event());
        //  System.out.println(e3.getId_event());
        // Reservation r5 = new Reservation(e3, 2, 4, "safae");
        //    System.out.println(r5);
//      
//        System.out.println(r7);
          ServiceReservation sr = new ServiceReservation();
        //  sr.notifier("vous avez une nouvelle notif", msg);
           // Reservation r7 = new Reservation(e4,  5,6, "chaima",date );
          
       //  sr.ajouter(r7);
     //   sr.ajouter(r1);
        //   sr.ajouter(r5);
        //sr.ajouter(r3);
        //  sr.supprimer(5);
//        List<Reservation> findreservationByName = sr.findreservationByName("amine");
//        System.out.println("events is : " + findreservationByName);
//        if(sr.verifDispoPlace())
//        {
//            System.out.println("reservation acceptee");
//        }else {
//            System.out.println("reservation refusee");
//}
   //     System.out.println(sr.getAll());

 SendEmail ts = new SendEmail ();
 ts.envoyer();
    }

      
}
