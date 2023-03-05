/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.entities.Artist;
import edu.artisty.entities.Customer;
import edu.artisty.entities.Utilisateur;
import edu.artisty.services.ServiceUtilisateur;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class FXMLhomeController implements Initializable {
    private Stage stage;
     private Scene scene;
     private Parent root;

    @FXML
    private Button home_btn;
    @FXML
    private Button blog_btn;
    @FXML
    private Button logoutbtn;
    @FXML
    private Button produit_btn;
    @FXML
    private Button event_btn;
    @FXML
    private Label username;
    @FXML
    private Button entertainement_btn;
    @FXML
    private AnchorPane hotarticle;
    
    
    public Utilisateur u;
    @FXML
    private TextField tf1;
    @FXML
    private TextField tf2;
    @FXML
    private TextField tf3;
    @FXML
    private TextField tf4;
    @FXML
    private TextField tf5;
    @FXML
    private Label emaillabel;
    
    ServiceUtilisateur su = new ServiceUtilisateur();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
    public void getName(String username1){
    username.setText(username1);
}
    
    public void getEmail(String email){
    emaillabel.setText(email);
}
    
    
    @FXML
    private void afficheprofile(ActionEvent event) {
        hotarticle.setVisible(true);
        Utilisateur u = su.getAllById(emaillabel.getText());
        if (u instanceof Artist){
            tf1.setText(u.getUsername());
            tf2.setText(u.getEmail());
            tf4.setText(((Artist) u).getDomaine());
            tf3.setVisible(false);
            tf5.setText(u.getNum_tel());
        }
        if (u instanceof Customer){
            tf1.setText(u.getUsername());
            tf2.setText(u.getEmail());
            tf3.setText(((Customer) u).getAdresse());
            tf4.setVisible(false);
            tf5.setText(u.getNum_tel());
        }
    }

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignIn.fxml"));                       
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void modify(ActionEvent event) {
        Utilisateur u = su.getAllById(emaillabel.getText());
        
        if (u instanceof Artist){
            Artist u1=new Artist();
            u1.setUsername(tf1.getText());
            u1.setEmail(tf2.getText());
            ((Artist) u1).setDomaine(tf4.getText());
            u1.setNum_tel(tf5.getText());
            su.modifier(u1, emaillabel.getText());
        }
        if (u instanceof Customer){
            Customer u1=new Customer();
            u1.setUsername(tf1.getText());
            u1.setEmail(tf2.getText());
            ((Customer) u1).setAdresse(tf3.getText());
            u1.setNum_tel(tf5.getText());
            su.modifier(u1, emaillabel.getText());
        }
    }

    @FXML
    private void supprimy(ActionEvent event) throws IOException {
        Utilisateur u = su.getAllById(emaillabel.getText());
        su.supprimer(u);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignIn.fxml"));                 
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setTitle("");
        stage.setScene(scene);
        stage.show();
    }
}
