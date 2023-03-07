/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import com.sothawo.mapjfx.Projection;
import entities.Category;
import entities.Product;
import java.io.IOException;
import services.CategoryService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 *
 * @author Nour Benkairia
 */
public class AjouterCategoryController implements Initializable {
    String old;
    @FXML
    private Button addCatBtn;
    @FXML
    private TextField NomCat;
    
    
      @FXML
    private TableView<Category> CategoryTable;
    @FXML
    private TableColumn<Category, String> nomCat;
    
CategoryService CatServ = new CategoryService();
Category collec = new Category();
    @FXML
    private Button ModifBtn;
    @FXML
    private Button SuppBtn;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      List<Category> cat = CatServ.getAll();
        ObservableList<Category> listCat = FXCollections.observableArrayList(cat);
         //idCat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        nomCat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CategoryTable.setItems(listCat);
    }
    
    @FXML
    public void AjouterCategory(Event event){
         Category c= new Category();
        c.setNom(NomCat.getText());
        CatServ.ajouter(c);
        reset();
         refresh();
}
     public void reset(){
//        idcat.setText("");
        NomCat.setText("");
       
        
    }
    
     
       @FXML
    private void getSelected(MouseEvent event) {
         int index = -1;
        index = CategoryTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        //idcat.setText(idCat.getCellData(index).toString());
        NomCat.setText(nomCat.getCellData(index).toString());
        old=nomCat.getCellData(index).toString();
        
    }

    @FXML
    private void supprimer(javafx.event.ActionEvent event) {
         //Category col= new Category();

        
        if (CategoryTable.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a Category from the table");
           alert.showAndWait();
        }else{
        
        System.out.println( CategoryTable.getSelectionModel().getSelectedItem()) ;
         Category c= new Category();

        String x=NomCat.getText();
        c.setNom(x);
        System.out.println(x);

        CatServ.supprimer(c);
         Alert confirmationDialog = new Alert(AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer le produit ? Cette action est irréversible.", ButtonType.YES, ButtonType.NO);
            confirmationDialog.setTitle("Confirmation de suppression");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.showAndWait();
    }
        
        refresh();
}
    
    
    

    
    

    @FXML
    private void modifier(javafx.event.ActionEvent event) {
        if (CategoryTable.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a Collection from the table");
           alert.showAndWait();
        }else{
        Category c= new Category();
        String x=NomCat.getText();
        c.setNom(x);
        
//        c.setNom(NomCat.getText());
//        String name=c.getNom();
       
        
        CatServ.modifier(c,old);
         Alert confirmationDialog = new Alert(AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir modifier le produit ? Cette action est irréversible.", ButtonType.YES, ButtonType.NO);
            confirmationDialog.setTitle("Confirmation de modification");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.showAndWait();
        reset();
    }
        refresh();
    }
    
    
     private void refresh(){
        
       List<Category> cat = CatServ.getAll();
        ObservableList<Category> listCategory = FXCollections.observableArrayList(cat);
         
        nomCat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CategoryTable.setItems(listCategory);
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
        CategoryTable.getScene().setRoot(rootNode);
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
               CategoryTable.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
             CategoryTable.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
        CategoryTable.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 CategoryTable.getScene().setRoot(rootNode);
        
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
                             CategoryTable.getScene().setRoot(root);
    }
     
     
     
}
