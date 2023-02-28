/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import edu.artisty.entities.Category;
import edu.artisty.entities.Product;
import edu.artisty.main.FXMain;
import edu.artisty.main.NewFXMainQR;
import edu.artisty.services.ArtsyAPI;
import edu.artisty.services.CategoryService;
import edu.artisty.services.ProductService;
import edu.artisty.utiles.DataSource;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.sql.Connection;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import okhttp3.AsyncDns.Callback;




/**
 * FXML Controller class
 *
 * @author Nour Benkairia
 */
public class ListProductController implements Initializable {

    @FXML
    private TableView<Product> ProductTable;
    @FXML
    private TableColumn<Product, String> nomProdColumn;
    @FXML
    private TableColumn<Product, String> DescColumn;
    @FXML
    private TableColumn<Product, Double> PrixColumn;
    @FXML
    private TableColumn<Product, Image> imgProdColumn;
    @FXML
    private TableColumn<Product, String> catProdColumn;
    @FXML
    private TableColumn<Product, Integer> userIdProdColumn;
    
    Connection cnx = DataSource.getInstance().getCnx();
     ProductService ProdServ = new ProductService();
    CategoryService CatServ = new CategoryService();
    @FXML
    private TableColumn<Product, String> URLColumn;
    @FXML
    private TextField searchURLtxt;
    @FXML
    private Button QRCodeButton;
    @FXML
    private TextField prodNameSearchtxt;
    
    
//   AjouterProductController pc = new AjouterProductController();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     

