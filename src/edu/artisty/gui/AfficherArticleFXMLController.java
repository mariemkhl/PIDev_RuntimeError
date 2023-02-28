package edu.artisty.gui;

import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.Int;
import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;
import edu.artisty.entities.Article;
import edu.artisty.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import static javax.management.Query.value;
import java.lang.String;

/**
 * FXML Controller class
 *
 * @author User
 */
public class AfficherArticleFXMLController implements Initializable {

    @FXML
    private AnchorPane main_form;

    @FXML
    private Label username;

    @FXML
    private Button home_btn;

    @FXML
    private Button blog_btn;

    @FXML
    private Button comments_btn;

    @FXML
    private Button logoutbtn;

    @FXML
    private AnchorPane home_form;

    @FXML
    private Label home_likes;

    @FXML
    private Label total_category;

    @FXML
    private Label total_bloggers;

    @FXML
    private ImageView imageview_article;

    @FXML
    private Button import_image;

    @FXML
    private TextField titre_article;

    @FXML
    private ComboBox<?> category;

    @FXML
    private Button update_article;

    @FXML
    private Button clear_fields_article;

    @FXML
    private DatePicker date_article;

    @FXML
    private TextField content_article;

    @FXML
    private Button delete_article;

    @FXML
    private Button add_article;

    @FXML
    private Button show_article;

    @FXML
    private TextField search_article;

    @FXML
    private TableColumn<Article, String> article_col1;

    @FXML
    private TableColumn<Article, String> article_col2;

    @FXML
    private TableColumn<Article, String> article_col3;

    @FXML
    private TableColumn<Article, String> article_col4;

    @FXML
    private TableColumn<Article, String> article_col5;

    @FXML
    private TableColumn<Article, String> article_col6;

    @FXML
    private Button like;

    @FXML
    private Button dislike;

    @FXML
    private Button comments;

    @FXML
    private Label total_likes;

    @FXML
    private ComboBox<?> article_id;

    @FXML
    private DatePicker date;

    @FXML
    private Spinner<?> status;

    @FXML
    private Button add_comment;

    @FXML
    private Button clear_fields;

    @FXML
    private Button update_comment;

    @FXML
    private Button delete_comment;
    @FXML
    private AnchorPane showarticle_form;

    private Image image;
    @FXML
    private TableView<Article> articletableview;

//    public AfficherArticleFXMLController() {
//        this.listcategory = ("livre , artworks");
//    }

