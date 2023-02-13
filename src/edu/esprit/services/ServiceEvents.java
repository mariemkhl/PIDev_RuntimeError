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


/**
 *
 * @author ASUS
 */
public class ServiceEvents implements IService<Events> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Events e) {
        try {
            String req = "INSERT INTO `events`(`name`, `date_event`, `location`, `user_id`,`categorie`,) VALUES (?,?,?,?,?)";
            PreparedStatement pso = cnx.prepareStatement(req);
            pso.setString(1, e.getName());
            pso.setDate(2, e.getDate_event());
            pso.setString(3, e.getLocation());
            pso.setInt(4,e.getUser_id() );
            pso.setString(6, e.getCategorie());
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
    public void modifier(Events e) {
        try {
            String req2 = "UPDATE `events` SET `name`='" + e.getName() + "',`date_event`='" + e.getDate_event() + "',`location`='" + e.getLocation() + ",`categorie`='"+e.getCategorie()+ "' WHERE `id_event` = " + e.getId_event();
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
                Events e = new Events(rs.getInt(1), rs.getString(2), rs.getDate(3), rs.getString(4), rs.getInt(5) , rs.getString(6));
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

}
