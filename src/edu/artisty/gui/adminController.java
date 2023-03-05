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
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class adminController implements Initializable {
    private Stage stage;
     private Scene scene;
     private Parent root;
    @FXML
    private Pane onquitadmin;
    @FXML
    private TextField adminnom;
    @FXML
    private TextField adminnumtel;
    @FXML
    private TextField adminemail;
    @FXML
    private TextArea result;
    @FXML
    private PasswordField adminmdp;
    @FXML
    private TextField adminmdp1;
    @FXML
    private CheckBox checkartist;
    @FXML
    private CheckBox customercheck;
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
    private TextField SearchBar;
    @FXML
    private TextField tfprenomad;
    @FXML
    private TextField tfemailad;
    @FXML
    private TextField tfnumtelad;
    @FXML
    private Button supration;
    @FXML
    private Button modifation;
    private Button bannation;
    @FXML
    private Button supration1;
    @FXML
    private Button supration2;
    @FXML
    private Button supration11;
    @FXML
    private Label adresse1;
    @FXML
    private Label artistdomaine1;
    @FXML
    private Label artisttf1;
    @FXML
    private Label addresstf1;
    ServiceUtilisateur su=new ServiceUtilisateur();
    @FXML
    private ListView<Utilisateur> myListView;
    public static boolean isValid(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    @FXML
    private AnchorPane chercher;
    @FXML
    private Button find;
    @FXML
    private TextField chercherbox;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
        myListView.getItems().addAll(su.getAll());
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
        }
    }       
    @FXML
    private void addemployee(ActionEvent event) throws MalformedURLException {
                     URL myurl=new URL("file:/C:/Users/khoul/OneDrive/Documents/NetBeansProjects/Artisty1/build/classes/edu/artisty/gui/admin_interface.fxml");

         if (checkartist.isSelected()){
            Artist al=new Artist(adminnom.getText(),adminmdp.getText(),adminemail.getText(),adminnumtel.getText(),artisttf.getText());
            if (isValid(al.getEmail())){
                su.ajouter(al);
             result.setText("Artist Added");
            myListView.getItems().clear();
            myListView.getItems().addAll(su.getAll());
            } 
            else{
                result.setText("Email n est pas saisie correctement");
            }
        }
        if (customercheck.isSelected()){
            Customer c=new Customer(adminnom.getText(),adminmdp.getText(),adminemail.getText(),adminnumtel.getText(),addresstf.getText());
             su.ajouter(c);
             result.setText("Customer Added");
            myListView.getItems().clear();
            myListView.getItems().addAll(su.getAll());
        }
         }

    @FXML
    private void clearfields(ActionEvent event) {
        adminnom.setText("");
        adminmdp.setText("");
        adminemail.setText("");
        adminnumtel.setText("");
        adresse.setVisible(false);
        addresstf.setVisible(false);
        artistdomaine.setVisible(false);
        artisttf.setVisible(false);
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
    private void delete(ActionEvent event) {
       Utilisateur selectedItem = myListView.getSelectionModel().getSelectedItem();
            tfprenomad.setText(selectedItem.getUsername());
            tfemailad.setText(selectedItem.getEmail());
            tfnumtelad.setText(selectedItem.getNum_tel());
            supration.setVisible(false);
            modifation.setVisible(false);
            bannation.setVisible(false);
            supration1.setVisible(true);
            supration2.setVisible(true);
    }

    @FXML
    private void update(ActionEvent event) {
        Utilisateur selectedItem = myListView.getSelectionModel().getSelectedItem();
            tfprenomad.setText(selectedItem.getUsername());
            tfemailad.setText(selectedItem.getEmail());
            tfnumtelad.setText(selectedItem.getNum_tel());
            supration.setVisible(false);
        modifation.setVisible(false);
        bannation.setVisible(false);
        supration11.setVisible(true);
        supration2.setVisible(true);
    }

    @FXML
    private void delete1(ActionEvent event) throws MalformedURLException {
        URL myurl=new URL("file:/C:/Users/khoul/OneDrive/Documents/NetBeansProjects/Artisty1/build/classes/edu/artisty/gui/admin_interface.fxml");
        Utilisateur u=new Utilisateur(tfprenomad.getText(),tfemailad.getText(),tfnumtelad.getText());
        su.supprimer(u);
         myListView.getItems().clear();
         myListView.getItems().addAll(su.getAll());
        tfprenomad.setText("");
        tfemailad.setText("");
        tfnumtelad.setText("");
         supration.setVisible(true);
         modifation.setVisible(true);
         bannation.setVisible(true);
         supration1.setVisible(false);
         supration2.setVisible(false);
    }

    @FXML
    private void delete2(ActionEvent event) {
        tfprenomad.setText("");
        tfemailad.setText("");
        tfnumtelad.setText("");
        supration.setVisible(true);
        modifation.setVisible(true);
        bannation.setVisible(true);
        supration1.setVisible(false);
        supration2.setVisible(false);
        supration11.setVisible(false);
    }

    @FXML
    private void modif1(ActionEvent event) throws MalformedURLException {

        Utilisateur u=new Utilisateur(tfprenomad.getText(),tfemailad.getText(),tfnumtelad.getText());
        su.modifier(u,u.getEmail());
             myListView.getItems().clear();
            myListView.getItems().addAll(su.getAll());
        tfprenomad.setText("");
        tfemailad.setText("");
        tfnumtelad.setText("");
         supration.setVisible(true);
         modifation.setVisible(true);
         bannation.setVisible(true);
         supration1.setVisible(false);
         supration2.setVisible(false);
         supration11.setVisible(false);
         
    }

    @FXML
    
    private void onquitad(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
    }

    @FXML
  private void chercher1(ActionEvent event) {
        String s =chercherbox.getText();
              Utilisateur p= new Utilisateur();
         
         if(su.getAllById(s)!=null){
         System.out.println(su.getAllById(s));
         Utilisateur Utilisateur = su.getAllById(s);
       
         ObservableList<Utilisateur> utiltList = FXCollections.observableArrayList(Utilisateur);
 
         myListView.setItems(utiltList);
        
         }
   }
}
    
   
 
    
    
