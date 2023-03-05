/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.services.ServiceCommande;
import edu.artisty.entities.Commande;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import com.sun.org.apache.xerces.internal.util.FeatureState;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField; 
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.DatePicker;



       /**
 * FXML Controller class
 *
 * @author 
 */
public class AjouterCommandeController implements Initializable {
@FXML
    public TextField tfuserid;
@FXML 
    public Label meth_paiment; 
@FXML
    private TextField idcommande;
    @FXML
    public TextField prix_tot1;

@FXML 
    private ChoiceBox <String> ChoiceBox;

    private final String [] paying_with ={"cash","Carte"};
 @FXML
    private DatePicker DatePicker;
 @FXML 
 private TextField tfidcommande;
    

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ChoiceBox.getItems().addAll(paying_with);
    	ChoiceBox.setOnAction(this::getamount);
             
// TODO
    }
    	public void getamount(ActionEvent event) {
		
		String paying_with = ChoiceBox.getValue();
		meth_paiment.setText(paying_with);
	}

    @FXML
    private void AjouterCommande()  {
        if (tfuserid.getText().isEmpty()  || prix_tot1.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR, "votre id est invalide invalide", ButtonType.OK);
           a.showAndWait();
        } 
            try {
                
                ServiceCommande sc = new ServiceCommande();
                          
// Récupérer la date sélectionnée
                LocalDate date = DatePicker.getValue();

// Convertir la date en String avec un format spécifique
                String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  
                Commande c = new Commande( tfuserid.getText(),Integer.valueOf(prix_tot1.getText()),meth_paiment.getText(), dateString);
                sc.ajouter(c);
                Alert a = new Alert(Alert.AlertType.INFORMATION, "Commande ajoutée !", ButtonType.OK);
                a.showAndWait();
                
                
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherCommande.fxml"));
                Parent root = loader.load();
                tfuserid.getScene().setRoot(root);
                
                AfficherCommandeController acc = loader.getController();
                acc.setuserid(tfuserid.getText());
                acc.setPrix_Tot(Integer.valueOf(prix_tot1.getText()));
                acc.setMeth_Paiment(meth_paiment.getText());
                              
                
                
            } catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
            }
    

	}
}

   

    
 

