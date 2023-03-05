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
import edu.artisty.entities.Collection;
import edu.artisty.entities.Product;
import edu.artisty.entities.ProductListCell;
import edu.artisty.main.FXMain;
import edu.artisty.main.NewFXMainQR;
import edu.artisty.services.CategoryService;
import edu.artisty.services.ProductService;
import edu.artisty.utiles.DataSource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Nour Benkairia
 */
public class AjouterProductController implements Initializable {

    @FXML
    private TextField NomProd;
    @FXML
    private TextField PrixProd;
    private TextField CatProd;
    @FXML
    private TextField UserProd;
    @FXML
    private TextArea DescProd;
    @FXML
    private ImageView ImgView;
    @FXML
    private Button BrowseBtn;
    @FXML
    private TextArea imgPath;
    private TextField idp;
    @FXML
    private ComboBox CategorieBox;
    @FXML
    private TextField urltxt;

    private TableView<Product> ProductTable;

    private File file;
    private FileChooser filechooser;
    private Image img;
    private File selectedFile;

    /**
     * Initializes the controller class.
     */
    Connection cnx = DataSource.getInstance().getCnx();
    ProductService ProdServ = new ProductService();
    CategoryService CatServ = new CategoryService();
    @FXML
    private Button AddProdBtn;
    @FXML
    private Button ModifBtn;
    @FXML
    private Button SuppBtn;
    @FXML
    private Button CollecWindow;
    @FXML
    private Button VersCatBtn;
    @FXML
    private Button AfficheProduct;

    ListProductController lpc = new ListProductController();
    @FXML
    private AnchorPane anchor2;
    @FXML
    private AnchorPane anchor1;
    @FXML
    private Button PgalleryBtn;
    @FXML
    private Button PaddBtn;
    @FXML
    private ListView<Product> Plist;
    @FXML
    private TextField searchURLtxt;
    @FXML
    private Button QRCodeButton;
    
    ProductListCell plcell= new ProductListCell();
    @FXML
    private Button deleteBtn1;
    @FXML
    private Button updateButton;
    @FXML
    private TextField Searchtxt;
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Category c = new Category();
        List<Category> cat = CatServ.getNames();
        ObservableList<Category> CategoryList = FXCollections.observableArrayList(cat);
        CategorieBox.setItems(CategoryList);

        filechooser = new FileChooser();
        
             // ListView<Product> Plist = new ListView<>();
              Plist.setCellFactory(lv -> new ProductListCell());
              


try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/artisty", "root", "")) {
    String query = "SELECT id_p, nom, prix, img, url FROM product";
    try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id_p");
            String nom = rs.getString("nom");
            double prix = rs.getDouble("prix");
            String imagePath =rs.getNString("img");
             File imageFile = new File(imagePath);
            //Image img = new Image(imagePath);
            String URL =rs.getString("url");
            Product product = new Product(id, nom, prix, imageFile, URL);
            Plist.getItems().add(product);
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}

 
        

    }
    
    
    
    
    
    
     public void ProductFind(Event event) {
         String s =Searchtxt.getText();
              Product p= new Product();
         
         if(ProdServ.getOneByName(s)!=null){
         System.out.println(ProdServ.getOneByName(s));
         Product product = ProdServ.getOneByName(s);
       
         ObservableList<Product> productList = FXCollections.observableArrayList(product);
 
         Plist.setItems(productList);
         
       Alert infoAlert = new Alert(AlertType.INFORMATION);
infoAlert.setTitle("Information");
infoAlert.setHeaderText("Produit existe déja !");
infoAlert.setContentText("Verification.");
infoAlert.showAndWait();
         
         }
         else{
              Alert infoAlert = new Alert(AlertType.INFORMATION);
infoAlert.setTitle("Information");
infoAlert.setHeaderText("Produit n'existe pas");
infoAlert.setContentText("Verification.");
infoAlert.showAndWait();
          
             refresh();
         }
        
     
             
  }
    
    
    


    private ObservableList<Product> retrieveProductData() {
        List<Product> lp = ProdServ.getAll();
    ObservableList<Product> data = FXCollections.observableArrayList(lp);
    return data;
    
    }
    
    
    
     public void getSelected(MouseEvent event) {
         
    int index = -1;
        index = Plist.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        ObservableList<Product> data = retrieveProductData();
        
        
             Plist.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Product>() {
                    @Override
                    public void changed(ObservableValue<? extends Product> observable,
                                        Product oldValue, Product newValue) {
                        if (newValue == null) {
                            searchURLtxt.setText("");
                                    
                                    
                             } else {
                        searchURLtxt.setText(newValue.getUrl());                      
                    }
                }
            });      
             
  }
     
    @FXML
      public void getSelectedOne(MouseEvent event) {
   int index = -1;
        index = Plist.getSelectionModel().getSelectedIndex();
        if (index <= -1){
            return;
        }
        ObservableList<Product> data = retrieveProductData();
        
        
             Plist.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Product>() {
                    @Override
                    public void changed(ObservableValue<? extends Product> observable,
                                        Product oldValue, Product newValue) {
                        if (newValue == null) {
                            NomProd.setText("");
                                    
                                    
                             } else {
                        NomProd.setText(newValue.getNom());                      
                    }
                }
            });     


         
     
