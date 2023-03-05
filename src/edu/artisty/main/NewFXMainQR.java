/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.main;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import okhttp3.AsyncDns.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Nour Benkairia
 */
public class NewFXMainQR extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
       









   Label ratingLabel = new Label("Loading...");

        StackPane root = new StackPane();
        root.getChildren().add(ratingLabel);

        Scene scene = new Scene(root, 200, 100);

        primaryStage.setTitle("Business Rating");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
         OkHttpClient client = new OkHttpClient();

//Request request = new Request.Builder()
//	.url("https://exposure-rating.p.rapidapi.com/image/rating/exposure_rating")
//	.post(null)
//	.addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
//	.addHeader("X-RapidAPI-Host", "exposure-rating.p.rapidapi.com")
//	.build();

Request request = new Request.Builder()
	.url("https://amazon-web-scraping-api.p.rapidapi.com/products/search?criteria=AMD%20Ryzen&page=1&countryCode=US&languageCode=EN")
	.get()
	.addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
	.addHeader("X-RapidAPI-Host", "amazon-web-scraping-api.p.rapidapi.com")
	.build();



Response response = client.newCall(request).execute();
        

//        String url = "https://api.yelp.com/v3/businesses/" + BUSINESS_ID;
//        OkHttpClient client = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(url)
//                .addHeader("Authorization", "Bearer " + API_KEY)
//                .build();

   //     Response response1 = client.newCall(request).execute();

        if (response.isSuccessful()) {
       try {
           String responseData = response.body().string();
           JSONObject businessJson = new JSONObject(responseData);
           double rating = businessJson.getDouble("**");
           ratingLabel.setText("Rating: " + rating);
       } catch (JSONException ex) {
           Logger.getLogger(NewFXMainQR.class.getName()).log(Level.SEVERE, null, ex);
       }
        } else {
            ratingLabel.setText("Error: " + response.code());
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}







    


            




        



        
//          QRCodeWriter qrCodeWriter = new QRCodeWriter();
//        String myWeb = "http://java-buddy.blogspot.com/";
//        int width = 300;
//        int height = 300;
//        String fileType = "png";
        
        
//         BufferedImage bufferedImage = null;
//        try {
//            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
//            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
//            bufferedImage.createGraphics();
//            
//            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
//            graphics.setColor(Color.WHITE);
//            graphics.fillRect(0, 0, width, height);
//            graphics.setColor(Color.BLACK);
//            
//            for (int i = 0; i < height; i++) {
//                for (int j = 0; j < width; j++) {
//                    if (byteMatrix.get(i, j)) {
//                        graphics.fillRect(i, j, 1, 1);
//                    }
//                }
//            }
            
//            System.out.println("Success...");
//            
//        } catch (WriterException ex) {
//            Logger.getLogger(NewFXMainQR.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        
//         ImageView qrView = new ImageView();
//        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));       

//         ImageView RATING = new ImageView();
//        RATING.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
//        
//
//
//         StackPane root = new StackPane();
//         root.getChildren().add(RATING);
//        
//        Scene scene = new Scene(root, 350, 350);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
//        
        
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        
//        Scene scene = new Scene(root, 300, 250);
//        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
 //   }

    /**
     * @param args the command line arguments
     */
//    public static void main(String[] args) {
//        launch(args);
//    }
//    
//}
