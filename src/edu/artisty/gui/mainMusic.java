package edu.artisty.gui;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class mainMusic extends Application {

//    private File directory;
//    private File[] files;
//    
//    private ArrayList<File> songs;
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/edu/artisty/gui/Scene.fxml"));
        Scene scene = new Scene(parent);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();

//        songs =new ArrayList<File>();
//        directory = new File("Music");
//        files =directory.listFiles();
//        
//        if(files != null){
//            for(File file: files){
//                songs.add(file);
//                System.out.println(file);
//            }
//        }
    }

   
    public static void main(String[] args) {
        SceneController controller = new SceneController();
        for (File song : controller.songs) {
            System.out.println(song);
        }
    }
}


