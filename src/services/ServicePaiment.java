/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import services.Iservicepaiment;
import entities.PAIMENT;
import utils.DataConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author user
 */
public class ServicePaiment implements Iservicepaiment<PAIMENT> {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    Connection cnx = DataConnection.getInstance().getCnx();

    @Override
      public void ajouter(PAIMENT p) {
        try {
            String req = "INSERT INTO `paiment` ( `num_carte`, `nom_carte`,`CV_code`,`prix_tot`) VALUES ('"+p.getNum_carte()+"', '"+p.getNom_carte()+"', '"+p.getCV_code()+"', '"+p.getPrix_tot()+"')" ;
             Statement st = cnx.createStatement();
             st.executeUpdate(req);
          System.out.println("paiment ajouté !");
        } catch (SQLException ex) {
           System.out.println(ex.getMessage());
        }
    }

    
       public void supprimer(int commande) {
        try {
            String req = "DELETE FROM `paiment` WHERE `commande` = ?" ;
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, commande);
            ps.executeUpdate();
            System.out.println("Paiment supprimé!");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

 
           

    @Override
    public List<PAIMENT> getAll() {
        List<PAIMENT> list = new ArrayList<>();
        try {
            String req = "Select * from `paiment`";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
           PAIMENT p = new PAIMENT(rs.getInt("num_carte"), rs.getString("nom_carte"), rs.getDate("date_ex"), rs.getInt("CV_code"), rs.getInt("prix_tot"), rs.getInt("commande"));
                list.add(p);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    @Override
    public PAIMENT getOneById(int commande) {
        PAIMENT p = null;
        try {
            String req = "SELECT * FROM `paiment` WHERE `commande` = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, commande);
            ResultSet rs = ps.executeQuery();

            while (rs.next() ) 
           p = new PAIMENT(rs.getInt("num_carte"), rs.getString("nom_carte"), rs.getDate("date_ex"), rs.getInt("CV_code"), rs.getInt("prix_tot"), rs.getInt("commande"));
            System.out.println(p);
            
            } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;
    }


    

        
   
}

    



    

