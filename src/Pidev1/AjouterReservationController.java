/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import entities.Events;
import services.ServiceEvents;
import services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import entities.Reservation;
import Pidev1.BackgroundImageEvent;
import com.sothawo.mapjfx.Projection;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.Date;
import java.sql.SQLException;

import javafx.beans.binding.StringBinding;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AjouterReservationController implements Initializable {

    @FXML
    private TextField tfuserid;
    @FXML
    public TextField tfname;
    @FXML
    private Button tbnadd;
   // private TextField tfeventid;
    @FXML
    public DatePicker datepc;
    @FXML
    private Button btnpdf;
    @FXML
    public TextField tfevent;
    @FXML
    private Button logoutbtn1;
    @FXML
    private Button entertainement_btn2;
    @FXML
    private Button event_btn1;
    @FXML
    private Button produit_btn1;
    @FXML
    private Button home_btn1;
    @FXML
    private Button blog_btn1;
    @FXML
    private Label username1;
    @FXML
    private Button entertainement_btn11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void actionadd(ActionEvent event) throws IOException {
        ServiceReservation sr = new ServiceReservation();
        ServiceEvents se = new ServiceEvents();
        String name = tfname.getText();
        int userid = Integer.valueOf(tfuserid.getText());
        String eventname = tfevent.getText();
       // System.out.println(eventname);
        Events evenname = se.getOneByName(eventname);
       // System.out.println(evenname);
        Date date = java.sql.Date.valueOf(datepc.getValue());

        if (tfname.getText().isEmpty() || tfuserid.getText().isEmpty() || tfevent.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setHeaderText(null);
            a.setHeaderText("Check you information");
            a.showAndWait();
        } else if (datepc.getValue().getYear() < 2023) {
            Alert al2 = new Alert(Alert.AlertType.ERROR);
            al2.setHeaderText(null);
            al2.setContentText("Current day please");
            al2.showAndWait();
        } else {
            Reservation r = new Reservation(name, userid, evenname, date);
            sr.ajouter(r);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("reservation created !");
            alert.showAndWait();
        }
    }

    private void addEmptyLine(Document document, int number) throws DocumentException {
        for (int i = 0; i < number; i++) {
            Paragraph p = new Paragraph(" ");
            document.add(p);
        }
    }

    @FXML
    private void PDFACTION(ActionEvent event) {

        Document document = new Document(); //cration de l'instance document
        // PdfWriter writer = null;
        try {
            PdfWriter writer = PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\Users\\iheb debbech\\reservation.pdf"));
            // creation de outputstream instance et pdfwriter instance
            BackgroundImageEvent bk = new BackgroundImageEvent();
            writer.setPageEvent(bk);

            Image signatureImage = Image.getInstance("C:\\Users\\iheb debbech\\Desktop\\signature.png");
            float signatureImageX = writer.getPageSize().getWidth() - 150;

            float signatureImageY = 100;
            signatureImage.setAbsolutePosition(signatureImageX, signatureImageY);

            document.open();
            BaseFont baseFont = BaseFont.createFont("C:\\Users\\iheb debbech\\Desktop\\fonttt.otf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

// Création d'une instance de Font avec la police de caractères et la taille souhaitées
            Font font = new Font(baseFont, 17);

// Ajout d'un paragraphe au document PDF avec la police de caractères créée
            Paragraph p = new Paragraph("PLATEFORME ARTISTY ", font);
            p.setAlignment(Element.ALIGN_RIGHT);
            document.add(p);

            Font font1 = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.NORMAL, BaseColor.GRAY);
            // Paragraph p =  new Paragraph(" PLATEFORME ARTISTY ", font1);  

            p.setAlignment(Element.ALIGN_RIGHT);
            //   document.add(p);
            document.add(signatureImage);

            Image img = Image.getInstance("C:\\Users\\iheb debbech\\Desktop\\logocha.png");
            //document.add("") ;  
            //   Image  img1 = Image.getInstance ("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\Capture.PNG");

            img.setAbsolutePosition(15, 690);
            img.scaleToFit(130, 150);
            addEmptyLine(document, 1); // utiliser la méthode addEmptyLine pour les paragraphes
            document.add(img);

            //BaseColor green = new BaseColor(0, 128, 0);
            Font font5 = new Font(Font.FontFamily.TIMES_ROMAN, 36, Font.NORMAL, BaseColor.DARK_GRAY);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            Paragraph p1 = new Paragraph(" Booking Ticket  ", font5);
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            ServiceReservation sr = new ServiceReservation();
            ServiceEvents se = new ServiceEvents();
            Reservation r = sr.AffichagePDF();

            Font font2 = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLDITALIC, BaseColor.BLACK);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("cher(e) client(e) :  ", font2));
            document.add(new Paragraph(" "));
            document.add(new Paragraph("vous avez reservé dans cet avec les information suivante :  ", font2));
            document.add(new Paragraph(" " + r.getEvent() + "", font2));
            document.add(new Paragraph(" Avec la date  : " + r.getDateRE() + "", font2));

            //document.add(pdfPTable);
            // Ajouter un nouveau paragraphe au document
            document.add(new Paragraph(" "));

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("L'opération a été effectuée avec succès !");
            alert.showAndWait();
            document.close();

            Desktop.getDesktop().open(new File("C:\\Users\\iheb debbech\\reservation.pdf"));

        } catch (DocumentException | IOException e) {

            System.out.println("ERROR PDF");

            System.out.println(e.getMessage());

        }

    }

    @FXML
    private void actiondetails(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AfficherReservation.fxml"));
                Parent root = loader.load();
                tfname.getScene().setRoot(root)  ;
    }

    @FXML
    private void logout(ActionEvent event) {
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
        tfname.getScene().setRoot(rootNode);
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
               tfname.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
               tfname.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
       tfname.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 tfname.getScene().setRoot(rootNode);
        
    }

    @FXML
    private void afficheprofile(ActionEvent event) {
    }

    

}