//              anchor1.setVisible(true);
//              anchor2.setVisible(false);
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
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    public void BrowserBtn(Event e) {

        try {
            filechooser = new FileChooser();
            filechooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*txt"),
                    new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
                    new ExtensionFilter("Audio Files", "*wav", "*.mp3", "*.acc")
            );

            file = filechooser.showOpenDialog(new Stage());
            imgPath.setText(file.getAbsolutePath());

            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage javafxImage = SwingFXUtils.toFXImage(bufferedImage, null);
            ImgView.setImage(javafxImage);
            //  ImgView = new ImageView(javafxImage);        
            //   img= new Image(new FileInputStream(file)); 
//          ImgView= ImageView(img);
//          file.toURI().toString()
        } catch (IOException ex) {
            Logger.getLogger(AjouterProductController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void AjouterProduct(Event e) {

        Product p = new Product();
       
        if (NomProd.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter an Product Name .");
            alert.showAndWait();
            return;
        }
        if (PrixProd.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a Price .");
            alert.showAndWait();
            return;
        }

   
        

        p.setNom(NomProd.getText());
        p.setDescription(DescProd.getText());
        p.setPrix(Double.valueOf(PrixProd.getText()));
        p.setImg(file.getAbsoluteFile());
        Category c = new Category(CategorieBox.getValue().toString());
        p.setCat_p(c);
        p.setUser_id(201);
        p.setUrl(urltxt.getText());

       

            
        
        ProdServ.ajouter(p);
        

        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText(" Votre Produit est ajouté avec succés !, voulez vous passer a la gallerie ?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
             anchor1.setVisible(false);
             anchor2.setVisible(true);
             refresh();
        } else {
            anchor1.setVisible(true);
             anchor2.setVisible(false);
        }
        
       
    }

    public void reset() {
        NomProd.setText("");
        DescProd.setText("");
        PrixProd.setText("");
        NomProd.setText("");
        //ImgView.setText("");
        UserProd.setText("");
        idp.setText("");
    }


    @FXML
    private void supprimer(javafx.event.ActionEvent event) {

        if (Plist.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Product from the table AND click 3 times to delete the product");
            alert.showAndWait();
        } else {
 
       Plist.setOnMouseClicked(e -> {
    if (e.getClickCount() == 3) {
        Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir supprimer ce produit ?");
        confirmationAlert.setContentText("Cette action ne peut pas être annulée !");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
              Product selectedProduct = Plist.getSelectionModel().getSelectedItem();
        if (selectedProduct != null) {
            
            boolean success = ProdServ.deleteProduct(selectedProduct.getId_p());
;
            if (success) {
                
                Plist.getItems().remove(selectedProduct);
            }
        }
        } else {
            Alert infoAlert = new Alert(AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setHeaderText("Annulation de l'opération ");
            infoAlert.setContentText("Ceci est un message d'information");
            infoAlert.showAndWait();
        }
      
        
        
        
        
        
        
    }
}) ;
        }
       
       
    }
    
    @FXML
    private void modifier(javafx.event.ActionEvent event) {
        
         if (Plist.getSelectionModel().getSelectedItem() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Please select a Product from the table");
            alert.showAndWait();
        } else {

   int index = Plist.getSelectionModel().getSelectedIndex();
    if (index != -1) { 
        
       // Product selectedProduct = Plist.getItems().get(index);
        
        String selectedName = Plist.getItems().get(index).getNom();
        NomProd.setText(selectedName);
        
        String selectedDesc = Plist.getItems().get(index).getDescription();
        DescProd.setText(selectedDesc);
        
        Double selectedPrice = Plist.getItems().get(index).getPrix();
        PrixProd.setText(String.valueOf(selectedPrice));
        
              
//        File selectedImg = Plist.getItems().get(index).getImg();
//        Path path = selectedImg.toPath();
//        String img = path.toString();
        
        File selectedImg = Plist.getItems().get(index).getImg();
        Image img1 = new Image(selectedImg.toURI().toString());
         ImgView.setImage(img1);
        
//        String selectedCat = Plist.getItems().get(index).getCat_p().getNom();
//        NomProd.setText(selectedCat);
        
        int selectedUser = Plist.getItems().get(index).getUser_id();
        NomProd.setText(String.valueOf(selectedUser));
        
        String selectedUrl = Plist.getItems().get(index).getUrl();
        urltxt.setText(selectedUrl);
      
        anchor1.setVisible(true); anchor2.setVisible(false); 
        } else {
            // handle the error
        }
         }
    }
        

    
  
    @FXML
    private void modifier1(javafx.event.ActionEvent event) {
  
        ModifBtn.setOnMouseClicked(e -> {
    if (e.getClickCount() == 1) { 
           Alert confirmationAlert = new Alert(AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Êtes-vous sûr de vouloir modifier ce produit ?");
        confirmationAlert.setContentText("Cette action ne peut pas être annulée !");
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            
    
    int index = Plist.getSelectionModel().getSelectedIndex();
    if (index != -1) { 
        
        Product selectedProduct = Plist.getItems().get(index);
        
        selectedProduct.setNom(NomProd.getText());
        
       selectedProduct.setDescription(DescProd.getText());
        
//        Double selectedName = Plist.getItems().get(index).getPrix();
//        PrixProd.setText(String.valueOf(selectedName));
//        selectedProduct.setPrix(Double.parseDouble(PrixProd.getText()));
         Category c = new Category(CategorieBox.getValue().toString());
         selectedProduct.setCat_p(c);
         
         selectedProduct.setPrix(Double.parseDouble(PrixProd.getText()));
//        selectedProduct.setUser_id(Integer.valueOf(UserProd.getText()));
        selectedProduct.setImg(file.getAbsoluteFile()); 
 

        selectedProduct.setUrl(urltxt.getText());
          
        ProdServ.updateProduct(selectedProduct);
        
       
            // refresh the list view to reflect the changes
            Plist.refresh();}
        
    
        
        //}
        } else {
            Alert infoAlert = new Alert(AlertType.INFORMATION);
            infoAlert.setTitle("Information");
            infoAlert.setHeaderText("Annulation de l'opération ");
            infoAlert.setContentText("Ceci est un message d'information");
            infoAlert.showAndWait();
      }
    
    }
        });
      anchor1.setVisible(false); anchor2.setVisible(true); 
        
    }

                
    
         
    
    @FXML
    public void AfficheCollection(Event event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/AjouterCollection.fxml"));
            Scene scene = new Scene(loader, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void AfficheCategory(Event event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/AjouterCategory.fxml"));
            Scene scene = new Scene(loader, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    public void AfficheProducts(Event event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("../gui/ListProduct.fxml"));
            Scene scene = new Scene(loader, 800, 600);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

  

 private void refresh(){
         Plist.setCellFactory(lv -> new ProductListCell());
              


try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/artisty", "root", "")) {
    String query = "SELECT id_p, nom, prix, img, url FROM product";
    try (PreparedStatement stmt = conn.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            int id = rs.getInt("id_p");
            String nom = rs.getString("nom");
            double prix = rs.getDouble("prix");
            String imagePath =rs.getNString("img");
             File imageFile = new File(imagePath);
            //Image img = new Image(imagePath);
            String URL =rs.getString("url");
            Product product = new Product(id, nom, prix, imageFile, URL);
            Plist.getItems().add(product);
        }
    }
} catch (SQLException e) {
    e.printStackTrace();
}
         }

  

    @FXML
    public void listProd(Event event) {
        anchor1.setVisible(false);
        anchor2.setVisible(true);
      
        
    }

    @FXML
    public void addProd(Event event) {
        anchor1.setVisible(true);
        anchor2.setVisible(false);
    }

}

