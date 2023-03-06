/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.main;

import API.ImageItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import static com.sun.org.apache.bcel.internal.classfile.Utility.getSignature;
import static com.sun.xml.internal.ws.api.ComponentFeature.Target.ENDPOINT;
import static com.sun.xml.internal.ws.api.ComponentFeature.Target.SERVICE;
import static com.sun.xml.internal.ws.policy.subject.WsdlBindingSubject.WsdlNameScope.OPERATION;
//import java.awt.Image;
import javafx.scene.image.Image;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.lang.model.util.Elements;
import javax.swing.text.Document;
import static okhttp3.OkHttp.VERSION;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;    
import javax.imageio.ImageIO;

//import org.jsoup.nodes.Document;      
//import org.jsoup.nodes.Element;       
//import org.jsoup.select.Elements; 

/**
 *
 * @author Nour Benkairia
 */
public class NewFXMainRating extends Application {

 private TextField searchField;
    private Label resultLabel;
    ImageView resultImageView = new ImageView();

    @Override
    public void start(Stage primaryStage) {
        
        
         searchField = new TextField();
        Button searchButton = new Button("Search");
        resultLabel = new Label();

        // Set the action for the search button
        searchButton.setOnAction(event -> {
            String productName = searchField.getText();
            try {
                OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/ImageSearchAPI?q="+productName+"/%20grande&pageNumber=1&pageSize=10&autoCorrect=true")
                    .get()
                    .addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
                    .addHeader("X-RapidAPI-Host", "contextualwebsearch-websearch-v1.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
                String responseBody = response.body().string();
                resultLabel.setText(responseBody);
                
                JsonParser jsonParser = new JsonParser();
        JsonObject responseJson = jsonParser.parse(responseBody).getAsJsonObject();
        JsonArray imageResults = responseJson.getAsJsonArray("value");
        String imageUrl = imageResults.get(0).getAsJsonObject().get("url").getAsString();

        // Load the image from the URL and set it in the ImageView
        Image image = new Image(imageUrl);
        resultImageView.setImage(image);
                
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        });

        // Create a layout and add the UI components
        VBox root = new VBox(10, searchField, searchButton, resultImageView, resultLabel);
        Scene scene = new Scene(root, 400, 400);

        // Set the title and show the stage
        primaryStage.setTitle("Amazon Product Search");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

   
    
}
