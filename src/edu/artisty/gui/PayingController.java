/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Token;
import edu.artisty.services.ServicePaiment;
import edu.artisty.entities.PAIMENT;
import edu.artisty.services.ServiceCommande;
import edu.artisty.utils.DataSource;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;


/**
 * FXML Controller class
 *
 * @author user
 */
public class PayingController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private Label prix_totcash;

    @FXML
    private Button conf_carte;

    @FXML
    private Button impression;

    @FXML
    private TextField num_carte;

    @FXML
    private TextField nom_carte;

    @FXML
    private TextField CV_code;

    @FXML
    private Label prix_totcarte;

    @FXML
    private Button conf_cash;
 
    @FXML
    private Label meth_paiment;
    
    @FXML
    private DatePicker date_ex ;
    
        // Récupérer la date sélectionnée
                LocalDate date = date_ex.getValue();

// Convertir la date en String avec un format spécifique
                String dateString = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));  
    
    @FXML
    void imprimer(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {

        Document document = new Document(); //cration de l'instance document

        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream("C:\\Users\\user\\Desktop\\Downloads\\Commande\\reçu.pdf"));  Connection cnx = DataSource.getInstance().getCnx();
                    
            String req = "SELECT * FROM paiment ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            // creation de outputstream instance et pdfwriter instance
            document.open();
            document.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.GREEN);
            Paragraph p = new Paragraph(" reçu de paiment  ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));
            PdfPTable pdfPTable = new PdfPTable(5);
            pdfPTable.setWidthPercentage(100);

            PdfPCell cell;
            cell = new PdfPCell(new Phrase("nom_carte", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            pdfPTable.addCell(cell);

            cell = new PdfPCell(new Phrase("num_carte", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            pdfPTable.addCell(cell);
            
            cell = new PdfPCell(new Phrase("CV_code", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            pdfPTable.addCell(cell);
            cell = new PdfPCell(new Phrase("prix_tot", FontFactory.getFont("Times New Roman", 11)));
            cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.WHITE);
            pdfPTable.addCell(cell);
                   
                        
          
            while (rs.next()) {
                PAIMENT pp = new PAIMENT();
                ServicePaiment sp = new ServicePaiment();
                
                cell = new PdfPCell(new Phrase(rs.getString("num_carte"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(cell);

                cell = new PdfPCell(new Phrase(rs.getString("nom_carte"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(cell);
                            
                cell = new PdfPCell(new Phrase(rs.getString("CV_code"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(cell);
                
                cell = new PdfPCell(new Phrase(rs.getString("prix_tot"), FontFactory.getFont("Times New Roman", 11)));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setBackgroundColor(BaseColor.WHITE);
                pdfPTable.addCell(cell);
                
                
            }
            document.add(pdfPTable);
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setHeaderText(null);
            alert.setContentText("votre reçu a été crée avec succès ! ");
            alert.showAndWait();
            document.close();

        } catch (DocumentException |IOException e){
            
               System.out.println("ERROR PDF");
                System.out.println(e.getMessage());    
 }

    }

    @FXML
  void valider_carte() throws ParseException {
String paymentMethod = meth_paiment.getText();
if ("cash".equals(paymentMethod)) {
    Alert a = new Alert(Alert.AlertType.ERROR, "Vous avez choisi de payer en espèces", ButtonType.FINISH);
    a.showAndWait();
}
 if ("credit_card".equals(paymentMethod)) {
    if (num_carte.getText().isEmpty() || CV_code.getText().isEmpty() || nom_carte.getText().isEmpty()) {
        Alert b = new Alert(Alert.AlertType.ERROR, "vous devez remplir tous les champs", ButtonType.OK);
        b.showAndWait();
    }
else if (!num_carte.getText().matches("\\d+") || !CV_code.getText().matches("\\d+")) {
        Alert c = new Alert(Alert.AlertType.ERROR, "le numéro de la carte et le CV code doivent être des entiers", ButtonType.FINISH);
        c.showAndWait();
    }
 else if (date_ex.getValue() == null) {
    Alert d = new Alert(Alert.AlertType.ERROR, "date invalide", ButtonType.FINISH);
    d.showAndWait();
}
 else  {
    try {
        String dateString = date_ex.getValue().toString(); // Get the date as a string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // Set the expected format of the date
        Date date = dateFormat.parse(dateString); // Parse the date string into a Date object
        Date currentDate = new Date(); // Get the current date

        if (date.before(currentDate)) { // Check if the entered date is before the current date
            Alert d = new Alert(Alert.AlertType.ERROR, "date invalide", ButtonType.FINISH);
            d.showAndWait();
        }
    } catch (ParseException e) {
        Alert d = new Alert(Alert.AlertType.ERROR, "date invalide", ButtonType.FINISH);
        d.showAndWait();
    }
}

}
else {
    // Handle other payment methods here
    ServicePaiment sp = new ServicePaiment();
    PAIMENT p = new PAIMENT(Integer.valueOf(num_carte.getText()), nom_carte.getText(), Integer.valueOf(CV_code.getText()));
    sp.ajouter(p);
    Alert a = new Alert(Alert.AlertType.INFORMATION, "Paiment effectué !", ButtonType.OK);
    a.showAndWait();

}

  }
 
    @FXML
    void valider_cash() throws IOException {
        
         if ( "Carte".equals(meth_paiment.getText()) ) 
             {      Alert a = new Alert(Alert.AlertType.ERROR, "Vous avez choisi de payer par carte", ButtonType.FINISH);
           a.showAndWait();

    } else {
             Alert a = new Alert(Alert.AlertType.INFORMATION, "Paiment effectué !", ButtonType.OK);
             a.showAndWait();
    }
    }
//        @FXML
//    void verif_payment() {
//
//        Stripe.apiKey = "sk_test_51MhusREq6aEa2VTuAfVPeqL43VcCUQlVqVM6d6tYc6TYBIK3Ip3YfCQFrcYH0QxsPKbpigevc5p1Lc2HPxnnehSq007YdMCTiP";
//        Map<String,Object> Param = new HashMap<String,Object>();
//        Param.put("number", num_carte.getText());
//        Param.put("exp_month","14/25");
//        Param.put("cvc", CV_code.getText());
//        Map<String,Object> TokenParam = new HashMap<String,Object>();
//        TokenParam.put("card",Param);
//        Token token=null;
//            ServicePaiment sp = new ServicePaiment();
//        try {
//            token = Token.create(TokenParam);
//        } catch (StripeException ex) {
//            Logger.getLogger(PayingController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Map<String,Object> chargeParam = new HashMap<String,Object>();
//        chargeParam.put("amount", Math.round((Integer.valueOf(prix_totcarte.getText())+10)*0.3*100));
//        chargeParam.put("currency", "EUR");
//        chargeParam.put("source", token.getId());
//        try {
//        Charge a =   Charge.create(chargeParam);
//        if(a.getPaid()){
//           Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                    alert.setTitle("Payement");
//                    alert.setContentText("Le Payement a été effectué avec succes");
//                    alert.showAndWait(); 
//                    sp.ToPayer(com.getId());
//                    form1.setVisible(false);
//        vbox1.setPrefHeight(71);
//        form2.setVisible(true);
//        vbox2.setPrefHeight(239);
//        }
//        } catch (StripeException ex) {
//            Logger.getLogger(PayingController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//             
//        
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO


}

     public void setMeth_Paiment(String meth_paiment) {
        this.meth_paiment.setText(meth_paiment);
    }

   public void setPrix_Totcarte(int prix_tot5) {
        this.prix_totcarte.setText(Integer.toString(prix_tot5));
    }

    public void setPrix_Totcash(int prix_tot5) {
        this.prix_totcash.setText(Integer.toString(prix_tot5));
    }


}