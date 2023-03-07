/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pidev1;


import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.sothawo.mapjfx.Projection;

import entities.Category;
import entities.Collection;
import entities.Product;
import entities.ProductListCell;

import services.CategoryService;
import services.ProductService;
import utils.DataConnection;
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
import java.util.ArrayList;
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
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.imageio.ImageIO;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    Connection cnx = DataConnection.getInstance().getCnx();
    ProductService ProdServ = new ProductService();
    CategoryService CatServ = new CategoryService();
    @FXML
    private Button AddProdBtn;
    @FXML
    private Button ModifBtn;
    @FXML
    private Button CollecWindow;
    @FXML
    private Button VersCatBtn;
    @FXML
    private Button AfficheProduct;

    
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
    @FXML
    private Button ReviewBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private Button btncommande;
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
    
  
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        Category c = new Category();
        List<Category> cat = CatServ.getNames();
        ObservableList<Category> CategoryList = FXCollections.observableArrayList(cat);
        CategorieBox.setItems(CategoryList);

        filechooser = new FileChooser();
        
             // ListView<Product> Plist = new ListView<>();
              Plist.setCellFactory(lv -> new ProductListCell());
              


try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8009/artisty", "root", "")) {
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
    
    
    
    @FXML
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
    
   
      
      
    private TextField searchField;
    private Label resultLabel;
    ImageView resultImageView = new ImageView();
    @FXML
      public void ReviewProductGenerator(Event event) {
          
          
   searchField = new TextField();
        Button searchButton = new Button("Search");
        resultLabel = new Label();
searchButton.setStyle("-fx-background-color: white;");
        // Set the action for the search button
        searchButton.setOnAction(eve -> {
            String productName = searchField.getText();
            try {
                OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder()
                    .url("https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/ImageSearchAPI?q="+productName+"/%20grande&pageNumber=1&pageSize=10&autoCorrect=true")
                    .get()
                    .addHeader("X-RapidAPI-Key", "")//"8be467020dmsh61fb6c2142fa03bp1d5682jsncacdbbe2c9dd")
                    .addHeader("X-RapidAPI-Host", "contextualwebsearch-websearch-v1.p.rapidapi.com")
                    .build();

            Response response = client.newCall(request).execute();
                String responseBody = response.body().string();
                resultLabel.setText(responseBody);
                
                JsonParser jsonParser = new JsonParser();
        JsonObject responseJson = jsonParser.parse(responseBody).getAsJsonObject();
        JsonArray imageResults = responseJson.getAsJsonArray("value");
        String imageUrl = imageResults.get(0).getAsJsonObject().get("url").getAsString();

        // Load the image from the URL and set it in the ImageView
        Image image = new Image(imageUrl);
        resultImageView.setImage(image);
                
            } catch (Exception e) {
                System.err.println("Error: " + e.getMessage());
            }
        });

        // Create a layout and add the UI components
        VBox root = new VBox(10, searchField, searchButton, resultImageView, resultLabel);
        Scene scene = new Scene(root, 400, 400);
//scene.getStylesheets().add(getClass().getResource("../css/dashboardDesign.css").toExternalForm());
        scene.getRoot().setStyle("-fx-background-color: linear-gradient(to bottom right ,#002427,#08747c);");
        // Set the title and show the stage
        Stage stage1= new Stage();
              stage1.setScene(scene);
              stage1.show();
              


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
            System.out.println("ERRRORRR WRITE EXCEPTION QR");
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
        p.setUser_id(UserSession.getId());
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
      
        anchor1.setVisible(true);
        anchor2.setVisible(false); 
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
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterCollection.fxml"));
            Scene scene = new Scene(loader, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
          System.out.println("ERRRORRR WRITE EXCEPTION QR");
        }

    }

    @FXML
    public void AfficheCategory(Event event) {
        try {
            Parent loader = FXMLLoader.load(getClass().getResource("AjouterCategory.fxml"));
            Scene scene = new Scene(loader, 600, 400);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
           System.out.println("ERRRORRR WRITE EXCEPTION QR");
        }

    }

    @FXML
    public void AfficheProducts(Event event) {
        //////fxml suprimer listproduit

    }

  

 private void refresh(){
         Plist.setCellFactory(lv -> new ProductListCell());
              


try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:8009/artisty", "root", "")) {
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

    @FXML
    private void commander(ActionEvent event) throws IOException {
        int index = Plist.getSelectionModel().getSelectedIndex();
   
        
        Product selectedProduct = Plist.getItems().get(index);
        
      Double selectedPrice = Plist.getItems().get(index).getPrix();
        
        
       selectedProduct.setDescription(DescProd.getText());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterCommande.fxml"));
        Parent root = loader.load();
               DescProd.getScene().setRoot(root);
               AjouterCommandeController ac=loader.getController();
               ac.tfuserid.setText(String.valueOf(UserSession.getId()));
               ac.prix_tot1.setText(String.valueOf(selectedPrice));
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
        anchor1.getScene().setRoot(rootNode);
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
              anchor1.getScene().setRoot(root);
        
        
    }

    @FXML
    private void gestionreclaction(ActionEvent event) throws IOException {
         Parent root = FXMLLoader.load(getClass().getResource("AjouterReclamation.fxml"));
              anchor1.getScene().setRoot(root);
        
    }

    @FXML
    private void blogs(ActionEvent event) throws IOException {
        String fxmlFile = "AfficherArticleFXML.fxml";
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AfficherArticleFXML.fxml"));
       // logger.trace("stage loaded");

       
        anchor1.getScene().setRoot(rootNode);
    }

    @FXML
    private void events(ActionEvent event) throws IOException {
        
      //  logger.debug("loading fxml file {}", fxmlFile);
        FXMLLoader fxmlLoader = new FXMLLoader();
        Parent rootNode = fxmlLoader.load(getClass().getResource("AjouterEvents.fxml"));
       // logger.trace("stage loaded");
 anchor1.getScene().setRoot(rootNode);
        
    }

    @FXML
    private void afficheprofile(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLhome.fxml"));	
                           Parent root = loader.load();	
                            FXMLhomeController scene2Controller = loader.getController();
                            //root = FXMLLoader.load(getClass().getResource("Scene2.fxml"));	
                             anchor1.getScene().setRoot(root);
    }

    @FXML
    private void logout(ActionEvent event) {
    }

  
}

