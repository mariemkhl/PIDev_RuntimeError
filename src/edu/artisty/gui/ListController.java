/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.entities.CommandeListCell;
import edu.artisty.entities.Commande;
import edu.artisty.services.IService;
import edu.artisty.services.ServiceCommande;
import edu.artisty.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import static org.apache.spark.metrics.source.HiveCatalogMetrics.reset;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class ListController {

    @FXML
    private Button quitter1;

    @FXML
    private Button supprimer1;

    @FXML
    private Button update1;

    @FXML
    private Button ajouter1;


    @FXML
    private Label lpayment;

    @FXML
    private Button Find;

    @FXML
    private Button affich;
        @FXML
    private TextField tfp;

    @FXML
    private Label lprix_tot;
    
    @FXML 
    private ListView <Commande> list ;
    @FXML
    private Label luserid;
    @FXML
    private Button passer_pay;


    @FXML
    void ajouter()throws IOException {
        
FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
                Parent root = loader.load();
                quitter1.getScene().setRoot(root);
    
    }
    
    

    @FXML
    void retour1 ()throws IOException {
        
FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
                Parent root = loader.load();
                quitter1.getScene().setRoot(root);
    }

    @FXML
    void supprimer1(ActionEvent event) {
    Commande commande = list.getSelectionModel().getSelectedItem();
    if (commande == null) {
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("Aucune commande sélectionnée");
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner une commande dans le tableau.");
        alert.showAndWait();
        }  else {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de suppression");
        alert.setHeaderText(null);
        alert.setContentText("Voulez-vous vraiment supprimer la commande sélectionnée ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            ServiceCommande service = new ServiceCommande();
            service.supprimer(commande);
            Alert confirmationDialog = new Alert(AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer votre commande ? Cette action est irréversible.", ButtonType.YES, ButtonType.NO);
            confirmationDialog.setTitle("Confirmation de suppression");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.showAndWait();
        }
        
                    }

    }


@FXML
void update1(ActionEvent event) throws IOException {
    int index = list.getSelectionModel().getSelectedIndex();
    if (index != -1) { 
        Commande selectedCommande = list.getItems().get(index);
        selectedCommande.setPayment(lpayment.getText());
        IService service = new ServiceCommande();
        service.modifier(selectedCommande);
        // refresh the list view to reflect the changes
        list.refresh();
        
       FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
                Parent root = loader.load();
                quitter1.getScene().setRoot(root); 
                AjouterCommandeController acc = loader.getController();
                acc.tfuserid.setText(selectedCommande.getuserid());
                acc.prix_tot1.setText(String.valueOf(selectedCommande.getPrix_tot())); // Update the value as a String
                acc.meth_paiment.setText(selectedCommande.getPayment());
        
    }              
}

ServiceCommande sc = new ServiceCommande ();
@FXML
void rechercherPaiment(ActionEvent event) {
    
    String s =tfp.getText();
              Commande c= new Commande();
              
         
         if(sc.rechercheParpaiment(s)!=null){
         System.out.println(sc.rechercheParpaiment(s));
         Commande commande = sc.rechercheParpaiment(s);
       
         ObservableList<Commande> CList = FXCollections.observableArrayList(commande);
 
         list.setItems(CList);
    
//     
//    ServiceCommande sc = new ServiceCommande();
//    ObservableList<Commande> liste = sc.rechercheParpaiment(lpayment.getText());
//    if (liste.isEmpty()) {
//        System.out.println("No results found for payment: " + lpayment.getText());
//    } else {
//        list.setItems(liste);
//        list.setCellFactory(param -> new ListCell<Commande>() {
//            @Override
//            protected void updateItem(Commande item, boolean empty) {
//                super.updateItem(item, empty);
//                if (empty || item == null) {
//                    setText(null);
//                } else {
//                    setText(item.getuserid() + " - " + item.getPayment() + " - " + item.getDate_creation());
//                }
//            }
//        });
//        list.refresh();
//    }
}
}





    /**
     * Initializes the controller class.
     */

    public void initialize(URL url, ResourceBundle rb) {
             // ListView<Product> Plist = new ListView<>();
              list.setCellFactory(lv -> new CommandeListCell());
              


try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/artisty", "root", "")) {
    String query = "SELECT idcommande, prix_tot, userid, payment, date_creation FROM commande";
    try (PreparedStatement stmt = conn.prepareStatement(query); 
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int idcommande = rs.getInt("idcommande");
            int prix_tot = rs.getInt("prix_tot");
            String userid = rs.getString("userid");
            String payment = rs.getString("payment");
            String date_creation =rs.getNString("date_creation");
             
            Commande c = new Commande(idcommande, prix_tot,userid, payment, date_creation);
            list.getItems().add(c);
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
        // TODO
    }   
  
 
    void setMeth_Paiment(String paiment) {
        this.lpayment.setText(paiment);
    }
    
    

 
   @FXML
void afficher() {
    ServiceCommande sc = new ServiceCommande();
    ObservableList<Commande> liste = FXCollections.observableArrayList(sc.getAll());
    list.setItems(liste);
    list.setCellFactory(param -> new ListCell<Commande>() {
        @Override
        protected void updateItem(Commande item, boolean empty) {
            super.updateItem(item, empty);
            if (empty || item == null) {
                setText(null);
            } else {
                setText(item.getId()+ " - " +item.getPrix_tot()+" - "+item.getuserid()+ " - " + item.getPayment()+ " - " + item.getDate_creation());
            }
        }
    });
}

   

}



   


