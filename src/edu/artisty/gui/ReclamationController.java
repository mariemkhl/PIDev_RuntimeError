/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.artisty.entities.Reclamation;
import java.awt.Desktop;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import edu.artisty.services.ReclamationCRUD;
import edu.artisty.utils.DataSource;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;

/**
 * FXML Controller class
 *
 * @author 21695
 */
public class ReclamationController implements Initializable {

       @FXML
    private TableView<Reclamation> table;

@FXML
private TableColumn<Reclamation, Integer> tfId;

@FXML
private TableColumn<Reclamation, String> tfComm;

@FXML
private TableColumn<Reclamation, String> tfType;

    @FXML
    private Button tbAff;

    @FXML
    private Button reponse;

    @FXML
    private TextField tvRech;

    @FXML
    private Button tbRech;
    
      @FXML
    private Button Ajouter;

    @FXML
    private TextField FilteredList;
    
public ObservableList<Reclamation> data = FXCollections.observableArrayList();
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
             
    tfId.setCellValueFactory(new PropertyValueFactory<>("numero"));
    tfComm.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
    tfType.setCellValueFactory(new PropertyValueFactory<>("typeReclamation"));
    table.setItems(data);
     
      
    }    
    public Connection getConnection(){
        Connection conn;
        try{
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/artisty", "root","");
            return conn;
        }catch(Exception ex){
            System.out.println("Error: " + ex.getMessage());
            return null;
        }  
 }  
@FXML
private void show(ActionEvent event) {
    
    try {
        Connection cnx = DataSource.getInstance().getCnx();
    String s = "SELECT *FROM reclamation";
    PreparedStatement st = cnx.prepareStatement(s);
    ResultSet rs = st.executeQuery();
    while (rs.next())
        data.add(new Reclamation (rs.getInt(1),rs.getString(2),rs.getString(3)));
  
    }catch (Exception e){
        e.printStackTrace();
    }

}

  
       


    

      @FXML
    private void PDF(ActionEvent event) throws SQLException {

       try {
       com.itextpdf.text.Document doc = new com.itextpdf.text.Document();
       PdfWriter.getInstance(doc,new FileOutputStream("C:\\Users\\user\\Desktop\\Downloads\\Commande\\reclamation.pdf"));  
       doc.open();
       
      
       doc.add(new Paragraph(" "));
       Font font = new Font(Font.FontFamily.TIMES_ROMAN, 28, Font.UNDERLINE, BaseColor.BLACK);
       Paragraph p = new Paragraph("liste des Reclamations  ", font);
       p.setAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       doc.add(p);
       doc.add(new Paragraph(" "));
       doc.add(new Paragraph(" "));

       PdfPTable tabpdf = new PdfPTable(3);
       tabpdf.setWidthPercentage(100);
       
       PdfPCell cell;
       cell = new PdfPCell(new Phrase("numero", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
      
       cell = new PdfPCell(new Phrase("commentaire ", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);

       cell = new PdfPCell(new Phrase(" typeReclamation", FontFactory.getFont("Times New Roman", 11)));
       cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
       cell.setBackgroundColor(BaseColor.WHITE);
       tabpdf.addCell(cell);
      
       
       Connection conn = getConnection();
        String query = "SELECT * FROM reclamation";
       
          Statement st;
          ResultSet rs;
          st = conn.createStatement();
          rs = st.executeQuery(query);
     // PreparedStatement pst = cnx.prepareStatement(requete);
      // ResultSet rs = pst.executeQuery();
      while (rs.next()) {
           cell = new PdfPCell(new Phrase(rs.getString("numero"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);
           
              
          cell = new PdfPCell(new Phrase(rs.getString("commentaire"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);

             cell = new PdfPCell(new Phrase(rs.getString("typeReclamation"), FontFactory.getFont("Times New Roman", 11)));
           cell.setHorizontalAlignment(com.itextpdf.text.Element.ALIGN_CENTER);
           cell.setBackgroundColor(BaseColor.WHITE);
           tabpdf.addCell(cell);

       }
     
   
          doc.add(tabpdf);
          JOptionPane.showMessageDialog(null, "Success !!");
          doc.close();
          Desktop.getDesktop().open(new File("C:\\Users\\user\\Desktop\\Downloads\\Commande\\reclamation.pdf"));
       }
 
        catch (DocumentException | HeadlessException | IOException e) {
            System.out.println("ERROR PDF");
            System.out.println(Arrays.toString(e.getStackTrace()));
            System.out.println(e.getMessage());
          }
 }
    


   
    @FXML
    private void Ajouter(ActionEvent event)  throws IOException {
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReclamation.fxml"));
                Parent root = loader.load();
                Ajouter.getScene().setRoot(root);
    }
    

    @FXML
    private void Recherche(ActionEvent event) {
              ReclamationCRUD rs = new ReclamationCRUD();
       
       List<Reclamation> liste = rs.recherchePartype(tvRech.getText());
       
              
      tfComm.setCellValueFactory(new PropertyValueFactory<>("commentaire"));
      tfType.setCellValueFactory(new PropertyValueFactory<>("typeReclamation"));
      tfId.setCellValueFactory(new PropertyValueFactory<>("numero"));
      table.setItems((ObservableList<Reclamation>) liste);
    }


   

  
    
}