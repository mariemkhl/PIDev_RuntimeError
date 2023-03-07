/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import Pidev1.SignInController;
import entities.Utilisateur;
import services.ServiceUtilisateur;

import java.awt.Button;
import java.awt.TextField;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.DataConnection;


/**
 * FXML Controller class
 *
 * @author khoul
 */
public class ForgorCntrl implements Initializable {

    @FXML
    private TextField codetf;
    @FXML
    private Button valider;
 private Parent root;
   private Stage stage;
     private Scene scene;
        Connection cnx = DataConnection.getInstance().getCnx();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

//    @FXML
//    private void validermail(ActionEvent event)  {
//        SignInController si= new SignInController();
//        if(Integer.valueOf(codetf.getText())==si.code)
//        {
//            try {
//                FXMLLoader loader = new FXMLLoader(getClass().getResource("forgorCntrl.fxml"));
//                root = loader.load();
//                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
//                scene = new Scene(root);
//                stage.setScene(scene);
//                stage.show();
//                si=loader.getController();
//                si.passwordsignin.getText();
//                
//                String req = "SELECT * FROM utilisateur WHERE Email =?";
//                PreparedStatement st = cnx.prepareStatement(req);
//                st.setString(1,t);
//                
//                //while (si.passwordsignin.setText()) 
//            } catch (IOException ex) {
//                Logger.getLogger(ForgorCntrl.class.getName()).log(Level.SEVERE, null, ex);
//            }
//         
//                   
//          
//             }
    
  //  }
    
    
    
    
    
    @FXML
    private void validermail(ActionEvent event)  {
        SignInController si= new SignInController();
        
                Utilisateur su=new Utilisateur();

        if(Integer.valueOf(codetf.getText())==si.code)
        {
            try {
                
                
                
              String   req = "UPDATE `utilisateur` SET `Password`=?  WHERE `Email`='"+si.passwordsignin+"'";
      
          PreparedStatement  st = cnx.prepareStatement(req);
            st.setString(1, su.getPassword());
            st.executeUpdate();
            System.out.println("User updated!");
             FXMLLoader loader = new FXMLLoader(getClass().getResource("SingIn.fxml"));
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
//                si=loader.getController();
//                si.passwordsignin.getText();
                
//                String req = "SELECT * FROM utilisateur WHERE Email =?";
//                PreparedStatement st = cnx.prepareStatement(req);
//                st.setString(1,t);
                
                //while (si.passwordsignin.setText()) 
            } catch (IOException ex) {
                Logger.getLogger(ForgorCntrl.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(ForgorCntrl.class.getName()).log(Level.SEVERE, null, ex);
            }
//       
        }
    
        }
    }
   