         List<Product> col = ProdServ.getAll();
        ObservableList<Product> listProduct = FXCollections.observableArrayList(col);
        nomProdColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        PrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imgProdColumn.setCellValueFactory(new PropertyValueFactory<>("img"));   
        catProdColumn.setCellValueFactory(new PropertyValueFactory<>("cat_p"));
        userIdProdColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        URLColumn.setCellValueFactory(new PropertyValueFactory<>("url"));
        //IdProdColumn.setCellValueFactory(new PropertyValueFactory<>("id_p"));
        ProductTable.setItems(listProduct);

         
 }    
    
    @FXML
     public void getSelected(MouseEvent event) {
         
          
         
         refresh();
        //  try {
             
              
//              Parent loader = FXMLLoader.load(getClass().getResource("../gui/AjouterProduct.fxml"));   
//              Scene scene = new Scene(loader, 600, 400);
//              Stage stage= new Stage();
//              stage.setScene(scene);
//              stage.show();
              
               
               int index = -1;
        index = ProductTable.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
//    
//        pc.getNomProd().setText(nomProdColumn.getCellData(index).toString());
//        pc.getDescProd().setText(DescColumn.getCellData(index).toString());
//        pc.getPrixProd().setText(PrixColumn.getCellData(index).toString());
//        pc.getCatProd().setText(catProdColumn.getCellData(index).toString());
//        pc.getUserProd().setText(userIdProdColumn.getCellData(index).toString());
//        //pc.getImgView().setSting(imgProdColumn.getCellData(index).toString());
//        //idp.setText(IdProdColumn.getCellData(index).toString());
          searchURLtxt.setText(URLColumn.getCellData(index).toString());
              
//             } catch (IOException ex) {
//                 Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
//             }
       
     
         
       // ImgView.setImage(imgProdColumn.getCellData(index).toString());
        
    }
     
     
     
     @FXML
     public void QRCodeGenerator(Event event) {
           QRCodeWriter qrCodeWriter = new QRCodeWriter();
           String url=searchURLtxt.getText();
       // String myWeb = "http://java-buddy.blogspot.com/";
        int width = 300;
        int height = 300;
        String fileType = "png";
        
        
         BufferedImage bufferedImage = null;
        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();
            
            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);
            
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            System.out.println("Success...");
            
        } catch (WriterException ex) {
            Logger.getLogger(NewFXMainQR.class.getName()).log(Level.SEVERE, null, ex);
        }
        
         ImageView qrView = new ImageView();
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        
        
         StackPane root = new StackPane();
        root.getChildren().add(qrView);
        
        Scene scene = new Scene(root, 350, 350);
        
        
         Stage stage= new Stage();
              stage.setScene(scene);
              stage.show();
        
      
     }
     
     
     public void ProductFind(Event event) {
            String s=prodNameSearchtxt.getText();
         Product p= new Product();
         if(ProdServ.RechercherProduitByNom(s)==true){
         
         Product product = ProdServ.getOneByName(s);
        // List<Product> col = ProdServ.getAll();
         ObservableList<Product> productList = FXCollections.observableArrayList(product);
          //List<Product> col = (List<Product>) ProdServ.getOneById(x);
         // ObservableList<Product> listProduct = FXCollections.observableArrayList(col);
          nomProdColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        PrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imgProdColumn.setCellValueFactory(new PropertyValueFactory<>("img"));
        catProdColumn.setCellValueFactory(new PropertyValueFactory<>("cat_p"));
        userIdProdColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
        //IdProdColumn.setCellValueFactory(new PropertyValueFactory<>("id_p"));
         ProductTable.setItems(productList);
         
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Produit deja existant !, Voulez vous fermer la fenetre?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Verification de l'existance du produit");
            alert.setHeaderText(null);
            alert.showAndWait();
         
         }
         else{
             // Alert alert = new Alert (Alert.AlertType.ERROR);
               Alert alert = new Alert(Alert.AlertType.INFORMATION, "Produit n'existe pas !, Voulez vous fermer la fenetre?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Verification de l'existance du produit");
            alert.setHeaderText(null);
            alert.showAndWait();
           
          
             
         }
        refresh();
     }
     
     
     private void refresh(){
         List<Product> stat = ProdServ.getAll();
        ObservableList<Product> productList = FXCollections.observableArrayList(stat);
        nomProdColumn.setCellValueFactory(new PropertyValueFactory<>("nom"));
        DescColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        PrixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        imgProdColumn.setCellValueFactory(new PropertyValueFactory<>("img"));
        catProdColumn.setCellValueFactory(new PropertyValueFactory<>("cat_p"));
        userIdProdColumn.setCellValueFactory(new PropertyValueFactory<>("user_id"));
         ProductTable.setItems(productList);
         }
     

    public TableView<Product> getProductTable() {
        return ProductTable;
    }

    public void setProductTable(TableView<Product> ProductTable) {
        this.ProductTable = ProductTable;
    }

    public TableColumn<Product, String> getNomProdColumn() {
        return nomProdColumn;
    }

    public void setNomProdColumn(TableColumn<Product, String> nomProdColumn) {
        this.nomProdColumn = nomProdColumn;
    }

    public TableColumn<Product, String> getDescColumn() {
        return DescColumn;
    }

    public void setDescColumn(TableColumn<Product, String> DescColumn) {
        this.DescColumn = DescColumn;
    }

    public TableColumn<Product, Double> getPrixColumn() {
        return PrixColumn;
    }

    public void setPrixColumn(TableColumn<Product, Double> PrixColumn) {
        this.PrixColumn = PrixColumn;
    }

    public TableColumn<Product, String> getCatProdColumn() {
        return catProdColumn;
    }

    public void setCatProdColumn(TableColumn<Product, String> catProdColumn) {
        this.catProdColumn = catProdColumn;
    }

    public TableColumn<Product, Integer> getUserIdProdColumn() {
        return userIdProdColumn;
    }

    public void setUserIdProdColumn(TableColumn<Product, Integer> userIdProdColumn) {
        this.userIdProdColumn = userIdProdColumn;
    }

    public TableColumn<Product, String> getURLColumn() {
        return URLColumn;
    }

    public void setURLColumn(TableColumn<Product, String> URLColumn) {
        this.URLColumn = URLColumn;
    }

   

    
    
    
}


