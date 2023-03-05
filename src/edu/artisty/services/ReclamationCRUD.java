/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;
import edu.artisty.entities.Reclamation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import static java.util.Collections.list;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import edu.artisty.utils.DataSource;
/**
 *
 * @author user
 */
public class ReclamationCRUD implements ReclamationInterface {

     Connection cnx = DataSource.getInstance().getCnx();

    public ReclamationCRUD() {
      
    }

    @Override
    public void AjoutReclamation2(Reclamation R) {
        try {
            String requete2 = "INSERT INTO reclamation(`numero`,`commentaire`,`typeReclamation`) VALUES(?,?,?)";
            PreparedStatement pst = cnx.prepareStatement(requete2);
            pst.setInt(1, R.getNumero());
            pst.setString(2, R.getCommentaire());
            pst.setString(3, R.getTypeReclamation());
           pst.executeUpdate();
            System.out.println("votre reclamation est ajoutee");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public ObservableList<Reclamation> afficherReclamation() {
        ObservableList<Reclamation> myList = FXCollections.observableArrayList();
        try {

            String requete3 = "SELECT *FROM  reclamation  ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(requete3);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setNumero(rs.getInt(1));
                r.setCommentaire(rs.getString("Commentaire"));
                r.setTypeReclamation(rs.getString("typeReclamation"));
                myList.add(r);
                //Collections.sort(myList, Collections.reverseOrder());
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return myList;
        
    }

    

    @Override
    public void supprimer(Reclamation R) {
        try {
            String req = "DELETE FROM reclamation where numero=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, R.getNumero());
            pst.executeUpdate();
            System.out.println("Reclamation suprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
 

    }

    @Override
    public void modifier(Reclamation R) {
        try {
            String requete4 = "update reclamation commentaire=?,typeReclamation=? WHERE `numero`=? ";


            PreparedStatement pst = cnx.prepareStatement(requete4);
              pst.setInt(1,R.getNumero());
              pst.setString(2, R.getCommentaire());
              pst.setString(3, R.getTypeReclamation());
              

            int ok = pst.executeUpdate();

            if (ok == -1) {
                System.out.println("Insertion Reclamation failed");
            } else {
                System.out.println("Insertion Reclamation succes ! ");
            }
            System.out.println("Reclamation modifiée avec succès");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }


    }

     @Override
    public List<Reclamation> rechercheReclamation(int numero) {
        List<Reclamation> listReclamation = new ArrayList<>();
        try {
            String query = "SELECT * FROM reclamation where numero=?" ;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Reclamation R = new Reclamation();
                R.setCommentaire(rs.getString("commentaire"));
                R.setTypeReclamation(rs.getString("typeReclamation"));
                listReclamation.add(R);
               Collections.sort(listReclamation, Collections.reverseOrder());
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return listReclamation;
        
    }

    public int rechercheParRE(String test,String test1) {
        int numero = 0;
        try {
            String query = "SELECT numero FROM reclamation WHERE nom=? AND prenom=?";
            PreparedStatement pst = cnx.prepareStatement(query);
            pst.setString(1, test);
            pst.setString(2, test1);
            ResultSet rs = pst.executeQuery();
            rs.first();
            numero = rs.getInt(1);
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return numero;
    }
public ObservableList<Reclamation> recherchePartype(String test) {
       // Reclamation p = new Reclamation();
         
        ObservableList<Reclamation> List = FXCollections.observableArrayList();
        try {
            String requete3 = "SELECT * FROM reclamation WHERE typeReclamation=? ";
            PreparedStatement pst = cnx.prepareStatement(requete3);
            pst.setString(1, test);
            ResultSet rs = pst.executeQuery();
             Reclamation p = new Reclamation();
           // rs.first();
           while (rs.next()){
            p.setNumero(rs.getInt(1));
            p.setCommentaire(rs.getString("commentaire"));
            p.setTypeReclamation(rs.getString("typeReclamation"));
             List.add(p);
           }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return List;
    }

 public ObservableList<Reclamation> trier() {
        ObservableList<Reclamation> list = FXCollections.observableArrayList();

        try {
            String requete = "SELECT * FROM reclamation ";
            PreparedStatement pst = cnx.prepareStatement(requete);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Reclamation(rs.getInt("numero"),rs.getString("commentaire"),rs.getString("typeReclamation")));
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
        Collections.sort(list, new ReclamationComparator());

        return list;
    }
public class ReclamationComparator implements Comparator<Reclamation> {

    @Override
    public int compare(Reclamation r1, Reclamation r2) {
        return r1.getNumero() - r2.getNumero();
            
        }

}
}
    

