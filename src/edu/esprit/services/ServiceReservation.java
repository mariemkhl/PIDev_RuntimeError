/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;


import edu.esprit.entities.Reservation;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
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
public class ServiceReservation implements InterfaceRes<Reservation> {

    Connection cnx = MyConnection.getInstance().getCnx();

    @Override
    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservations`( `id_event`, `user_id`, `name`) VALUES (?,?,?)";
            PreparedStatement pso = cnx.prepareStatement(req);
            pso.setInt(2, r.getId_event());
            pso.setInt(3, r.getUser_id());
            pso.setString(4, r.getName());
            pso.executeUpdate();
            System.out.println("Reservation created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    @Override
    public void supprimer(int id_res) {
        try {
            String req1 = "DELETE FROM `reservations` WHERE id_res " + id_res;
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
            String req2="UPDATE `reservations` SET `id_event`='"+r.getId_event()+"',`user_id`='"+r.getUser_id()+"',`name`='"+r.getName()+"' WHERE `id_res`=" +r.getId_res();
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
                Reservation r = new Reservation(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4));
                
                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}
