/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import Pidev1.AfficherCommandeController;
import com.sothawo.mapjfx.Projection;
import services.ServiceCommande;
import entities.Commande;
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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
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
    public TextField prix_tot1;

@FXML 
    private ChoiceBox <String> ChoiceBox;

    private final String [] paying_with ={"cash","Carte"};
 @FXML
    private DatePicker DatePicker;
 @FXML 
 private Button ok1;
    @FXML
    private Label date;
    @FXML
    private Button entertainement_btn;
    @FXML
    private Button event_btn;
    @FXML
    private Button produit_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button blog_btn;
    @FXML
    private Label username;
    @FXML
    private Button entertainement_btn1;
    @FXML
    private Button logoutbtn;
    

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
                Commande c = new Commande( UserSession.getId(),Double.valueOf(prix_tot1.getText()),meth_paiment.getText(), dateString);
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
        ChoiceBox.getScene().setRoot(rootNode);
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
               ChoiceBox.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
               ChoiceBox.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
        ChoiceBox.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 ChoiceBox.getScene().setRoot(rootNode);
        
    }


    @FXML
    private void afficheprofile(ActionEvent event) {
    }

   

    @FXML
    private void logout(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLhome.fxml"));	
                           Parent root = loader.load();	
                            FXMLhomeController scene2Controller = loader.getController();
                            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
                             ChoiceBox.getScene().setRoot(root);
    }
}

   

    
 

