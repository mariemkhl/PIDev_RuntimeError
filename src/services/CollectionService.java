/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Collection;
import utils.DataConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nour Benkairia
 */
public class CollectionService implements IServiceP<Collection> {
     Connection cnx = DataConnection.getInstance().getCnx();

    @Override
    public void ajouter(Collection t) {
        try {
            String req = "INSERT INTO `collection`(`nom_col`, `id_p`,`nom_p`) VALUES (?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom_col());
            ps.setInt(2, t.getId_p());
            ps.setString(3, t.getNom_p());
            ps.executeUpdate();
            System.out.println("Collection added successfully !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Collection c) {
        try {
            String req = "DELETE FROM collection WHERE id_col = ? ";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setInt(1,c.getId_col());
            ste.executeUpdate();
            System.out.println("Collection deleted successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    

    @Override
    public void modifier(Collection t) {
        try {
            String req = "UPDATE `collection` SET `nom_col`='" + t.getNom_col() + "',`id_p`='" + t.getId_p() + "',`nom_p`='" + t.getNom_p() + "' WHERE `id_col`= '" + t.getId_col() + "';";

            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Collection updated successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public Collection getOneById(int id) {
        Collection t = null;
        try {
            String req = "SELECT * FROM `collection`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                t = new Collection(rs.getInt(1), rs.getString("nom_col"), rs.getInt(1), rs.getString("nom_p"));
                System.out.println(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;
    }

    @Override
    public List<Collection> getAll() {
        List<Collection> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `collection`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Collection t = new Collection(rs.getInt(1), rs.getString("nom_col"), rs.getInt(1), rs.getString("nom_p"));
                list.add(t);
                System.out.println(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

 
    
    
    
    
     public Collection getOneByNom(String nom) {
        Collection t = null;
        try {
            String req = "SELECT `id_col` FROM `collection` WHERE `nom_col` = " + nom;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                t = new Collection(rs.getInt(1));
                System.out.println(t);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return t;
    }
    
    
}
