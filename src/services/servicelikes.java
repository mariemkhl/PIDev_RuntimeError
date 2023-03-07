/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

import java.sql.*;
import entities.Likes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.DataConnection;

/**
 *
 * @author iheb debbech
 */
public class servicelikes implements Iservices<Likes> {
       Connection cnx = DataConnection.getInstance().getCnx();

    @Override
    public void ajouter(Likes l) {
        try {
            String req = "INSERT INTO `likes` (`refL`,`RefU`, `nomU`) VALUES ('" + l.getRefL() + "', '" + l.getRefU() + "', '" + l.getNom_u() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("like created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void ajouter2(Likes l) {
        try {
            String req = "INSERT INTO `likes` (`refL`,`id_u`, `nom_u`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, l.getRefL());
            ps.setInt(2, l.getRefU());
            ps.setString(3, l.getNom_u());
            ps.executeUpdate();
            System.out.println("like created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(String ref) {
        try {
            String req = "DELETE FROM `likes` WHERE RefL = " + ref;
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Like deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Likes l) {
        try {
            String req = "UPDATE `likes` SET `nom_u` = '" + l.getNom_u() + "'WHERE RefL = '" + l.getRefL()+"';";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Like updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Likes> getAll() {
        List<Likes> list = new ArrayList<>();
        try {
            String req = "Select * from likes";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Likes l = new Likes(rs.getString(2), rs.getInt("RefU"), rs.getString(4));
                list.add(l);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Likes  getOneById(String ref) {
        Likes l = null;
        try {
            String req = "Select * from likes WHERE `likes`.`RefL` = '" + ref+"';";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                l = new Likes(rs.getString(2), rs.getInt("id_u"), rs.getString(4));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return l;
    }

    
  
    
}

