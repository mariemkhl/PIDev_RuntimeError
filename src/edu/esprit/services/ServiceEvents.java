/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Events;

import edu.esprit.utils.MyConnection;
import java.io.File;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author ASUS
 */
public class ServiceEvents implements IService<Events> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Events e) {

        try {
            
            String checkReq = "SELECT COUNT(*) FROM `events` WHERE nameEv = ?";
        PreparedStatement checkPs = cnx.prepareStatement(checkReq);
        checkPs.setString(1, e.getNameEv());
        ResultSet rs = checkPs.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            System.err.println("Un événement avec ce nom existe déjà!");
            return;
        }
//            
            String req = "INSERT INTO `events`(`nameEv`, `date_event`, `location`, `id_user`,`categorie`, `nbplacetotal`,`img`) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pso = cnx.prepareStatement(req);
            pso.setString(1, e.getNameEv());
            pso.setDate(2, e.getDate_event());
            pso.setString(3, e.getLocation());
            pso.setInt(4, e.getId_user());
            pso.setString(5, e.getCategorie());
            pso.setInt(6, e.getNbplacetotal());
//            pso.
//            File file = new File (e.getImg().toString());
//            Path path = file.toPath();
//            String img = path.toString();
              pso.setString(7,e.getImg());
          //  pso.setInt(7, e.getNbplaceres());
            Date Date = new Date(120, 01, 21);
            pso.executeUpdate();
            System.out.println("event created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Events> AfficherEvenement() {
        ObservableList<Events> myList1 = FXCollections.observableArrayList();
        try {

            String requete3 = " SELECT * FROM events";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Events e = new Events();
                // e.setIdEvent(rs.getInt("idEvent"));
                e.setNameEv(rs.getString("nameEv"));
                e.setDate_event(rs.getDate("date_event"));
                e.setLocation(rs.getString("location"));
                e.setId_user(rs.getInt("id_user"));
                e.setCategorie(rs.getString("categorie"));
                e.setNbplacetotal(rs.getInt("nbplacetotal"));
                myList1.add(e);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            //ex.printStackTrace();
        }
        return myList1;
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
            String req2 = "UPDATE `events` SET `nameEv`='" + e.getNameEv()+ "',`date_event`='" + e.getDate_event() + "',`location`='" + e.getLocation() + "',`categorie`='" + e.getCategorie() + "',`nbplacetotal`='" + e.getNbplacetotal() + "' WHERE `id_event` =' " + i + "';";
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
                Events e = new Events(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7),rs.getString(8));
                 // (rs.getInt(1), rs.getString("name"), rs.getDate(3), rs.getString("location"),rs.getInt(1));
                list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Events getOneByName(String nameEv) {
        Events e = new Events();
        String req = "SELECT * FROM `events` WHERE `nameEv` ='" + nameEv+ "';";

        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                e.setId_event(rs.getInt("id_event"));
                e.setNameEv(rs.getString("nameEv"));
                e.setDate_event(rs.getDate("date_event"));
                e.setLocation(rs.getString("location"));
                e.setId_user(rs.getInt("id_user"));
                e.setCategorie(rs.getString("categorie"));
                e.setNbplacetotal(rs.getInt("nbplacetotal"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return e;
    }
//        Events e = null;
//        try {
//            String req = "SELECT * FROM `events` ";
//            Statement st = cnx.createStatement();
//            ResultSet rs = st.executeQuery(req);
//            while (rs.next()) {
//                Events e1 = new Events(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5), rs.getString(6), rs.getInt(7),rs.getInt(8));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//
//        return e;

    public List<Events> findeventsByLocation(String location) {

        List<Events> findevents = null;
        // try{
        findevents = this.getAll()
                .stream()
                .filter(e -> e.getLocation().equals(location))
                .collect(Collectors.toList());
        //  System.out.println("events is : " + findevents);
        //}catch (Exception e) {
        //  System.out.println(e.getMessage());

        // }
        return findevents;
    }

    public TreeSet<Events> tripardate(Date date_event) {
        TreeSet<Events> trievents = null;
        trievents = this.getAll()
                .stream()
                .collect(Collectors
                        .toCollection(()
                                -> new TreeSet<>((a, b) -> a.getDate_event().compareTo(b.getDate_event()))));

        return trievents;

    }

//    public void participer(int id_user, int id_event, int nbplaceres) {
//        int nbplacedispo = 0;
//        int nbplacetotal = 0;
//        try {
//            String req = "SELECT `nbplacetotal` FROM `events` WHERE id_event=" + id_event;
//            PreparedStatement pse = cnx.prepareStatement(req);
//            //   System.out.println("helloo");
//            ResultSet rs = pse.executeQuery(req);
//
//            if (rs.next()) {
//
//                System.out.println(nbplacedispo = rs.getInt("nbpalcetotal") - rs.getInt("nbplaceres"));
//                if (nbplacedispo < nbplacetotal) {
//                    String req1 = "UPDATE events SET nbplaceres = ? WHERE id_event = ? ";
//                    PreparedStatement ps = cnx.prepareStatement(req1);
//                    //      System.out.println("helloo");
//                    ps.setInt(1, rs.getInt("nbplaceres") + 1);
//                    ps.setInt(2, id_event);
//                    ps = cnx.prepareStatement(req1);
//                    ps.executeUpdate();
//                    System.out.println(" You are inscribed for the event");
//                } else {
//                    System.out.println("The number of places is full");
//                }
//
//            }
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//
//    }
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
