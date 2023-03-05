/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Events;
import edu.esprit.entities.Reservation;
import edu.esprit.services.ServiceEvents;
import edu.esprit.services.ServiceReservation;
import edu.esprit.utils.MyConnection;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherReservationController implements Initializable {


    private Label tfname;
    @FXML
    private Button btndelete;
    @FXML
    private Button btnupdate;
    private TableView<Reservation> tvres;
    private TableColumn<Reservation, Integer> colevent;
    @FXML
    private Button btnrefresh;
    @FXML
    private ListView<Reservation> listeres;
    @FXML
    private ScrollPane scres;
    @FXML
    private VBox vbox;
    @FXML
    private AnchorPane ev;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    // int user;
//          //ServiceReservation sr = new ServiceReservation();
//       public void setUser_ID(int user_id){
//           user_id= Integer.valueOf(tfiduser.getText());
//   
//       }

    public void setName(String name) {
        tfname.setText(name);
    }  


    @FXML
    private void showaction(ActionEvent event) {
    
   
        List<Reservation> even = new ArrayList();
        ServiceReservation se = new ServiceReservation();
        listeres.getItems();
        even = se.getAll();
        //  System.out.println(ev);
        int x = 0, y = 0;
        // listevent.getItems().addAll(even);
        for (Reservation e : even) {

            AnchorPane an = new AnchorPane();
            an.setLayoutX(x);
            an.setLayoutY(y);

            Label name = new Label(e.getName());
            name.setLayoutX(x + 55);
            name.setLayoutY(y + 20);
            String d = String.valueOf(e.getDateRE());
            Label date = new Label(d);
            date.setLayoutX(x +248 );
            date.setLayoutY(y + 20);
            
            Button btnaffiche = new Button("Affichage");

            an.getChildren().addAll(name, date);
            ev.getChildren().addAll(an);

            vbox.getChildren().add(an);

        }
    }

    @FXML
    private void deleteaction(ActionEvent event) {
        
        ServiceReservation se = new ServiceReservation();

        if (listeres.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Reservation from the table AND click 3 times to delete the reservation");
            alert.showAndWait();
        } else {
 
       listeres.setOnMouseClicked(e -> {
    if (e.getClickCount() == 3) {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce produit ?");
        confirmationAlert.setContentText("Cette action ne peut pas être annulée !");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
              Reservation selectedReservation = listeres.getSelectionModel().getSelectedItem();
        if ( selectedReservation!= null) {
            
            se.supprimer(selectedReservation.getId_res());

            
                
                listeres.getItems().remove(selectedReservation);
               }else{}
  
    }
               }
       });
   
               }
    }
    

    @FXML
    private void updateaction(ActionEvent event) {
    }
    
    

  



    }