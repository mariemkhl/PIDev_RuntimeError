/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML.java to edit this template
 */
package Pidev1;
import services.servicemap;
import entities.emplacement;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import com.sothawo.mapjfx.*;
import javafx.scene.control.Slider;
import javafx.scene.control.Button;
import javafx.fxml.FXML;
import com.sothawo.mapjfx.Configuration;
import com.sothawo.mapjfx.Coordinate;
import com.sothawo.mapjfx.MapView;
import com.sothawo.mapjfx.Projection;
import entities.Likes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import javafx.scene.layout.HBox;
import services.servicelikes;
/**
 *
 * @author iheb debbech
 */
public class Pidev1 extends Application {
     
    
public static void main(String[] args) {
   
    //ls.ajouter(l1);
   // ls.ajouter2(l1);
   // ls.ajouter2(l2);
   //ls.modifier(l);
   //ls.supprimer(3);
  
        launch(args);
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        String fxmlFile = "SignIn.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource(fxmlFile));
       // logger.trace("stage loaded");

        /*final Controller controller = fxmlLoader.getController();
        final Projection projection = getParameters().getUnnamed().contains("wgs84")
                ? Projection.WGS_84 : Projection.WEB_MERCATOR;
        controller.initMapAndControls(projection);*/
        /*fxmlLoader.getController();
          buttonZoom.setOnAction(event -> mapView.setZoom(ZOOM_DEFAULT));
        sliderZoom.valueProperty().bindBidirectional(mapView.zoomProperty());
         mapView.setCenter(new Coordinate(36.8510414,10.159122));
        mapView.initialize(Configuration.builder()
            .projection(projection)
            .showZoomControls(false)
            .build());*/
StackPane stackPane = new StackPane();
       // Scene scene = new Scene(stackPane);
      //  stage.setScene(scene);
        Scene scene = new Scene(rootNode);
      stage.setTitle("diverticement artistique");
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
      
}

//import org.slf4j.LoggerFactory;