    public ObservableList<Article> articledata() {
        ObservableList<Article> listData = FXCollections.observableArrayList();

        String sql = "SELECT id_article,titre_article,date_article,content_article,image_article,category_article FROM article";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(sql);

            Article article;
            while (rs.next()) {
                article = new Article(rs.getInt("idArticle"),
                        rs.getString("titreArticle"),
                        rs.getDate("dateArticle"),
                        rs.getString("contentArticle"), rs.getString("imageArticle"),
                        rs.getString("categoryArticle"));

                listData.add(article);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return listData;
    }

//    private ObservableList<Article> listData2;
//    public void articleshowlist() {
//        listData2 = articledata();
//        article_col1.setCellFactory(new PropertyValueFactory<>("idArticle"));
//        article_col2.setCellFactory(new PropertyValueFactory<>("titreArticle"));
//        article_col3.setCellFactory(new PropertyValueFactory<>("dateArticle"));
//        article_col4.setCellFactory(new PropertyValueFactory<>("contenteArticle"));
//        article_col5.setCellFactory(new PropertyValueFactory<>("imageArticle"));
//        article_col6.setCellFactory(new PropertyValueFactory<>("categoryArticle"));
//        
//      articletableview.setItems(listData2);
//    }
    Connection cnx = DataSource.getInstance().getCnx();
//    private PreparedStatement prepare;
////    private ResultSet result;

    public void articleAdd() throws SQLException {
        String sql = "INSERT INTO article (titre_article,null,content_article,nbr_likes_article,image_article,category_article) VALUES(?,?,?,?,?,)";

//    Connection cnx = DataSource.getInstance().getCnx();
        try {

            Alert alert;

            if (titre_article.getText().isEmpty() ) {

                alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();

            } else {

                String checkData = "SELECT titre_article,null,content_article,image_article,category_article FROM article WHERE titre_article='" + titre_article.getText().isEmpty() + "'";
                try (Statement statement = cnx.createStatement();
                        ResultSet result = statement.executeQuery(checkData)) {
                    if (result.next()) {
                        alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Message");
                        alert.setHeaderText(null);
                        alert.setContentText("titre article:" + titre_article.getText() + " already exist!");
                        alert.showAndWait();
                    } 
                    // Close the result set.
                    // Close the statement.
                }
            }
            
            

        } catch (SQLException e) {
        }

    }
    String listcategory[]= {"livre", "artwork"};

    public void availablecategories() {
        List<String> lists = new ArrayList<>();
        for (String data : listcategory) {
            lists.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(lists);
        category.setItems(listData);

    }

    public void availableFDClear() {

        titre_article.setText("");
        content_article.setText("");
        GetData.path = "";
        imageview_article.setImage(null);
        category.getSelectionModel().clearSelection();

    }

    @FXML
    public void articleImage() {
        FileChooser open = new FileChooser();
        open.setTitle("Open Image File");
        open.getExtensionFilters().add(new ExtensionFilter("Image Files", "*.jpg", "*.png"));

        File file = open.showOpenDialog(main_form.getScene().getWindow());

        if (file != null) {
            GetData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 200, 220, false, true);
            imageview_article.setImage(image);
        }
    }

    public void displayUsername() {
        String user = GetData.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    @FXML
    public void switchForm(ActionEvent event) {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            blog_form.setVisible(false);
            comment_form.setVisible(false);
            showarticle_form.setVisible(false);

            home_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            blog_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            comments_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
//
//            dashboardNC();
//            dashboardTI();
//            dashboardTIncome();
//            dashboardNOCCChart();
//            dashboardICC();
        } else if (event.getSource() == blog_btn) {
            home_form.setVisible(false);
            blog_form.setVisible(true);
            comment_form.setVisible(false);
            showarticle_form.setVisible(false);

            home_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            blog_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            comments_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");

//
//            availableFDShowData();
//            availableFDSearch();
        } else if (event.getSource() == comments_btn) {
            home_form.setVisible(false);
            blog_form.setVisible(false);
            comment_form.setVisible(true);
            showarticle_form.setVisible(false);

            home_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            blog_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            comments_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");

//
//            orderProductId();
//            orderProductName();
//            orderSpinner();
//            orderDisplayData();
//            orderDisplayTotal();
        } else if (event.getSource() == show_article) {
            home_form.setVisible(false);
            blog_form.setVisible(false);
            comment_form.setVisible(false);
            showarticle_form.setVisible(true);

//            articleshowlist();
            availablecategories();

        } else if (event.getSource() == comments) {
            home_form.setVisible(false);
            blog_form.setVisible(false);
            comment_form.setVisible(true);
            showarticle_form.setVisible(false);
        }

    }

    private double x = 0;
    private double y = 0;
    @FXML
    private AnchorPane blog_form;
    @FXML
    private TextField content_article1;
    @FXML
    private AnchorPane comment_form;

    @FXML
    public void logout() {
        try {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                logoutbtn.getScene().getWindow().hide();
                //LINK YOUR LOGIN FORM

                Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                root.setOnMousePressed(event -> {
                    x = event.getSceneX();
                    y = event.getSceneY();
                });

                root.setOnMouseDragged(event -> {
                    stage.setX(event.getScreenX() - x);
                    stage.setY(event.getScreenY() - y);

                    stage.setOpacity(.8);
                });

                root.setOnMouseReleased(event -> {

                    stage.setOpacity(1);
                });

                stage.initStyle(StageStyle.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }
        } catch (IOException e) {
        }
    }

    public void close() {
        System.exit(0);
    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

//    @FXML
//    private ImageView imageView;
//    int count;
//
//    public void slider() {
//
//        ArrayList<Image> images = new ArrayList<>();
//        images.add(new Image("image1.jpg"));
//        images.add(new Image("image2.jpg"));
//        images.add(new Image("image3.jpg"));
//
//        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), event -> {
//            imageView.setImage(images.get(count));
//            count++;
//            if (count == 3) {
//                count = 0;
//            }
//        }));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        slider();
        displayUsername();
//        articleshowlist();
        availablecategories();
    }

}
