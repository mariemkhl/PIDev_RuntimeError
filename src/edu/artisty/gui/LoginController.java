/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;


import edu.artisty.utils.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author User
 */
public class LoginController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button loginBtn;

    @FXML
    private Button close;

    
    private PreparedStatement prepare;
    private ResultSet result;
    

    @FXML
    public void login() {
        String sql = "SELECT * FROM utilisateur WHERE Username =? and Password=?";
//        Connection cnx = DataSource.getConnection();
Connection cnx = DataSource.getInstance().getCnx();
        
        try{
            prepare=cnx.prepareStatement(sql);
            prepare.setString(1,username.getText());
            prepare.setString(2, password.getText());
            result = prepare.executeQuery();
            Alert alert;
            
            if(username.getText().isEmpty()|| password.getText().isEmpty()){
                alert = new Alert (AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
            }else{
                if(result.next()){
                    // IF CORRECT USERNAME AND PASSWORD THEN PROCEED TO DASHBOARD
                    
                    
                 
                    
                    alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("INFORMATION MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully Login !");
                    alert.showAndWait();
                    
                    GetData.username= username.getText();
                    
                       //TO HIDE LOGIN FORM 
                    loginBtn.getScene().getWindow().hide();
                    
                Parent root = FXMLLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                
                stage.setScene(scene);
                stage.show();
                
                }else{
                    //IF NOT THEN ERROR MESSAGE WILL APPEAR
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("ERROR MESSAGE");
                    alert.setHeaderText(null);
                    alert.setContentText("Wrong Username/Password");
                    alert.showAndWait();
                }
            }
            
            
        }catch(Exception e){e.printStackTrace();}
    }

    @FXML
    public void close() {
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

}
