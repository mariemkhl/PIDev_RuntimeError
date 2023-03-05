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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherEventsController implements Initializable {

    private Label lname;
    
    private VBox vbox;
    private AnchorPane ev;
    @FXML
    private ScrollPane spe;
    @FXML
    private VBox vbe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showeven();

    }


    private void returnaction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvents.fxml"));
        Parent root = loader.load();
        lname.getScene().setRoot(root);
    }

   
    private void showeven() {
        System.out.println("sxwscdf");
        List<Events> even = new ArrayList();       
        ServiceEvents se = new ServiceEvents();
       // listevent.getItems();
        even = se.getAll();
                    System.out.println(even);

        //  System.out.println(ev);
        int x = 350, y = 50;
        // listevent.getItems().addAll(even);
        for (int i=0;i<even.size();i++) {
            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);
           
            Label name = new Label(even.get(i).getName());
            name.setLayoutX(x + 14);
            name.setLayoutY(y + 22);
            an.getChildren().add(name);
            String d = String.valueOf(even.get(i).getDate_event());
            Label date = new Label(d);
            date.setLayoutX(x + 135);
            date.setLayoutY(y + 22);
            an.getChildren().add(date);
            Label location = new Label(even.get(i).getLocation());
            location.setLayoutX(x + 214);
            location.setLayoutY(y + 22);
            an.getChildren().add(location);
            String u = String.valueOf(even.get(i).getId_user());
            Label user = new Label(u);
            user.setLayoutX(x + 337);
            user.setLayoutY(y + 22);
            an.getChildren().add(user);
            Label category = new Label(even.get(i).getCategorie());
            category.setLayoutX(x + 422);
            category.setLayoutY(y + 22);
            an.getChildren().add(category);
            String nb = String.valueOf(even.get(i).getNbplacetotal());
            Label nbreplace = new Label(nb);
            nbreplace.setLayoutX(x + 571);
            nbreplace.setLayoutY(y + 22);
            an.getChildren().add(nbreplace);
            Button btnaffiche = new Button("Affichage");

          //  an.getChildren().addAll(name, date, location, user, category, nbreplace);
         //   ev.getChildren().addAll(an);
               an.setVisible(true);
            vbe.getChildren().add(an);
            
        }
    }
}
