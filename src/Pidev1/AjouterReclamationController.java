/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import com.sothawo.mapjfx.Projection;
import entities.Reclamation;
import services.ReclamationCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
  import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javax.swing.JOptionPane;
import java.util.Properties;
import javafx.scene.control.TextField;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
  

/**
 * FXML Controller class
 *
 * @author user
 */
public class AjouterReclamationController implements Initializable {
    @FXML
    private ChoiceBox<String> ChoiceBox1;
    
      @FXML
    private TextField tfconf;

    @FXML
    private Button confirmation;

        
    @FXML
    private Button exit;
        @FXML
    private Label choisir1;
        
        @FXML
    private TextField section_commentaires;
    
    private final String [] type ={"Authenticité des oeuvres d'art","Bug","Elément inapproprié","Récéption d'un poduit érroné","problème de livraison", "problème de paiment"};
    @FXML
    private Button entertainement_btn;
    @FXML
    private Button event_btn;
    @FXML
    private Button produit_btn;
    @FXML
    private Button home_btn;
    @FXML
    private Button blog_btn;
    @FXML
    private Label username;
    @FXML
    private Button entertainement_btn1;
    @FXML
    private Button logoutbtn;
    @FXML
    private Button notif;

    @FXML
    void confirmation(ActionEvent event) {
        
        ReclamationCRUD rc = new ReclamationCRUD();
        Reclamation r = new Reclamation( section_commentaires.getText(),choisir1.getText());
                rc.AjoutReclamation2(r);
                
        Alert a = new Alert(Alert.AlertType.INFORMATION, "Votre réclamation a été ajoutée. Nous allons la prendre en considération !", ButtonType.OK);
                a.showAndWait();

    }

    @FXML
    void exit(ActionEvent event) throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                Parent root = loader.load();
                exit.getScene().setRoot(root);

    }
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        ChoiceBox1.getItems().addAll(type);
    	ChoiceBox1.setOnAction(this::choisir);
             
// TODO
    }
    	public void choisir(ActionEvent event) {
		
		String type = ChoiceBox1.getValue();
		choisir1.setText(type);
	}
        // TODO


@FXML
void sendEmailNotification(ActionEvent event) throws MessagingException {
    
    // Assuming you are sending email through relay.jangosmtp.net
    String host = "smtp.gmail.com";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.ssl.protocols","TLSv1.2");
    props.put("mail.smtp.port", "587");
    
    String from = "yessmine.gsouri@esprit.tn";
    final String username = "yessmine.gsouri@esprit.tn";
    System.out.println("preparing to send email");
           
    String to = tfconf.getText();
         
    String myAccountEmail="yessmine.gsouri@esprit.tn";
    String password="223AFT1624";
    
    Session session = Session.getInstance(props, new Authenticator() {
        @Override
        protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(myAccountEmail, password);
        }
    }); 
    
    try {
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject("Confirmation de récéption de réclamation"); 
        
        // Create a MimeMultipart to contain the content of the email
        MimeMultipart multipart = new MimeMultipart();

        // Create a new MimeBodyPart for the content with the desired font
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        String messageContent = "<p style=\"font-family: Arial, sans-serif;\">Votre Réclamation a été bien reçue. Nous allons la prendre en considération. <br> Merci pour votre patience <br> L'équipe de Artisty</p>";
        messageBodyPart.setContent(messageContent, "text/html");

        // Add the MimeBodyPart to the MimeMultipart
        multipart.addBodyPart(messageBodyPart);

        // Create a new MimeBodyPart for the image
        MimeBodyPart imagePart = new MimeBodyPart();

        // Set the image file location
        String imagePath = "C:\\Users\\user\\Desktop\\logo.png";

        // Add the image file to the MimeBodyPart
        imagePart.attachFile(imagePath);

        // Add the MimeBodyPart to the MimeMultipart
        multipart.addBodyPart(imagePart);

        // Set the content of the message to the MimeMultipart
        message.setContent(multipart);

        Transport.send(message);
        JOptionPane.showMessageDialog(null, "Message sent successfully!");
    } catch (MessagingException e) {
        JOptionPane.showMessageDialog(null, "Error sending message: " + e.getMessage());
    } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error attaching image: " + e.getMessage());
    }
}

     @FXML
    private void gestionmap(ActionEvent event) throws IOException {
        String fxmlFile = "FXMLDocument.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResourceAsStream(fxmlFile));
       // logger.trace("stage loaded");

        final Controller controller = fxmlLoader.getController();
        
        Projection projection= Projection.WEB_MERCATOR;
        controller.initMapAndControls(projection);
        choisir1.getScene().setRoot(rootNode);
        /*fxmlLoader.getController();
          buttonZoom.setOnAction(event -> mapView.setZoom(ZOOM_DEFAULT));
        sliderZoom.valueProperty().bindBidirectional(mapView.zoomProperty());
         mapView.setCenter(new Coordinate(36.8510414,10.159122));
        mapView.initialize(Configuration.builder()
            .projection(projection)
            .showZoomControls(false)
            .build());*/
        
    }

    @FXML
    private void goproduit(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("AjouterProduct.fxml"));
               choisir1.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
               choisir1.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
        choisir1.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 choisir1.getScene().setRoot(rootNode);
        
    }

    @FXML
    private void afficheprofile(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLhome.fxml"));	
                           Parent root = loader.load();	
                            FXMLhomeController scene2Controller = loader.getController();
                            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
                             choisir1.getScene().setRoot(root);
    }

 
    @FXML
    private void logout(ActionEvent event) {
    }



 
}
