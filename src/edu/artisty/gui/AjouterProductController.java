/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.gui;

import edu.artisty.entities.Category;
import edu.artisty.entities.Collection;
import edu.artisty.entities.Product;
import edu.artisty.main.FXMain;
import edu.artisty.services.CategoryService;
import edu.artisty.services.ProductService;
import edu.artisty.utiles.DataSource;
import java.awt.Graphics;
import java.awt.Image;
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
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
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
    private TableColumn<Product, String> nomProdColumn;
    private TableColumn<Product, String> DescColumn;
    private TableColumn<Product, Double> PrixColumn;
    private TableColumn<Product, Image> imgProdColumn;
    private TableColumn<Product, Integer> catProdColumn;
    private TableColumn<Product, Integer> userIdProdColumn;
    private TableColumn<Product, Integer> IdProdColumn;
     
     
     
    
    
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
    private Pane imgview1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        imgview1.getBackground();
     //  lpc.getSelected(event);
//int index = -1;
//        index = lpc.getProductTable().getSelectionModel().getSelectedIndex();
//        if (index <= -1){
//            return;
//        }
    
//        NomProd.setText(lpc.getNomProdColumn().getCellData(index).toString());
//        DescColumn.setText(lpc.getDescColumn().getCellData(index).toString());
//        PrixProd.setText(lpc.getPrixColumn().getCellData(index).toString());
//        CatProd.setText(lpc.getCatProdColumn().getCellData(index).toString());
//        UserProd.setText(lpc.getUserIdProdColumn().getCellData(index).toString());
//        //pc.getImgView().setSting(imgProdColumn.getCellData(index).toString());
//        //idp.setText(IdProdColumn.getCellData(index).toString());
//        urltxt.setText(lpc.getURLColumn().getCellData(index).toString());
        
        
        Category c = new Category();
        List<Category> cat = CatServ.getNames();
        ObservableList<Category> CategoryList = FXCollections.observableArrayList(cat);
        CategorieBox.setItems(CategoryList);
        
        filechooser = new FileChooser();
        
    }    
    
    @FXML
     public void BrowserBtn(Event e) {
        
        try {
            filechooser = new FileChooser();
            filechooser.getExtensionFilters().addAll(
                    new ExtensionFilter("Text Files", "*txt"),
                    new ExtensionFilter("Image Files","*.png","*.jpg","*.gif"),
                    new ExtensionFilter("Audio Files","*wav","*.mp3","*.acc")
            );
            
            file = filechooser.showOpenDialog(new Stage());
            imgPath.setText(file.getAbsolutePath());
            
            
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage javafxImage = SwingFXUtils.toFXImage(bufferedImage,null);
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
//         if (email.trim().isEmpty()) {
//    // Show an error message to the user
//    Alert alert = new Alert(AlertType.ERROR);
//    alert.setTitle("Error");
//    alert.setHeaderText(null);
//    alert.setContentText("Please enter an email address.");
//    alert.showAndWait();
//    return;
    
           Product p= new Product();
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
     
     if (CategorieBox.getValue().toString().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a Category .");
    alert.showAndWait();
    return;
           }
       if (UserProd.getText().isEmpty()) {
            Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(null);
    alert.setContentText("Please enter a Category .");
    alert.showAndWait();
    return;
           }
     
        p.setNom(NomProd.getText());
        p.setDescription(DescProd.getText());
        p.setPrix(Double.valueOf(PrixProd.getText()));
        p.setImg(file.getAbsoluteFile());
        Category c = new Category(CategorieBox.getValue().toString());
        p.setCat_p(c);
        p.setUser_id(Integer.valueOf(UserProd.getText()));
        p.setUrl(urltxt.getText());
        
        
//         String htdocsPath = "C:/xampp/htdocs/img/";
//                 File destinationFile = new File(htdocsPath + imgPath.getText());
//            if(selectedFile!=null){
//                try (InputStream in = new FileInputStream(selectedFile);
//                 OutputStream out = new FileOutputStream(destinationFile)) {
//                byte[] buf = new byte[8192];
//                int length;
//                while ((length = in.read(buf)) > 0) {
//                    out.write(buf, 0, length);
//                }
        
        
        ProdServ.ajouter(p);
//        reset();
//          
//      }        catch (FileNotFoundException ex) {
//                   Logger.getLogger(AjouterProductController.class.getName()).log(Level.SEVERE, null, ex);
//               } catch (IOException ex) {
//                   Logger.getLogger(AjouterProductController.class.getName()).log(Level.SEVERE, null, ex);
//               }
     refresh();
//}
      }

           

            
        public void reset(){
        NomProd.setText("");
        DescProd.setText("");
        PrixProd.setText("");
         NomProd.setText("");
        //ImgView.setText("");
        UserProd.setText("");
        idp.setText("");
    }
     
     
     
//    private void getSelected(MouseEvent event) {
//         int index = -1;
//        index = ProductTable.getSelectionModel().getSelectedIndex();
//        if (index <= -1){
//            return;
//        }
//        
//        NomProd.setText(nomProdColumn.getCellData(index).toString());
//        DescProd.setText(DescColumn.getCellData(index).toString());
//        PrixProd.setText(PrixColumn.getCellData(index).toString());
//        CatProd.setText(catProdColumn.getCellData(index).toString());
//        UserProd.setText(userIdProdColumn.getCellData(index).toString());
//        //idp.setText(IdProdColumn.getCellData(index).toString());
//     //   URL.setText(userIdProdColumn.getCellData(index).toString());
//         
//     
//         
//       // ImgView.setImage(imgProdColumn.getCellData(index).toString());
//        
//    }
    
    
    
    
     @FXML
    private void supprimer(javafx.event.ActionEvent event) {
         Product col= new Product();

               
        if (ProductTable.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a Product from the table");
           alert.showAndWait();
        }else{
        
        System.out.println( ProductTable.getSelectionModel().getSelectedItem()) ;
         Product p= new Product();

        int x=Integer.parseInt(idp.getText());
        p.setId_p(x);
        //System.out.println(x);

        ProdServ.supprimer(p);
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir supprimer le produit ? Cette action est irréversible.", ButtonType.YES, ButtonType.NO);
            confirmationDialog.setTitle("Confirmation de suppression");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.showAndWait();
    }
        refresh();
}

    @FXML
    private void modifier(javafx.event.ActionEvent event) {
        if (ProductTable.getSelectionModel().getSelectedItem()== null ){
            Alert alert = new Alert (Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
           alert.setContentText("Please select a Product from the table");
           alert.showAndWait();
        }else{
        Product p= new Product();
        
        
        int x=Integer.parseInt(idp.getText());
        p.setId_p(x);
        
        p.setNom(NomProd.getText());
        String name=p.getNom();
        
        p.setDescription(DescProd.getText());
        String desc=p.getDescription();
        
        p.setPrix(Double.parseDouble(PrixProd.getText())); 
        Double prix=p.getPrix();
        
        
//         p.setCat_p((Integer.parseInt(CatProd.getText())));
//        Category cat=p.getCat_p();
        
         p.setUser_id(Integer.parseInt(UserProd.getText()));
        int user=p.getUser_id();
        
        
         p.setUrl(urltxt.getText());
        String url=p.getUrl();
        
//        p.setImg(ImgView.getText());
//         String img=p.getImg();
        
        ProdServ.modifier(p);
        Alert confirmationDialog = new Alert(AlertType.CONFIRMATION, "Êtes-vous sûr de vouloir modifier le produit ? Cette action est irréversible.", ButtonType.YES, ButtonType.NO);
            confirmationDialog.setTitle("Confirmation de Modification");
            confirmationDialog.setHeaderText(null);
            confirmationDialog.showAndWait();
        //reset();
        refresh();
    }
    }
    
    
    
    
         @FXML
    public void AfficheCollection(Event event){
         try {
              Parent loader = FXMLLoader.load(getClass().getResource("../gui/AjouterCollection.fxml"));   
              Scene scene = new Scene(loader, 600, 400);
              Stage stage= new Stage();
              stage.setScene(scene);
              stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }
    
    @FXML
    public void AfficheCategory(Event event){
         try {
              Parent loader = FXMLLoader.load(getClass().getResource("../gui/AjouterCategory.fxml"));   
              Scene scene = new Scene(loader, 600, 400);
              Stage stage= new Stage();
              stage.setScene(scene);
              stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }
    
    @FXML
    public void AfficheProducts(Event event){
         try {
              Parent loader = FXMLLoader.load(getClass().getResource("../gui/ListProduct.fxml"));   
              Scene scene = new Scene(loader, 800, 600);
              Stage stage= new Stage();
              stage.setScene(scene);
              stage.show();
             } catch (IOException ex) {
                 Logger.getLogger(FXMain.class.getName()).log(Level.SEVERE, null, ex);
             }
        
    }
    

 
    
    
     public void RechercherProduit(){
         
         
         int x=Integer.parseInt(idp.getText());
         Product p= new Product();
         if(ProdServ.RechercherProduit(x)==true){
         
         Product product = ProdServ.getOneById(x);
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
        IdProdColumn.setCellValueFactory(new PropertyValueFactory<>("id_p"));
         ProductTable.setItems(productList);
         
        Alert alert = new Alert(AlertType.INFORMATION, "Produit deja existant !, Voulez vous fermer la fenetre?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Verification de l'existance du produit");
            alert.setHeaderText(null);
            alert.showAndWait();
         
         }
         else{
             // Alert alert = new Alert (Alert.AlertType.ERROR);
               Alert alert = new Alert(AlertType.INFORMATION, "Produit n'existe pas !, Voulez vous fermer la fenetre?", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Verification de l'existance du produit");
            alert.setHeaderText(null);
            alert.showAndWait();
           
          
             
         }
         reset();
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
     
     
     

    public TextField getNomProd() {
        return NomProd;
    }

    public void setNomProd(TextField NomProd) {
        this.NomProd = NomProd;
    }

    public TextField getPrixProd() {
        return PrixProd;
    }

    public void setPrixProd(TextField PrixProd) {
        this.PrixProd = PrixProd;
    }

    public TextField getCatProd() {
        return CatProd;
    }

    public void setCatProd(TextField CatProd) {
        this.CatProd = CatProd;
    }

    public TextField getUserProd() {
        return UserProd;
    }

    public void setUserProd(TextField UserProd) {
        this.UserProd = UserProd;
    }

    public TextArea getDescProd() {
        return DescProd;
    }

    public void setDescProd(TextArea DescProd) {
        this.DescProd = DescProd;
    }

    public ImageView getImgView() {
        return ImgView;
    }

    public void setImgView(ImageView ImgView) {
        this.ImgView = ImgView;
    }

    public TextField getUrltxt() {
        return urltxt;
    }

    public void setUrltxt(TextField urltxt) {
        this.urltxt = urltxt;
    }
     
     
     
     
     
    
}
