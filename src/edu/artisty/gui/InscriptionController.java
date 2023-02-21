/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.entities.Utilisateur;
import edu.artisty.services.ServiceUtilisateur;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;



/**
 * FXML Controller class
 *
 * @author khoul
 */
public class InscriptionController implements Initializable {

    @FXML
    private TextField ftusername;
    @FXML
    private TextField ftid;
    
    @FXML
    private TextField ftpassword;
    @FXML
    private TextField ftemail;
    @FXML
    private TextField ftnumtel;
    @FXML
    private Button ifadd;

 

   
   
    /**
     * Initializes the controller class.
     */
    @Override
    
    public void initialize(URL url, ResourceBundle rb) {
        
        // Mark required fields with an asterisk
    }

    @FXML 
  

private void saveuser(ActionEvent event) {
    int id = Integer.parseInt(ftid.getText());
    String username = ftusername.getText();
    ;
    String password = ftpassword.getText();
    
   
    String email = ftemail.getText();
     
    String num_tel = ftnumtel.getText();
    
    // Check if the username field is empty
    if (username.trim().isEmpty() ) {
        // Show an error message to the user
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Please enter username.");
        alert.showAndWait();
        return;
    }else if (username.length() < 1|| username.length() > 2){ // Show an error message to the user
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Username should be between 6 and 20 characters.");
    alert.showAndWait();
    return;
}
    if (password.trim().isEmpty()) {
    // Show an error message to the user
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a password.");
    alert.showAndWait();
    return;
}
    if (email.trim().isEmpty()) {
    // Show an error message to the user
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter an email.");
    alert.showAndWait();
    return;
    
}
    // Check if the email is in a valid format
Pattern pattern = Pattern.compile("([a-zA-Z0-9_\\\\-\\\\.]+)@([a-zA-Z0-9_\\\\-\\\\.]+)\\\\.([a-zA-Z]{2,5})");
Matcher matcher = pattern.matcher(email);
if (!matcher.matches()) {
    // Show an error message to the user
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a valid email address.");
    alert.showAndWait();
    return;
}
ServiceUtilisateur pc = new ServiceUtilisateur();
if (pc.emailExists(email)) {
    // Show an error message to the user
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("An account with this email address already exists. Please use a different email address.");
    alert.showAndWait();
    return;
}
if (num_tel.trim().isEmpty()) {
    // Show an error message to the user
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter your phone number");
    alert.showAndWait();
    return;
}else if (username.length() < 1|| username.length() > 2){ // Show an error message to the user
    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("phone number should be between 1 and 2 characters.");
    alert.showAndWait();
    return;
}


  

    
    // All required fields are filled in, so proceed with saving the data
    Utilisateur u = new Utilisateur(id, username, password, email, num_tel);
    ServiceUtilisateur p = new ServiceUtilisateur();
    pc.ajouter(u);
}

    
    

      
}
