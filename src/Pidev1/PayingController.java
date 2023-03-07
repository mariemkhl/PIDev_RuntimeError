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
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import services.ServicePaiment;
import entities.PAIMENT;
import utils.DataConnection;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.sothawo.mapjfx.Projection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;


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
    private DatePicker date_ex1 ;   
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
   
    @FXML
    void imprimer(ActionEvent event) throws FileNotFoundException, DocumentException, SQLException, IOException {

        Document document = new Document(); //cration de l'instance document

        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\iheb debbech\\Desktop\\reçu de paiment.pdf"));  Connection cnx = DataConnection.getInstance().getCnx();
                    
            String req = "SELECT * FROM paiment ";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            // creation de outputstream instance et pdfwriter instance
            document.open();
            document.add(new Paragraph(" "));
            Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLUE);
            Paragraph p = new Paragraph(" reçu de paiment  ", font);
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.add(new Paragraph(" "));
                
            PdfPTable pdfPTable = new PdfPTable(4);
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
 else if (date_ex1.getValue() == null) {
    Alert d = new Alert(Alert.AlertType.ERROR, "date invalide", ButtonType.FINISH);
    d.showAndWait();
}
 else  {
    try {
        String dateString = date_ex1.getValue().toString(); // Get the date as a string
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
        num_carte.getScene().setRoot(rootNode);
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
              num_carte.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
               num_carte.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
       num_carte.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 num_carte.getScene().setRoot(rootNode);
        
    }

    @FXML
    private void afficheprofile(ActionEvent event) {
    }

   

}