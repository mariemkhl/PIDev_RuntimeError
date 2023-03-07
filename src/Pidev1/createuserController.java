/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import entities.Artist;
import entities.Customer;

import services.ServiceUtilisateur;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class createuserController implements Initializable {
    private Stage stage;
     private Scene scene;
     private Parent root;
    @FXML
    private Label adresse;
    @FXML
    private Label artistdomaine;
    @FXML
    private TextField artisttf;
    @FXML
    private TextField addresstf;
    @FXML
    private Label errorlabel;
    
    ServiceUtilisateur su=new ServiceUtilisateur();
    @FXML
    private CheckBox checkartist;
    @FXML
    private CheckBox customercheck;
    @FXML
    private TextField usernametf;
    @FXML
    private TextField phonetf;
    @FXML
    private TextField mailtf;
    @FXML
    private TextField pwtf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void CreateAccount(ActionEvent event) throws IOException{
        if (checkartist.isSelected()){
            Artist al=new Artist(usernametf.getText(),pwtf.getText(),mailtf.getText(),phonetf.getText(),artisttf.getText());
             su.ajouter(al);
             errorlabel.setText("Artist Added");
             errorlabel.setVisible(true);
        }
        if (customercheck.isSelected()){
            Customer c=new Customer(usernametf.getText(),pwtf.getText(),mailtf.getText(),phonetf.getText(),addresstf.getText());
             su.ajouter(c);
             errorlabel.setText("Customer Added");
             errorlabel.setVisible(true);
        }
        else {
            errorlabel.setText("please pick your account type");
            errorlabel.setVisible(true);
        }
    }

    @FXML
    private void artistchecked(ActionEvent event) {
        if (checkartist.isSelected()){
            adresse.setVisible(false);
            addresstf.setVisible(false);
            artistdomaine.setVisible(true);
            artisttf.setVisible(true);
        }
        else {
             adresse.setVisible(false);
            addresstf.setVisible(false);
            artistdomaine.setVisible(false);
            artisttf.setVisible(false);
        }
    }

    @FXML
    private void Customerchecked(ActionEvent event) {
            if (customercheck.isSelected()){
            adresse.setVisible(true);
            addresstf.setVisible(true);
            artistdomaine.setVisible(false);
            artisttf.setVisible(false);
        }
        else {
             adresse.setVisible(false);
            addresstf.setVisible(false);
            artistdomaine.setVisible(false);
            artisttf.setVisible(false);
        }
    }

    @FXML
    private void Back(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
    }
}
    
