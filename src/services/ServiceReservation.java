/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import services.ServiceEvents;
import com.itextpdf.text.Paragraph;
import services.InterfaceRes;
import entities.Events;
import entities.Reservation;
import utils.DataConnection;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
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
import java.util.stream.Collectors;

/**
 *
 * @author ASUS
 */
public class ServiceReservation implements InterfaceRes<Reservation> {

    Connection cnx = DataConnection.getInstance().getCnx();

    @Override
    public void ajouter(Reservation r) {
        try {
            String req = "INSERT INTO `reservations`( `nameEv`, `id_user`, `name`, `dateRE`) VALUES (?,?,?,?)";
            PreparedStatement pso = cnx.prepareStatement(req);
            pso.setString(1, r.getEvent().getNameEv());
            pso.setInt(2, r.getId_user());
            pso.setString(3, r.getName());
            pso.setDate(4,r.getDateRE());
              Date Date = new Date(120, 01, 21);
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
            String req2 = "UPDATE `reservations` SET `nameEv`='" + r.getEvent() + "',`user_id`='" + r.getId_user() + "',`name`='" + r.getName() +"',`dateRE`='"+r.getDateRE()+ "' WHERE `id_res`=" + r.getId_res();
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
                
          
                r.setId_res(rs.getInt(1));

                r.setEvent(se.getOneByName(rs.getString(2)));
                r.setUser_id(rs.getInt(3));
                r.setName(rs.getString(4));
                r.setDateRE(rs.getDate(5));

                list.add(r);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
       // System.out.println("done");
        return list;

    }

    @Override
    public Reservation getOneById(int id_res) {
        Reservation r = null;
        try {
            String req = "SELECT * FROM `reservations` WHERE id_res ="+id_res;
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                ServiceEvents se = new ServiceEvents();
              r.setId_res(rs.getInt("id_res"));
                
                r.setEvent(se.getOneByName(rs.getString(2)));
                r.setUser_id(rs.getInt(3));
                r.setName(rs.getString(4));
                r.setDateRE(rs.getDate(5));
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
    
      public Events getEventForReservation(String s){
        String query = "SELECT * FROM events WHERE nameEv = '" + s +"' LIMIT 1";
        Events evenement = new Events();
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                evenement.setId_event(rs.getInt("id_evene"));
//                evenement.set(rs.getString("titre_evenement"));
//                evenement.setLieuEvenement(rs.getString("lieu_evenement"));
//                evenement.setTypeEvenement(rs.getString("type_evenement"));
//                evenement.setDescriptionEvenement(rs.getString("description_evenement"));
//                evenement.setDateEvenement(rs.getDate("date_evenement"));
//                evenement.setImageEvenement(rs.getString("image_evenement"));
//                evenement.setStatusEvenement(rs.getInt("status"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return evenement;
    }
    
    
    
    
    
   
    public void notifier(String titre, String msg) {

        // Check if the system tray is supported
        if (!SystemTray.isSupported()) {
            System.out.println("System tray is not supported.");
            return;
        }

        // Get the system tray and create a tray icon
        SystemTray tray = SystemTray.getSystemTray();
        Image image = Toolkit.getDefaultToolkit().getImage("path/to/notification_icon.png");
        TrayIcon trayIcon = new TrayIcon(image, "Notification");

        // Add the tray icon to the system tray
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("Tray icon could not be added.");
            return;
        }

        // Show the notification
        trayIcon.displayMessage(titre, msg, MessageType.INFO);

    }
    
    
    
    
    
    public Reservation AffichagePDF(){
        Reservation r= null;
        try {
            int size=0;
            
            String req = "SELECT * FROM `reservations`";
            
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            
            while(rs.next()){
            size++ ;
            }
            rs.absolute(2);
           String s=  rs.getString("id_res");
           ServiceEvents se = new ServiceEvents();
           Events e = se.getOneByName(rs.getString("nameEv"));
           
           r = new Reservation(Integer.valueOf(rs.getString("id_res")), e,Integer.valueOf(rs.getString("id_user")),rs.getString("name"),rs.getDate("dateRE"));
          
        } catch (SQLException ex) {
            System.out.println("errrorrr");
           
        }
      return r;
    
    }
    
    
    
    private static void addEmptyLine(Paragraph paragraph, int number) {
        
        }
    }

    
     
     
     


