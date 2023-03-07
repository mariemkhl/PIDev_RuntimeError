/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import edu.esprit.entities.Events;
import edu.esprit.services.ServiceEvents;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficherEventsController implements Initializable {

    private Label lname;
    
    private VBox vbox;
    private AnchorPane ev;
    @FXML
    private ScrollPane spe;
    @FXML
    private VBox vbe;
    @FXML
       private  AnchorPane main_form;
   // private AnchorPane main_form;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showeven();

    }


    private void returnaction(ActionEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvents.fxml"));
        Parent root = loader.load();
        lname.getScene().setRoot(root);
    }

   
    private void showeven() {
        vbe.getChildren().clear();
       // System.out.println("sxwscdf");
        List<Events> even = new ArrayList();       
        ServiceEvents se = new ServiceEvents();
       // listevent.getItems();
        even = se.getAll();
                    System.out.println(even);

        //  System.out.println(ev);
        int x = 50, y = 50;
        
        
        // listevent.getItems().addAll(even);
        for (int i=0;i<even.size();i++) {
            AnchorPane an = new AnchorPane();
            
            an.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #d4d4d4);"
                    + " -fx-border: 12px solid; -fx-border-color: white;");
            an.setLayoutX(x);
            an.setLayoutY(y);
           
            Label name = new Label(even.get(i).getNameEv());
            name.setLayoutX(x +349);
            name.setLayoutY(y -45);
           name.setStyle(" -fx-font : 34px Georgia ;");
            an.getChildren().add(name);
            
            String d = String.valueOf(even.get(i).getDate_event());
            Label date = new Label(d);
            date.setLayoutX(x + 340);
            date.setLayoutY(y + 110);
             date.setStyle(" -fx-font : 18px Arial ;");
            an.getChildren().add(date);
            
            Label location = new Label(even.get(i).getLocation());
            location.setLayoutX(x + 48);
            location.setLayoutY(y + 50);
             location.setStyle(" -fx-font : 20px Arial ;");
            an.getChildren().add(location);
            
            String u = String.valueOf(even.get(i).getId_user());
            Label user = new Label(u);
            user.setLayoutX(x + 235);
            user.setLayoutY(y + 50);
             user.setStyle(" -fx-font : 20px Arial ;");
            an.getChildren().add(user);
            
            
            Label category = new Label(even.get(i).getCategorie());
            category.setLayoutX(x + 345);
            category.setLayoutY(y + 50);
             category.setStyle(" -fx-font : 20px Arial ;");
            an.getChildren().add(category);
            
            String nb = String.valueOf(even.get(i).getNbplacetotal());
            Label nbreplace = new Label(nb);
            nbreplace.setLayoutX(x + 500);
            nbreplace.setLayoutY(y + 50);
             nbreplace.setStyle(" -fx-font : 20px Arial ;");
            an.getChildren().add(nbreplace);
            
            
              Label lb = new Label (); 
            Button btnapi= new Button();
                btnapi.setText("Update");
                btnapi.setLayoutX(x + 300);
                btnapi.setLayoutY(lb.getLayoutY() + 130);
                an.getChildren().add(btnapi);
                btnapi.setStyle(  "    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
                
                        an.setVisible(true);
            vbe.getChildren().add(an);
            File file = new File (even.get(i).getImg() );
            Image img = new Image(  file.toURI().toString() ,215,173, false , true);
            ImageView imageview = new ImageView(img);
            imageview.setFitHeight(250);
            imageview.setFitWidth(250);
            imageview.setPreserveRatio(true);
            imageview.setLayoutX(x+660);
            imageview.setLayoutY(lb.getLayoutY()+5);
            an.getChildren().add(imageview);
                
              //  Events a = new Events(Integer.valueOf(even.get(i).getId_event()), even.get(i).getNameEv(), even.get(i).getDate_event(), even.get(i).getLocation(), Integer.valueOf(even.get(i).getId_user()), even.get(i).getCategorie(), Integer.valueOf(even.get(i).getNbplacetotal()));
            Events a = new Events (Integer.valueOf(even.get(i).getId_event()), even.get(i).getNameEv(),even.get(i).getDate_event(),even.get(i).getLocation(), Integer.valueOf(even.get(i).getId_user()), even.get(i).getCategorie() , Integer.valueOf(even.get(i).getNbplacetotal()),even.get(i).getImg());
            GetData.path = a.getImg();
               
                btnapi.setOnAction(new EventHandler(){
                 public void handle(Event event){
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterEvents.fxml"));
                
                     try {
                         Parent root = loader.load();
                          spe.getScene().setRoot(root)  ;
                     } catch (IOException ex) {
                         Logger.getLogger(AfficherEventsController.class.getName()).log(Level.SEVERE, null, ex);
                     }
               
                AjouterEventsController ae = loader.getController();
           
                   
                    spe.setVisible(false);
                  //  content2.setText(a.getContentArticle());
           ae.tfName.setText(a.getNameEv());
           ae.tfLocation.setText(a.getLocation());
           ae.tfnbplace.setText(String.valueOf(a.getNbplacetotal()));
           ae.lbevent.setId(String.valueOf(a.getId_event()));
                    
//                    hiddenIdArticle.setText(String.valueOf(a.getIdArticle()));
//                    CommentaireService cs = new CommentaireService();
//                    System.out.println("Cms: " + cs.getCommentsByArticle(a.getIdArticle()));
//                    List<Commentaire> aComments = cs.getCommentsByArticle(a.getIdArticle());
//                    for (int i = 0; i < aComments.size(); ++i) {
//                        vboxcomment.getChildren().add(new Label(aComments.get(i).getContentCommentaire()));
//                    }
//                    commentaireScrollPane.setContent(vboxcomment);
////                    pn.getChildren().add(lb);
//                    ImageView imageview_article2 = new ImageView(img);
//
//                    AnP.getChildren().add(imageview_article2);
//                    //  CommentaireService cs = new CommentaireService();
//                    Date d = Date.valueOf(LocalDate.now());
//                    spe.setId(String.valueOf(a.getIdArticle()));

                }
            });
                     
                Button btndel= new Button();
                btndel.setText("delete");
                btndel.setLayoutX(x + 400);
                btndel.setLayoutY(lb.getLayoutY() + 130);
                an.getChildren().add(btndel);
                btndel.setStyle(  "    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
                btndel.setOnAction(new EventHandler(){
                 public void handle(Event event){
                      
                
                     se.supprimer(a.getId_event());     
                 showeven();
                 }
            });
//                    an.setVisible(true);
//            vbe.getChildren().add(an);
//            File file = new File (even.get(i).getImg() );
//            Image img = new Image(  file.toURI().toString() ,215,173, false , true);
//            ImageView imageview = new ImageView(img);
//            imageview.setFitHeight(100);
//            imageview.setFitWidth(100);
//            imageview.setPreserveRatio(true);
//            imageview.setLayoutX(x+880);
//            imageview.setLayoutY(lb.getLayoutY()+70);
//            an.getChildren().add(imageview);
                     
                     
                
                 }
                
        
         
           
//            Button btnaffiche = new Button("Affichage");
            
  
           
            
            
        }
    }

