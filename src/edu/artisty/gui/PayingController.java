/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import java.net.URL;
import java.time.Duration;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.transform.Translate;


/**
 * FXML Controller class
 *
 * @author user
 */
public class PayingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label prix_totcash;

    @FXML
    private VBox vbox;

    @FXML
    private Button conf_carte;

    @FXML
    private Button impression;

    @FXML
    private TextField num_carte;

    @FXML
    private TextField nom_carte;

    @FXML
    private TextField CV_code;

    @FXML
    private Label prix_totcarte;

    @FXML
    private Button conf_cash;

        @FXML
    private Button swipe2;

    @FXML
    private Button swipe1;
    
    @FXML
    void imprimer(ActionEvent event) {

    }

    @FXML
    void valider_carte(ActionEvent event) {

    }

    @FXML
    void valider_cash(ActionEvent event) {

    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


}

        
    }    
    






