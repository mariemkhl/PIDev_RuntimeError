/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Reservation;
import edu.esprit.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherReservationController implements Initializable {

    @FXML
    private ScrollPane scr;
    @FXML
    private VBox vbr;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowReservation();
    }    
    
    private void ShowReservation(){
    
    vbr.getChildren().clear();
    
      //  System.out.println("vvygirbchdnuj;kl");
        
        List <Reservation> res = new ArrayList();
    ServiceReservation sr= new ServiceReservation();
  res= sr.getAll();
        System.out.println(sr.getAll());
        int x= 50 , y=50;
       
       for (int i=0;i<res.size();i++) {
           
            AnchorPane an = new AnchorPane();
            
            an.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #d4d4d4);"
                    + " -fx-border: 12px solid; -fx-border-color: white;");
            an.setLayoutX(x);
            an.setLayoutY(y);
           
            Label name = new Label(res.get(i).getName());
            name.setLayoutX(x +310 );
            name.setLayoutY(y -30);
            name.setStyle(" -fx-font :34px Georgia;");
            an.getChildren().add(name);
            
             Label nameEv = new Label(res.get(i).getEvent().getNameEv());
            nameEv.setLayoutX(x +340 );
            nameEv.setLayoutY(y  +30);
            nameEv.setStyle(" -fx-font : 19px Arial ;");
            an.getChildren().add(nameEv);
            
            
             String d = String.valueOf(res.get(i).getDateRE());
            Label date = new Label(d);
            date.setLayoutX(x + 319);
            date.setLayoutY(y + 120);
             date.setStyle(" -fx-font : 19px Arial ;");
            an.getChildren().add(date);
            
              Label lb = new Label (); 
            Button btnapi= new Button();
                btnapi.setText("Update");
                btnapi.setLayoutX(x + 280);
                btnapi.setLayoutY(lb.getLayoutY() + 130);
                an.getChildren().add(btnapi);
                btnapi.setStyle(  "    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
            
            an.setVisible(true);
            vbr.getChildren().add(an);
            
            
             Reservation a = new Reservation (Integer.valueOf(res.get(i).getId_res()), res.get(i).getEvent(),Integer.valueOf(res.get(i).getId_user()),res.get(i).getName(),res.get(i).getDateRE());
            
               
                btnapi.setOnAction(new EventHandler(){
                 public void handle(Event event){
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReservation.fxml"));
                 try {
                         Parent root = loader.load();
                          scr.getScene().setRoot(root)  ;
                     } catch (IOException ex) {
                         Logger.getLogger(AfficherEventsController.class.getName()).log(Level.SEVERE, null, ex);
                     }
               
                AjouterReservationController ae = loader.getController();
                        
                          scr.setVisible(false);
                          
                          ae.tfname.setText(a.getName());
                        // ae.datepc.setDayCellFactory(value);
                          
                          
                 }});
            
                  Button btndel= new Button();
                btndel.setText("delete");
                btndel.setLayoutX(x + 400);
                btndel.setLayoutY(lb.getLayoutY() + 130);
                an.getChildren().add(btndel);
                btndel.setStyle(  "    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
                btndel.setOnAction(new EventHandler(){
                 public void handle(Event event){
                      
                
                     sr.supprimer(a.getId_res());     
                 ShowReservation();
                 }
            });
       
       }}}
