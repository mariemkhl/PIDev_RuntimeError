/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.gui;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Events;
import edu.esprit.services.ServiceEvents;
import edu.esprit.services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import edu.esprit.entities.Reservation;
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
    private TextField tfname;
    @FXML
    private Button tbnadd;
    @FXML
    private TextField tfeventid;
    @FXML
    private DatePicker datepc;
    @FXML
    private Button btnpdf;

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
        int eventid = Integer.valueOf(tfeventid.getText());
        System.out.println(eventid);
        Events evenid = se.getOneById(eventid);
        System.out.println(evenid);
        Date date = java.sql.Date.valueOf(datepc.getValue());

        if (tfname.getText().isEmpty() || tfuserid.getText().isEmpty() || tfeventid.getText().isEmpty()) {
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

            Reservation r = new Reservation(name, userid, evenid, date);
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
                    new FileOutputStream("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\reservation.pdf"));
            // creation de outputstream instance et pdfwriter instance
            BackgroundImageEvent bk = new BackgroundImageEvent();
            writer.setPageEvent(bk);

            Image signatureImage = Image.getInstance("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\signature.png");
            float signatureImageX = writer.getPageSize().getWidth() - 150;

            float signatureImageY = 100;
            signatureImage.setAbsolutePosition(signatureImageX, signatureImageY);

            document.open();
            BaseFont baseFont = BaseFont.createFont("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\src\\edu\\esprit\\gui\\fonttt.otf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

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

            Image img = Image.getInstance("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\logo.png");
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

            Desktop.getDesktop().open(new File("C:\\Users\\ASUS\\OneDrive\\Documents\\NetBeansProjects\\PIDev\\reservation.pdf"));

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

}
