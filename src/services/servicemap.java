/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;

//import entities.Likes;
import Pidev1.UserSession;
import entities.Likes;
import entities.emplacement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import utils.DataConnection;


/**
 *
 * @author iheb debbech
 */
public class servicemap implements Iservices<emplacement>{
   Connection cnx = DataConnection.getInstance().getCnx();

    @Override
    public void ajouter(emplacement e) {
  try {
            String req = "INSERT INTO `map_art`( `nomplace`, `description`, `lien`, `image`,  `Latitude`, `Longitude`, `categorie`) VALUES ('" + e.getNomplace() + "','" + e.getDescription() + "','" + e.getLien() + "','" + e.getImage() + "','" + e.getLatitude() + "','" + e.getLongitude() + "','" + e.getCategorie() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("emplacement created !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(String nome) {
try {
            String req = "DELETE FROM map_art WHERE nomplace = '" + nome+"';";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("emplacement deleted !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(emplacement e) {
 try {
     servicelikes ls= new servicelikes();
     Likes li= new Likes("e"+e.getLongitude(),UserSession.getId(),UserSession.getName());
     ls.ajouter(li);
     int l=e.getNblikes()+1;
     System.out.println(l);
            String req = "UPDATE `map_art` SET `nblikes` = '" +l + "'WHERE nomplace = '" + e.getNomplace()+"';";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("emplacement updated !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }    }

    @Override
    public List <emplacement> getAll() {
            List<emplacement> list = new ArrayList<>();
        try {
            String req = "Select * from map_art";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                emplacement e = new emplacement(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getInt(6),rs.getDouble(7),rs.getDouble(8),rs.getString(9));
                list.add(e);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } 
    return list;}

    @Override
    public emplacement getOneById(String ref) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
