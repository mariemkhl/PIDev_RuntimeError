/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.entities.Category;
import edu.artisty.entities.Product;
import edu.artisty.services.CategoryService;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private TextField idcat;
    @FXML
    private TextField NomCat;
    
    
      @FXML
    private TableView<Category> CategoryTable;
    @FXML
    private TableColumn<Category, String> nomCat;
    @FXML
    private TableColumn<Category, Integer> idCat;
    
CategoryService CatServ = new CategoryService();
Category collec = new Category();
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
     
     
     
}
