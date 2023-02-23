/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Events;
import edu.esprit.services.ServiceEvents;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterEventsController implements Initializable {

    @FXML
    private DatePicker dpDate;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfLocation;
    @FXML
    private Button btnadd;
    @FXML
    private Button btnmodify;
    @FXML
    private Button btndelete;
    @FXML
    private Button bntsee;
    @FXML
    private Button btnrefresh;
    @FXML
    private TextField tfnbplace;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
//     public Connection getConnection() {
//        Connection conn;
//        try {
//            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javafx", "root", "");
//            return conn;
//        } catch (Exception ex) {
//            System.out.println("Error: " + ex.getMessage());
//            return null;
//        }
//    }

    @FXML
    private void getDate_event(ActionEvent event) {
        
    }

    
    
    
    
    @FXML
    private void add_event(ActionEvent event) {
        Date date = java.sql.Date.valueOf(dpDate.getValue());
    
               if (tfName.getText().isEmpty()  || tfLocation.getText().isEmpty() || tfnbplace.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "champs vides ", ButtonType.OK);
           a.showAndWait();}
           else if (dpDate.getValue().getYear() < 2023) {
            Alert al2 = new Alert(Alert.AlertType.ERROR);
            al2.setHeaderText(null);
            al2.setContentText("Veuillez choisir une date courante");
            al2.showAndWait();
           
        } else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Succées");
            alert.setHeaderText(null);
            alert.setContentText("L'ajout d'un new event a été effectué avec succées");
            alert.showAndWait();
           
           } 
            try {
                
                               ServiceEvents se = new ServiceEvents();

                          
                Events e = new Events ( tfName.getText(),tfLocation.getText(),Integer.valueOf(tfnbplace.getText()));
                se.ajouter(e);
                Alert a = new Alert(Alert.AlertType.INFORMATION, " ajoutée !", ButtonType.OK);
                a.showAndWait();
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvents.fxml"));
                Parent root = loader.load();
                tfName.getScene().setRoot(root);
                
                AfficherEventsController aec = loader.getController();
//                aec.setName(tfName.getText());
//                aec.setLocation(tfLocation.getText());
//                aec.setNbplace(tfnbplace.getText());
//        
    }   catch (IOException ex) {
        System.err.println(ex.getMessage());
        }
    
}

    @FXML
    private void see_event(ActionEvent event) {
    }

    @FXML
    private void delete_event(ActionEvent event) {
    }

    @FXML
    private void refresh_ev(ActionEvent event) {
    }
}
