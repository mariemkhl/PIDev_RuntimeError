/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

//import com.itextpdf.text.Image;
import edu.esprit.entities.Events;
import edu.esprit.services.ServiceEvents;
import edu.esprit.utils.MyConnection;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
//import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterEventsController implements Initializable {

    @FXML
    private DatePicker dpDate;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfLocation;
    @FXML
    private Button btnadd;
    @FXML
    public TextField tfnbplace;
    @FXML
    private ChoiceBox<String> Mychoicebox;
    private final String[] category = {"online", "cinematic", "literature", "theatre", "salle_exposition_des_tableaux", "salle_exposition_des_sculpture"};
    @FXML
    private Label Mylabel;
    @FXML
    public TextField tfuser;
    @FXML
    public Button bntshow;
    @FXML
    private AnchorPane main_form;
    @FXML
    public ImageView imageview_event;
    @FXML
    public Label lbevent;

//    private Filechooser Filechooser;
//    private File file;
//    
//   Filechooser filechooser= new Filechooser();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Mychoicebox.getItems().addAll(category);
        Mychoicebox.setOnAction(this::getCategory);
    }
    Connection cnx = MyConnection.getInstance().getCnx();

    public void getCategory(ActionEvent event) {
        String myCategory = Mychoicebox.getValue();
        Mylabel.setText(myCategory);

    }

    @FXML
    private void getDate_event(ActionEvent event) {

    }

    @FXML
    private void add_event(ActionEvent event) throws IOException, SQLException {
        Date date = java.sql.Date.valueOf(dpDate.getValue());
        String nameEv = tfName.getText();
        int userid = Integer.valueOf(tfuser.getText());
        String location = tfLocation.getText();
        int nbplace = Integer.valueOf(tfnbplace.getText());
        String myCategory = Mychoicebox.getValue();

        String req1 = "SELECT `id_event`, `nameEv`, `date_event`, `location`, `id_user`, `categorie`, `nbplacetotal`, `img` FROM `events` WHERE nameEv ='" + tfName.getText().isEmpty() + "'";

        if (tfName.getText().isEmpty() || tfLocation.getText().isEmpty() || tfnbplace.getText().isEmpty() || tfuser.getText().isEmpty() || GetData.path == null) {
            Alert a = new Alert(Alert.AlertType.ERROR);
//          
            a.setHeaderText(null);
            a.setHeaderText("Check you information");
            a.showAndWait();
        } else if (dpDate.getValue().getYear() < 2023) {
            Alert al2 = new Alert(Alert.AlertType.ERROR);
            al2.setHeaderText(null);
            al2.setContentText("Current day please");
            al2.showAndWait();

        } else {
            try (Statement statement = cnx.createStatement();
                    ResultSet result = statement.executeQuery(req1)) {

                if (result.next()) {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("nameEv :" + tfName.getText() + " already exist!");
                    alert.showAndWait();
                } else {
                    ServiceEvents se = new ServiceEvents();

                    Events e = new Events(nameEv, date, location, userid, myCategory, nbplace, GetData.path);
                    se.ajouter(e);
                     tfName.setText("");
        tfLocation.setText("");
         tfnbplace.setText("");
          tfuser.setText("");
          
          dpDate.valueProperty().setValue(null);
                    

                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Success");
                    alert.setHeaderText(null);
                    alert.setContentText("event created !");
                    alert.showAndWait();

                }
            } catch (SQLException e) {
                // Handle exception
            }
//            
//            

        }

    }
   
    @FXML
    private void show_event(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherEvents.fxml"));
        Parent root = loader.load();
        tfName.getScene().setRoot(root);
//                AfficherEventsController aec = loader.getController();
//                aec.setName(tfName.getText());
//                aec.setLocation (tfLocation.getText());

    }

    @FXML
    private void EnsertImage(ActionEvent event) {

        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            GetData.path = file.getAbsolutePath();
            //ImageView imageview_event = new ImageView();

            Image img = new Image(file.toURI().toString(), 215, 173, false, true);
            imageview_event.setImage(img);
        }
    }

    


}
