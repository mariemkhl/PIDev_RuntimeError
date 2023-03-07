/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;


import entities.Artist;
import entities.Customer;
import entities.Utilisateur;

import services.ServiceUtilisateur;

import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import utils.DataConnection;

/**
 * FXML Controller class
 *
 * @author khoul
 */
public class SignInController implements Initializable {
     public static int code=0;
     public static String mail;
        Connection cnx = DataConnection.getInstance().getCnx();

     private Stage stage;
     private Scene scene;
     private Parent root;

    @FXML
    private Button login;
        ServiceUtilisateur su=new ServiceUtilisateur();

    @FXML
    private TextField mailsignin;
    @FXML
     PasswordField passwordsignin;
    @FXML
    private Hyperlink hyper1;
    @FXML
    private Hyperlink hyper2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void loginto(ActionEvent event) throws IOException, SQLException {
           String role = "";
           if (su.login(mailsignin.getText(), passwordsignin.getText())){
               System.out.println(mailsignin.getText());
                      try {
                        String req = "SELECT * FROM utilisateur WHERE Email ='"+mailsignin.getText()+"' and password='"+passwordsignin.getText()+"'";
                        Statement ps = this.cnx.createStatement();
                        ResultSet rs = ps.executeQuery(req);
                        
                        if (rs.next())
                        {
                        switch (rs.getString(8)){
                        case("Artist"):
                        {
                       
                            //affecter les valeur dans un utlisateur
                            Artist u = new Artist();
                            u.setEmail(rs.getString("email"));
                            u.setNum_tel(rs.getString("Num_tel"));
                            u.setPassword(rs.getString("password"));
                            u.setUsername(rs.getString("username"));
                            u.setDomaine(rs.getString("domaine"));
           
                           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLhome.fxml"));
                           Parent root =fxmlLoader.load();
                           FXMLhomeController c2=  fxmlLoader.getController();                           
                           Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
                            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                            stage.setTitle("");
                            stage.setScene(scene);
                            stage.show();
                            UserSession.setId(rs.getInt(1));
                            UserSession.setName(rs.getString(2));

                        }
                        case("utilisateur"):
                        {
                           FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("admin_interface.fxml"));
                           Parent root=fxmlLoader.load();
                            login.getScene().setRoot(root);
                           UserSession.setId(rs.getInt(1)); 
                           UserSession.setName(rs.getString(2));
                        }
                        case("Customer"):
                        { 
                            Customer uti = new Customer();
                            uti.setEmail(rs.getString("email"));
                            uti.setNum_tel(rs.getString("Num_tel"));
                            uti.setPassword(rs.getString("password"));
                            uti.setUsername(rs.getString("Username"));
                            uti.setAdresse(rs.getString("Address"));
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLhome.fxml"));	
                            root = loader.load();	
                            FXMLhomeController scene2Controller = loader.getController();
                            scene2Controller.getName(rs.getString("Username"));
//                            scene2Controller.getEmail(uti.getEmail());
//                            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
                             login.getScene().setRoot(root);
                             UserSession.setId(rs.getInt(1));
                             UserSession.setName(rs.getString(2));
                        }
            }
                      }
                      }
                      catch (SQLException ex){
                          System.err.println(ex.getMessage());
                      }
    }
    }

    @FXML
    private void tocreation(ActionEvent event) throws IOException{
          root = FXMLLoader.load(getClass().getResource("CreateAccount.fxml"));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
           

    }

    @FXML
    private void forgor(ActionEvent event)throws IOException, Exception {
        
            root = FXMLLoader.load(getClass().getResource("forgorCntrl.fxml"));
          stage = (Stage)((Node)event.getSource()).getScene().getWindow();
          scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
          SecureRandom rand= new SecureRandom();
          int r=rand.nextInt(10000)+1;
          code=r;
          mail=mailsignin.getText();
          sendMail(mailsignin.getText(),r);
    
}
 public static void sendMail(String recepient,int code) throws Exception{
        System.out.println("preparing to send email");
        Properties properties =new Properties();
        
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        
        String myAccountEmail="khouloud.boussaha@esprit.tn";
        String password="amoula793";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password); //To change body of generated methods, choose Tools | Templates.
            }
            
}); 
        
   
            Message message =new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("campy");
            
            String msg=code+" est votre code de récupération de compte";
             
message.setContent("<body style='box-sizing: border-box;'><div style='position: relative;max-width: 800px;margin: auto 0;'><div><img style='vertical-align:middle;width:100%;' src='https://www.linkpicture.com/q/thumb-1920-555700.jpg'><div style='font-size: 20px; position:relative;text-align: center;background:black; height: 50  px;'><table style='width: 80%;margin-left: 15%;'><tr><td style='width: 70%;color: rgb(255,249,234); text-align: center;'>votre code de récupération de compte est :</td><td style='font-size: 30px;color: rgb(243,184,68);text-align:left;width: 30%;'>"+code+"</td></tr></table></div><img style='vertical-align:middle;width:100%;' src='https://www.linkpicture.com/q/thumb-1920-555700.jpg'></div></body>"
        
        ,"text/html");
           //message.setText(msg);
            
            
        Transport.send(message);
        System.out.println("message sent succesfully");
    

 }


}
