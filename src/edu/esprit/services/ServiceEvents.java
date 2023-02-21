/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Events;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author ASUS
 */
public class ServiceEvents implements IService<Events> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Events e) {

        try {
            String req = "INSERT INTO `events`(`name`, `date_event`, `location`, `id_user`,`categorie`) VALUES (?,?,?,?,?);";
            PreparedStatement pso = cnx.prepareStatement(req);
            pso.setString(1, e.getName());
            pso.setDate(2, e.getDate_event());
            pso.setString(3, e.getLocation());
            pso.setInt(4, e.getId_user());
            pso.setString(5, e.getCategorie());
            Date Date = new Date(120, 01, 21);
            pso.executeUpdate();
            System.out.println("event created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(int id_event) {
        try {
            String req1 = "DELETE FROM `events` WHERE id_event = " + id_event;
            Statement st = cnx.createStatement();
            st.executeUpdate(req1);
            System.out.println("Event Deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void modifier(Events e, int i) {
        try {
            String req2 = "UPDATE `events` SET `name`='" + e.getName() + "',`date_event`='" + e.getDate_event() + "',`location`='" + e.getLocation() + "',`categorie`='" + e.getCategorie() + "' WHERE `id_event` =' " + i + "';";
            Statement st = cnx.createStatement();
            st.executeUpdate(req2);
            System.out.println("Event Updated ! ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public List<Events> getAll() {
        List<Events> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `events` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Events e = new Events(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getString(6));
                //  (rs.getInt(1), rs.getString("name"), rs.getDate(3), rs.getString("location"),rs.getInt(1));
                list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Events getOneById(int id_event) {
        Events e = null;
        try {
            String req = "SELECT * FROM `events` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Events e1 = new Events(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getString(6));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return e;
    }
   
      public List <Events> findeventsByLocation  ( String location){
          
          List <Events> findevents = null;
         // try{
             findevents=  this.getAll()
                          .stream()
                          .filter(e ->  e.getLocation().equals(location))
                          .collect(Collectors.toList());
            //  System.out.println("events is : " + findevents);
          //}catch (Exception e) {
          //  System.out.println(e.getMessage());

         // }
          return findevents ;
    }
      public void participer(int id_user){
      try{
      String req = "SELECT * FROM `events`where id_user =  "+id_user;
       PreparedStatement pse= cnx.prepareStatement(req);
       ResultSet rs = pse.executeQuery(req);
       
          System.out.println("participation succes");
      
      
      }catch(Exception e){
          System.out.println(e.getMessage());
      }
          
      
      }
       
//    public boolean rechercheEvent (Date date_event, String location){
//     List <Events> List = new ArrayList();
//    for (Events v :List ){
//    if(v.getDate_event().equals(date_event)&& v.getLocation().equals(location)){
//       return true ; 
//    }}
//      return false ; 
//
//}
  
}
