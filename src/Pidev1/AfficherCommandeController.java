package Pidev1;

import Pidev1.PayingController;
import com.sothawo.mapjfx.Projection;
import entities.Commande;
import services.ServiceCommande;
import utils.DataConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * This class is responsible for displaying details about a customer's order.
 */
public class AfficherCommandeController implements Initializable {

    private Label idcommande;

    private Label userid;

    @FXML
    private Label meth_paiment;

    @FXML
    private Label prix_tot;


     @FXML
    private Button passer_paiment;
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
    @FXML
    private Button voir_plus;
    @FXML
    private Button retour;
    

    
    @FXML
    void retour(ActionEvent event) throws IOException {
        
FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
                Parent root = loader.load();
                userid.getScene().setRoot(root);
    }

    @FXML
    void passer_paiment(ActionEvent event)  {
        
        try{
  FXMLLoader loader = new FXMLLoader(getClass().getResource("paying.fxml"));
                Parent root = loader.load();
                meth_paiment.getScene().setRoot(root);
                PayingController pc = loader.getController();
                pc.setMeth_Paiment(meth_paiment.getText());
                pc.setPrix_Totcarte(Integer.valueOf(prix_tot.getText()));
                pc.setPrix_Totcash(Integer.valueOf(prix_tot.getText()));

                
        }
                
         catch (IOException ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
                a.showAndWait();
                
            }    
        }
    
//                 @FXML
//    void supprimer(ActionEvent event) {
//        
//        int index = Integer.valueOf(idcommande.getText());
//        ServiceCommande sc = new ServiceCommande();
//        sc.supprimer(index );
//        Alert a = new Alert(Alert.AlertType.INFORMATION, "Commande supprimée !", ButtonType.FINISH);
//        a.show();
//     

//    @FXML
//    void modifier(ActionEvent event) {
//   
//          try {
//                
//                ServiceCommande sc = new ServiceCommande();
//                int index = Integer.valueOf(idcommande.getText());         
//                Commande c = new Commande( userid.getText(),Integer.valueOf(prix_tot.getText()));
//                sc.modifier(c,index);
//                Alert a = new Alert(Alert.AlertType.INFORMATION, "Commande updated !", ButtonType.OK);
//                a.showAndWait();
//                
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("paying.fxml"));
//                Parent root = loader.load();
//                prix_tot.getScene().setRoot(root);
//                                             
//                } catch (IOException ex) {
//                Alert a = new Alert(Alert.AlertType.ERROR, ex.getMessage(), ButtonType.OK);
//                a.showAndWait();
//            }    
//   }
    
    @FXML
    void voir_plus(ActionEvent event) throws IOException {
        
                FXMLLoader loader = new FXMLLoader(getClass().getResource("list.fxml"));
                Parent root = loader.load();
                userid.getScene().setRoot(root);
                
                               
    }
    



    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
                // TODO
    }

    public void setMeth_Paiment(String meth_paiment) {
        this.meth_paiment.setText(meth_paiment);
    }

    public void setidcommande(int idcommande) {
        this.idcommande.setText(Integer.toString(idcommande));
    }

    public void setPrix_Tot(int prix_tot) {
        this.prix_tot.setText(Integer.toString(prix_tot));
    }


    void setuserid(String userid) {
    this.userid.setText(userid);  
            
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
        meth_paiment.getScene().setRoot(rootNode);
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
               meth_paiment.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
               meth_paiment.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
        meth_paiment.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 meth_paiment.getScene().setRoot(rootNode);
        
    }


    @FXML
    private void afficheprofile(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLhome.fxml"));	
                           Parent root = loader.load();	
                            FXMLhomeController scene2Controller = loader.getController();
                            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
                             meth_paiment.getScene().setRoot(root);
    }

    

    @FXML
    private void logout(ActionEvent event) {
    }

   

}