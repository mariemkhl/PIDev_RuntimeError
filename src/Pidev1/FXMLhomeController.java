/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.Projection;
import entities.Artist;
import entities.Customer;
import entities.Utilisateur;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khoul
 */
/**
     * @param args the command line arguments
     */
public class FXMLhomeController  implements Initializable  {
    
    private Stage stage;
     private Scene scene;
     private Parent root;

    @FXML
    private Button produit_btn;
    private Label username;
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
    @FXML
    private Button entertainement_btn2;
    @FXML
    private Button event_btn1;
    @FXML
    private Button produit_btn1;
    @FXML
    private Button home_btn1;
    @FXML
    private Button blog_btn1;
    @FXML
    private Label username1;
    @FXML
    private Button entertainement_btn11;
    @FXML
    private Button logoutbtn1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(UserSession.getId());
    }    
    
    public void getName(String username){
    username1.setText(username);
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

    @FXML
    private void gestionmap(ActionEvent event) throws IOException {
        String fxmlFile = "FXMLDocument.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
       // logger.trace("stage loaded");

        final Controller controller = fxmlLoader.getController();
        
        Projection projection= Projection.WEB_MERCATOR;
        controller.initMapAndControls(projection);
        tf5.getScene().setRoot(rootNode);
        /*fxmlLoader.getController();
          buttonZoom.setOnAction(event -> mapView.setZoom(ZOOM_DEFAULT));
        sliderZoom.valueProperty().bindBidirectional(mapView.zoomProperty());
         mapView.setCenter(new Coordinate(36.8510414,10.159122));
        mapView.initialize(Configuration.builder()
            .projection(projection)
            .showZoomControls(false)
            .build());*/
        
    }

    @FXML
    private void goproduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterProduct.fxml"));
               tf5.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
               tf5.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
        tf5.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 tf5.getScene().setRoot(rootNode);
        
    }

    
}
