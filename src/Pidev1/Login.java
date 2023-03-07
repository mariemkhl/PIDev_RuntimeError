/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author ASUS
 */
public class Login extends Application {
   
    private double x=0;
    private double y=0;
    @Override
    public void start(Stage stage) throws Exception {
        try {
      //  Parent root = FXMLLoader.load(getClass().getResource("Back.fxml"));  
       Parent root = FXMLLoader.load(getClass().getResource("SignIn.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
         
        } catch (IOException ex) {
           System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
