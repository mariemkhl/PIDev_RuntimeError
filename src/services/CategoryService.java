/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Category;
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
public class CategoryService implements IServiceP<Category> {
Connection cnx = DataConnection.getInstance().getCnx();
    @Override
    public void ajouter(Category t) {
 try {
            String req = "INSERT INTO `category`(`nom`) VALUES (?)";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getNom());
           
            ps.executeUpdate();
            System.out.println("Category added successfully !");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }    }

    @Override
    public void supprimer(Category t) {
try {
            String req = "DELETE FROM category WHERE nom = ? ";
            PreparedStatement ste = cnx.prepareStatement(req);
            ste.setString(1,t.getNom());
            ste.executeUpdate();
            System.out.println("Category deleted successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


    }

   
    public void modifier(Category t,String s) {
try {
           String req = "UPDATE `category` SET `nom`='" + t.getNom() + "' WHERE `nom`= '" + s + "';";
            //String req = "UPDATE `category` SET nom='" + t.getNom() + "' WHERE nom= '" + t.getNom() + "';";
           // String req = "UPDATE `category` SET `nom`= ? WHERE `nom`= ? ;";

            Statement st = cnx.createStatement();
           
            st.executeUpdate(req);
            System.out.println("Category updated successfully !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }  
    }

    @Override
    public Category getOneById(int id) {
 Category c = null;
        try {
            String req = "SELECT * FROM `category`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                c = new Category(rs.getInt(1), rs.getString("nom"));
                System.out.println(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return c; 
    }

    @Override
    public List<Category> getAll() {
 List<Category> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM `category`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category c = new Category(rs.getInt(1), rs.getString("nom"));
                list.add(c);
                System.out.println(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    
     public List<Category> getNames() {
 List<Category> list = new ArrayList<>();
        try {
            String req = "SELECT nom FROM `category`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category c = new Category(rs.getString("nom"));
                list.add(c);
                System.out.println(list);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

      public Category getOneByName(String nom) {
 
        try {
            String req = "SELECT * FROM `category` WHERE `nom`='"+nom+"'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Category c = new Category(rs.getInt(1),rs.getString(2));
               return c;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return null;
    }

    @Override
    public void modifier(Category t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

