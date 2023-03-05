/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import edu.artisty.entities.Commande;
import edu.artisty.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


/**
 *
 * @author yessmine
 */
public class ServiceCommande implements IService<Commande> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
      public void ajouter(Commande c) {
        try {
            String req = "INSERT INTO `commande` ( `prix_tot`, `userid`, `payment`,`date_creation`) VALUES ('"+c.getPrix_tot()+"', '"+c.getuserid()+"','"+c.getPayment()+"','"+c.getDate_creation()+"')" ;
             Statement st = cnx.createStatement();
             st.executeUpdate(req);
          System.out.println("commande ajoutée !");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }

    
    
   public void supprimer(Commande commande) {
    String req = "DELETE FROM commande WHERE idcommande=?";
    try {
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setInt(1, commande.getId());
        ps.executeUpdate();
        System.out.println("Commande supprimée avec succès");
    } catch (SQLException ex) {
        System.out.println("Erreur lors de la suppression de la commande: " + ex.getMessage());
    }
}

 public void modifier(Commande c) {
    try {
        String req = "UPDATE commande SET `prix_tot` = ?, `payment` = ? WHERE `idcommande` = ?;";
        PreparedStatement ps = cnx.prepareStatement(req);
        ps.setDouble(1, c.getPrix_tot());
        ps.setString(2, c.getPayment());
        ps.setInt(3, c.getId());
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Commande updated successfully !");
        } else {
            System.out.println("No commandes were updated.");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}

           

    @Override
    public List<Commande> getAll() {
        List<Commande> list = new ArrayList<>();
        try {
            String req = "Select * from `Commande`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
            Commande c = new Commande(rs.getInt("idcommande"), rs.getInt("prix_tot"), rs.getString("userid"), rs.getString("payment"), rs.getString("date_creation"));
                list.add(c);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public Commande getOneById(int idcommande) {
        Commande c = null;
        try {
            String req = "SELECT * FROM `commande` WHERE `idcommande` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, idcommande);
            ResultSet rs = ps.executeQuery();

            while (rs.next() )
                c = new Commande (rs.getInt("idcommande"),rs.getInt("prix_tot"),rs.getString("userid"));
            System.out.println(c);
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return c;
    }

   public ObservableList<Commande> rechercheParpaiment(String test) {
       // Reclamation p = new Reclamation();
         
        ObservableList<Commande> List = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM Commande WHERE payment=? ";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
             Commande p = new Commande();
           // rs.first();
           while (rs.next()){
            p.setuserid(rs.getString("userid"));
            p.setPrix_tot(rs.getInt("prix_tot"));
            p.setDate_creation(rs.getString("date_creation"));
            p.setId(rs.getInt("idcommande"));
            p.setPayment(rs.getString("payment"));
             List.add(p);
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return List;
    }
   
    
    public ObservableList<Commande> afficherCommande() {
        ObservableList<Commande> myList = FXCollections.observableArrayList();
        try {

            String requete3 = "SELECT *FROM  Commande  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            
            Commande p = new Commande ();
          while (rs.next()){
            p.setuserid(rs.getString("userid"));
            p.setPrix_tot(rs.getInt("prix_tot"));
            p.setDate_creation(rs.getString("date_creation"));
            p.setId(rs.getInt("idcommande"));
            p.setPayment(rs.getString("payment"));
             myList.add(p);
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
        
    }
}

    
