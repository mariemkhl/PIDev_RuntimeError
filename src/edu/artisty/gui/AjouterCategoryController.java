/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.entities.Category;
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
import javafx.scene.control.Button;
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
         idCat.setCellValueFactory(new PropertyValueFactory<>("id_cat"));
        nomCat.setCellValueFactory(new PropertyValueFactory<>("nom"));
        CategoryTable.setItems(listCat);
    }
    
    @FXML
    public void AjouterCategory(Event event){
         Category c= new Category();
        c.setNom(NomCat.getText());
        CatServ.ajouter(c);
        reset();
}
     public void reset(){
        idcat.setText("");
        NomCat.setText("");
        
    }
    
     
       @FXML
    private void getSelected(MouseEvent event) {
         int index = -1;
        index = CategoryTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        idcat.setText(idCat.getCellData(index).toString());
        NomCat.setText(nomCat.getCellData(index).toString());
        
    }

    @FXML
    private void supprimer(javafx.event.ActionEvent event) {
         Category col= new Category();

        
        if (CategoryTable.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a Category from the table");
           alert.showAndWait();
        }else{
        
        System.out.println( CategoryTable.getSelectionModel().getSelectedItem()) ;
         Category c= new Category();
//        c.setDepartC(TextDepart.getText());
//        System.out.println( TextDepart.getText()) ;
//        c.setArriveeC(TextArrivee.getText());
//        System.out.println( TextArrivee.getText()) ;
        int x=Integer.parseInt(idcat.getText());
        c.setId_cat(x);
        System.out.println(x);

        CatServ.supprimer(c);
    }
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
        int x=Integer.parseInt(idcat.getText());
        c.setId_cat(x);
        
        c.setNom(NomCat.getText());
        String name=c.getNom();
       
        
        CatServ.modifier(c);
        reset();
    }
    }
     
     
     
}
