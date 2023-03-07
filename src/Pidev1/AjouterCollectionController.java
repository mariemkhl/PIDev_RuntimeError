/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import com.sothawo.mapjfx.Projection;
import entities.Collection;

import services.CollectionService;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static java.util.ServiceLoader.load;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nour Benkairia
 */
public class AjouterCollectionController implements Initializable {

    @FXML
    private Button BtnAjout;
    @FXML
    private TextField ColNameID;
    @FXML
    private TextField ProdID;
    @FXML
    private TextField ProdNameID;
    @FXML
    private Label labelNameCol;
    @FXML
    private Label labelProdId;
    @FXML
    private Label labelProdName;

    /**
     * Initializes the controller class.
     */
 //   AfficherCollectionController affcol = new AfficherCollectionController();
    CollectionService ColServ = new CollectionService();
    Collection collec = new Collection();
    @FXML
    private AnchorPane AddBtnID;
    @FXML
    private Button AfficheBtn;
    @FXML
    private TableView<Collection> collectionTable;
    @FXML
    private TableColumn<Collection, String> NomCol;
    @FXML
    private TableColumn<Collection, Integer> refProd;
    @FXML
    private TableColumn<Collection, String> NomProd;
    @FXML
    private TableColumn<Collection, Integer> idCollec;
    @FXML
    private TextField ifid;
    @FXML
    private Button logoutbtn1;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       List<Collection> col = ColServ.getAll();
        ObservableList<Collection> listCollection = FXCollections.observableArrayList(col);
         idCollec.setCellValueFactory(new PropertyValueFactory<>("id_col"));
        NomCol.setCellValueFactory(new PropertyValueFactory<>("nom_col"));
        refProd.setCellValueFactory(new PropertyValueFactory<>("id_p"));
        NomProd.setCellValueFactory(new PropertyValueFactory<>("nom_p"));
        collectionTable.setItems(listCollection);
         
    }
      


        
   
    
    @FXML
    public void AjouterCollection(Event event){
         Collection c= new Collection();
        c.setNom_col(ColNameID.getText());
        c.setId_p(Integer.valueOf(ProdID.getText()));
        c.setNom_p(ProdNameID.getText());
        ColServ.ajouter(c);
        reset();
}
    
    @FXML
    public void AfficheCollection(Event event){
         try {
              Parent loader = FXMLLoader.load(getClass().getResource("../gui/AfficherCollection.fxml"));   
              Scene scene = new Scene(loader, 600, 400);
              Stage stage= new Stage();
              stage.setScene(scene);
              stage.show();
             } catch (IOException ex) {
                 System.out.println("ERRRORRR WRITE EXCEPTION QR");
             }
        
    }
    
    public void reset(){
        ColNameID.setText("");
        ProdID.setText("");
        ProdNameID.setText("");
    }
   // fx:id="AddBtn"

    @FXML
    private void getSelected(MouseEvent event) {
         int index = -1;
        index = collectionTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        ifid.setText(idCollec.getCellData(index).toString());
        ColNameID.setText(NomCol.getCellData(index).toString());
        ProdID.setText(refProd.getCellData(index).toString());
        ProdNameID.setText(NomProd.getCellData(index).toString());
    }

    @FXML
    private void supprimer(javafx.event.ActionEvent event) {
         Collection col= new Collection();
//       c.getNom_col();
//        c.getId_p();
//        c.getNom_p();
//         ObservableList<Collection> AllCollection,SingleCollection;
//         AllCollection=collectionTable.getItems();
//         SingleCollection=collectionTable.getSelectionModel().getSelectedItems();
//         SingleCollection.forEach(AllCollection::remove);
//        int selectedID = collectionTable.getSelectionModel().getSelectedIndex();
//        System.out.println(selectedID);
//        colServ.supprimer(selectedID);

//        int x=collectionTable.getSelectionModel().getSelectedIndex();
//        String nomcol = NomCol.getCellData(x);
//       System.out.println(colServ.getOneByNom(nomcol));
//        int id=Integer.parseInt(idcol);
//        col.setId_col(id);
//        System.out.println(id);
        
        
        if (collectionTable.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a Collection from the table");
           alert.showAndWait();
        }else{
        
        System.out.println( collectionTable.getSelectionModel().getSelectedItem()) ;
         Collection c= new Collection();
//        c.setDepartC(TextDepart.getText());
//        System.out.println( TextDepart.getText()) ;
//        c.setArriveeC(TextArrivee.getText());
//        System.out.println( TextArrivee.getText()) ;
        int x=Integer.parseInt(ifid.getText());
        c.setId_col(x);
        System.out.println(x);

        ColServ.supprimer(c);
    }
}

    @FXML
    private void modifier(javafx.event.ActionEvent event) {
        if (collectionTable.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a Collection from the table");
           alert.showAndWait();
        }else{
        Collection c= new Collection();
        //c.setDepartC(TextDepart.getText());
        //System.out.println(c.getDepartC());
        //c.setArriveeC(TextArrivee.getText()); 
       
       // c.getIdCircuit(ifid.getText());
        int x=Integer.parseInt(ifid.getText());
        c.setId_col(x);
        //System.out.println(x);
        c.setNom_col(ColNameID.getText());
        String name=c.getNom_col();
        c.setId_p(Integer.parseInt(ProdID.getText())); 
        int namep=c.getId_p();
        c.setNom_p(ProdNameID.getText());
         String idp=c.getNom_p();
        
        ColServ.modifier(c);
        reset();
    }
    }

    @FXML
    private void logout(javafx.event.ActionEvent event) {
    }

    @FXML
    private void gestionmap(javafx.event.ActionEvent event) throws IOException {
        String fxmlFile = "FXMLDocument.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
       // logger.trace("stage loaded");

        final Controller controller = fxmlLoader.getController();
        
        Projection projection= Projection.WEB_MERCATOR;
        controller.initMapAndControls(projection);
        ColNameID.getScene().setRoot(rootNode);
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
    private void goproduit(javafx.event.ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterProduct.fxml"));
               ColNameID.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(javafx.event.ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
              ColNameID.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(javafx.event.ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
        ColNameID.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(javafx.event.ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 ColNameID.getScene().setRoot(rootNode);
        
    }


    @FXML
    private void afficheprofile(javafx.event.ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLhome.fxml"));	
                           Parent root = loader.load();	
                            FXMLhomeController scene2Controller = loader.getController();
                            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
                             ColNameID.getScene().setRoot(root);
    }

    
}
