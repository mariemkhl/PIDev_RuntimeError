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

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
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
    private Button btnrefresh;
    @FXML
    private TextField tfnbplace;
    @FXML
    private ChoiceBox<String> Mychoicebox;
    private final String[] category = {"online", "cinematic", "literature", "theatre", "salle_exposition_des_tableaux", "salle_exposition_des_sculpture"};
    @FXML
    private Label Mylabel;
    @FXML
    private TextField tfuser;
    @FXML
    private Button bntshow;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mychoicebox.getItems().addAll(category);
        Mychoicebox.setOnAction(this::getCategory);
    }

    public void getCategory(ActionEvent event) {
        String myCategory = Mychoicebox.getValue();
        Mylabel.setText(myCategory);

    }

    @FXML
    private void getDate_event(ActionEvent event) {

    }
//    

    @FXML
    private void add_event(ActionEvent event) throws IOException {
        Date date = java.sql.Date.valueOf(dpDate.getValue());
        String name = tfName.getText();
        int userid= Integer.valueOf(tfuser.getText());
        String location = tfLocation.getText();
        int nbplace = Integer.valueOf(tfnbplace.getText());
        String myCategory = Mychoicebox.getValue();

        if ( tfName.getText().isEmpty() || tfLocation.getText().isEmpty() || tfnbplace.getText().isEmpty() || tfuser.getText().isEmpty() ) {
            Alert a = new Alert(Alert.AlertType.ERROR);
//            a.setTitle("Field is empty");
            a.setHeaderText(null);
            a.setHeaderText("Check you information");
            a.showAndWait();
        } else if (dpDate.getValue().getYear() < 2023) {
            Alert al2 = new Alert(Alert.AlertType.ERROR);
            al2.setHeaderText(null);
            al2.setContentText("Current day please");
            al2.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("event created !");
            alert.showAndWait();
            ServiceEvents se = new ServiceEvents();
            Events e = new Events(name, userid,date, location, nbplace, myCategory);
            se.ajouter(e);

        }
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommande.fxml"));
                Parent root = loader.load();
                tfuser.getScene().setRoot(root);

    }

    @FXML
    private void refresh_ev(ActionEvent event) {
        
        
        

    }


    @FXML
    private void delete_event(ActionEvent event) {
    }

    @FXML
    private void show_event(ActionEvent event) {
    }

}
