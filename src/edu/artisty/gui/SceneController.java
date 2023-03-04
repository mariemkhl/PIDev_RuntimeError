/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User
 */
public class SceneController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private Label songlabel;
    @FXML
    private ProgressBar songprogressbar;
    @FXML
    private Button playbutton;
    @FXML
    private Button pausebutton;
    @FXML
    private Button resetbutton;
    @FXML
    private Button previousbutton;
    @FXML
    private Button nextbutton;
    @FXML
    private ComboBox<String> speed;
    @FXML
    private Slider volumeslider;
    
    private File directory;
    private File[] files;
    
    ArrayList<File> songs;
    
    private int songNumber;
    private int[] speeds={25 ,50, 75,100, 125, 150,175,200};
    private Timer timer;
    private TimerTask task;
    private boolean running;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        songs =new ArrayList<File>();
        directory = new File("Music");
        files =directory.listFiles();
        
        if(files != null){
            for(File file: files){
                songs.add(file);
                System.out.println(file);
            }
        }
        
    }    

    @FXML
    private void playmedia(ActionEvent event) {
    }

    @FXML
    private void pausemedia(ActionEvent event) {
    }

    @FXML
    private void resetmedia(ActionEvent event) {
    }

    @FXML
    private void previousmedia(ActionEvent event) {
    }

    @FXML
    private void nextmedia(ActionEvent event) {
    }

    @FXML
    private void changespeed(ActionEvent event) {
    }
    
    public void beginTimer(){
        
    }
    public void cancelTimer(){
        
    }
    

    
}

