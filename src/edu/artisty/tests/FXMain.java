/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.tests;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author abdelazizmezri
 */
public class FXMain extends Application {
    
    /**
     *
     * @param primaryStage
     * @throws IOException
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root= FXMLLoader.load(getClass().getResource("../gui/AjouterCommande.fxml"));
            Scene scene = new Scene(root,700,450);
            primaryStage.setTitle("Gestion des commandes");
            primaryStage.setScene(scene); 
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**<
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
