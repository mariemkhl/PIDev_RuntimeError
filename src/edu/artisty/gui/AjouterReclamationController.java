/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.entities.Reclamation;
import edu.artisty.services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterReclamationController implements Initializable {
    @FXML
    private ChoiceBox<String> ChoiceBox1;

    @FXML
    private Button confirmation;

    @FXML
    private Button exit;
        @FXML
    private Label choisir1;
        
        @FXML
    private TextField section_commentaires;
    
    private final String [] type ={"Authenticité des oeuvres d'art","Bug","Elément inapproprié","Récéption d'un poduit érroné","problème de livraison", "problème de paiment"};

    @FXML
    void confirmation(ActionEvent event) {
        
        ReclamationCRUD rc = new ReclamationCRUD();
        Reclamation r = new Reclamation( section_commentaires.getText(),choisir1.getText());
                rc.AjoutReclamation2(r);
                
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre réclamation a été ajoutée. Nous allons la prendre en considération !", ButtonType.OK);
                a.showAndWait();

    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                Parent root = loader.load();
                exit.getScene().setRoot(root);

    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ChoiceBox1.getItems().addAll(type);
    	ChoiceBox1.setOnAction(this::choisir);
             
// TODO
    }
    	public void choisir(ActionEvent event) {
		
		String type = ChoiceBox1.getValue();
		choisir1.setText(type);
	}
        // TODO
    }    
  






