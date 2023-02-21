/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Events;
import edu.esprit.entities.Reservation;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
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
public class ServiceReservation implements InterfaceRes<Reservation> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservations`( `id_event`, `id_user`, `name`) VALUES (?,?,?)";
            PreparedStatement pso = cnx.prepareStatement(req);
            pso.setInt(1, r.getEvent().getId_event());
            pso.setInt(2, r.getId_user());
            pso.setString(3, r.getName());
            pso.executeUpdate();
            System.out.println("Reservation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void supprimer(int id_res) {
        try {
            String req1 = "DELETE FROM `reservations` WHERE id_res= " + id_res;
            Statement st = cnx.createStatement();
            st.executeUpdate(req1);
            System.out.println("Reservation deleted!");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    public void modifier(Reservation r) {
        try {
            String req2 = "UPDATE `reservations` SET `id_event`='" + r.getEvent() + "',`user_id`='" + r.getId_user() + "',`name`='" + r.getName() + "' WHERE `id_res`=" + r.getId_res();
            Statement st = cnx.createStatement();
            st.executeUpdate(req2);
            System.out.println("Reservation Updated!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> list = new ArrayList<>();

        try {
            String req = "SELECT * FROM `reservations` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Reservation r = new Reservation();

                ServiceEvents se = new ServiceEvents();

                r.setEvent(se.getOneById(rs.getInt(2)));
                r.setUser_id(rs.getInt(3));
                r.setName(rs.getString(4));

                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;

    }

    @Override
    public Reservation getOneById(int id_res) {
        Reservation r = null;
        try {
            String req = "SELECT * FROM `reservations` ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ServiceEvents se = new ServiceEvents();

                r.setEvent(se.getOneById(rs.getInt(2)));
                r.setUser_id(rs.getInt(3));
                r.setName(rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return r;
    }

    public List<Reservation> findreservationByName(String name) {
        List<Reservation> findreservation = null;
        // try{
        findreservation = this.getAll()
                .stream()
                .filter(e -> e.getName().equals(name))
                .collect(Collectors.toList());
        //  System.out.println("events is : " + findevents);
        //}catch (Exception e) {
        //  System.out.println(e.getMessage());

        // }
        return findreservation;
    }
    
     
     public boolean VerifDispoPlace(Reservation T) {
        int nbplaceres  = 0;
        int placedispo = 0;
        final int capacite=50 ;
         try {
        String req = "SELECT `nbplace` FROM  `reservation` " ;
        PreparedStatement ps = cnx.prepareStatement(req);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            nbplaceres = rs.getInt("nombre_place");
           
               }
               
        }
         
        catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
                int placesDisponibles = capacite - nbplaceres;
                if (nbplaceres <= placesDisponibles) {
            return true;
        } else {
            return false;
        }

    }
     
       


}