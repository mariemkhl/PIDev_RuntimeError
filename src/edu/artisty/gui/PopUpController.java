/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author User
 */
public class PopUpController implements Initializable {

    @FXML
    private Button button;
    @FXML
    private Label label;
    
    
      @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        Notifications.create()
                .title("Title Text")
                .text("Hello World 0!")
                .showWarning();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
}
