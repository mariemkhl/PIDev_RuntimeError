/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.main;


import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
//import java.awt.Insets;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import javax.net.ssl.HttpsURLConnection;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.*;

/**
 *
 * @author Nour Benkairia
 */
public class NewFXMainStars extends Application {
    

    
    @Override
    public void start(Stage primaryStage)  {
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(10);
        
        // Create a ToggleGroup to handle radio button selection
        ToggleGroup ratingGroup = new ToggleGroup();
        
        // Create 5 radio buttons for the 5 stars
        for (int i = 1; i <= 5; i++) {
            RadioButton star = new RadioButton();
            star.setId("star" + i);
            star.setUserData(i);
            star.setToggleGroup(ratingGroup);
            
            Label label = new Label("\u2605"); // Unicode for star symbol
            
            HBox hbox = new HBox();
            hbox.setAlignment(Pos.CENTER);
            hbox.getChildren().addAll(star, label);
            
            vbox.getChildren().add(hbox);
             }
        
        // Create a label to display the selected rating
        Label ratingLabel = new Label();
        ratingLabel.setAlignment(Pos.CENTER);
        
        // Add an event listener to the rating group to update the rating label
        ratingGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                int rating = (int) newValue.getUserData();
                ratingLabel.setText("Rating: " + rating);
            }
        });
        
        // Create a scene and add the VBox and rating label to it
        Scene scene = new Scene(new VBox(vbox, ratingLabel), 250, 200);
        
        primaryStage.setScene(scene);
        primaryStage.show();
        
    }
    
    
//     private String getStarRating(double rating) {
//        int numStars = (int) Math.round(rating);
//        StringBuilder stars = new StringBuilder();
//        for (int i = 1; i <= 5; i++) {
//            if (i <= numStars) {
//                stars.append("★");
//            } else {
//                stars.append("☆");
//            }
//        }
//        return stars.toString();
//    }

    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
