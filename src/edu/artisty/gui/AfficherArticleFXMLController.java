package edu.artisty.gui;

import com.jfoenix.controls.JFXTextField;
import edu.artisty.entities.Article;
import edu.artisty.entities.Commentaire;
import edu.artisty.services.ArticleService;
import edu.artisty.services.CommentaireService;
import edu.artisty.utils.DataSource;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.lang.String;
import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import org.controlsfx.control.Notifications;

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
    private Button like;

    @FXML
    private Button dislike;

    @FXML
    private Button comments;

    @FXML
    private Label total_likes;

    @FXML
    private Button delete_comment;
    @FXML
    private AnchorPane showarticle_form;
    @FXML
    private Button clear_comment;

    private Image image;
    ScrollPane sp = new ScrollPane();

    @FXML
    private JFXTextField searchArt;

    @FXML
    private Label hiddenIdArticle;

//    public AfficherArticleFXMLController() {
//        this.listcategory = ("livre , artworks");
//    }

    /* public ObservableList<Article> articledata() {
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
    }*/
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
    @FXML
    private Label titre;
    @FXML
    private Label categorie;
    @FXML
    private Label content2;
    @FXML
    private Label nom;
    @FXML
    private Label date1;
    @FXML
    private TextArea ctf;
    @FXML
    private Label id;
    @FXML
    private ScrollPane commentaireScrollPane;
    @FXML
    private Label success;
    @FXML
    private Label warning;
    @FXML
    private Label idU;
    @FXML
    private Button reload;
    @FXML
    private ImageView imageview_article2;
    @FXML
    private Button like3;
    @FXML
    private Button dislike1;
    @FXML
    private VBox vboxcomment;
//    @FXML
//    private AnchorPane commentbox;
    @FXML
    private AnchorPane AnP;
//    @FXML
//    private TextField txs;
//    @FXML
//    private Button btns;
    @FXML
    private AnchorPane imageview3;

    @FXML
    private Button shareArticleFB;

    @FXML
    public void articleAdd() throws SQLException, MalformedURLException {
        String checkData = "SELECT titre_article,date_article,content_article,image_article,category_article FROM article WHERE titre_article='" + titre_article.getText().isEmpty() + "'";

        Alert alert;
// Connection cnx = DataSource.getInstance().getCnx();
        if (titre_article.getText().isEmpty() || date_article.getValue() == null || content_article1.getText().isEmpty()
                || GetData.path == null || category.getValue() == null) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all fields");
            alert.showAndWait();

        } else if (!titre_article.getText().matches("^[a-zA-Z0-9\\s]+$")) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please enter a valid title (alphanumeric characters and spaces only)");
            alert.showAndWait();

        } else if (content_article1.getText().length() > 5000) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Content should not exceed 5000 characters");
            alert.showAndWait();

        } else {
            try (Statement statement = cnx.createStatement();
                    ResultSet result = statement.executeQuery(checkData)) {

                if (result.next()) {
                    alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("titre article:" + titre_article.getText() + " already exist!");
                    alert.showAndWait();
                } else {

                    ArticleService as = new ArticleService();

                    Article ar = new Article(titre_article.getText(), java.sql.Date.valueOf(date_article.getValue()), content_article1.getText(), 0, GetData.path, category.getValue().toString(), 100);
                    as.ajouter(ar);
                    as.filtrerMotsInappropries();

                    home_form.setVisible(false);
                    blog_form.setVisible(false);
                    comment_form.setVisible(false);
                    showarticle_form.setVisible(false);
                    afficherlesarticle();
//            articleshowlist();
                    availablecategories();
//
//            availableFDShowData();
//            availableFDSearch();

                    System.out.println("You clicked me!");
                    Notifications.create()
                            .title("Notification")
                            .text("Congratulations, your article has been successfully added!")
                            .showWarning();

                }

            } catch (SQLException e) {
                // Handle exception
            }
        }

//        Alert alert;
////    Connection cnx = DataSource.getInstance().getCnx();
//        if (titre_article.getText().isEmpty()) {
//
//            alert = new Alert(AlertType.ERROR);
//            alert.setTitle("Error Message");
//            alert.setHeaderText(null);
//            alert.setContentText("Please fill all blank fields");
//            alert.showAndWait();
//
//        } else {
//            try (Statement statement = cnx.createStatement();
//                    ResultSet result = statement.executeQuery(checkData)) {
//
//                if (result.next()) {
//                    alert = new Alert(AlertType.ERROR);
//                    alert.setTitle("Error Message");
//                    alert.setHeaderText(null);
//                    alert.setContentText("titre article:" + titre_article.getText() + " already exist!");
//                    alert.showAndWait();
//                } else {
//                    ArticleService as = new ArticleService();
//
//                    Article ar = new Article(titre_article.getText(), java.sql.Date.valueOf(date_article.getValue()), content_article1.getText(), 0, GetData.path, category.getValue().toString(), 100);
//                    as.ajouter(ar);
//
//                    home_form.setVisible(false);
//                    blog_form.setVisible(false);
//                    comment_form.setVisible(false);
//                    showarticle_form.setVisible(false);
//                    afficherlesarticle();
////            articleshowlist();
//                    availablecategories();
////
////            availableFDShowData();
////            availableFDSearch();
//
//                }
//
//            } catch (SQLException e) {
//            }
//        }
//
    }
    String listcategory[] = {"livre", "artwork"};

    @FXML
    public void availablecategories() {
        List<String> lists = new ArrayList<>();
        for (String data : listcategory) {
            lists.add(data);
        }
        ObservableList listData = FXCollections.observableArrayList(lists);
        category.setItems(listData);

    }

    @FXML
    public void availableFDClear() {

        titre_article.setText("");
        content_article1.setText("");
        GetData.path = "";
        imageview_article.setImage(null);
        category.getSelectionModel().clearSelection();

    }

    @FXML
    void clearfield(ActionEvent event) {
        ctf.setText("");

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
    public void switchForm(ActionEvent event) throws MalformedURLException {

        if (event.getSource() == home_btn) {
            home_form.setVisible(true);
            blog_form.setVisible(false);
            comment_form.setVisible(false);
            showarticle_form.setVisible(false);
            sp.setVisible(false);

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
            sp.setVisible(false);
//            commentbox.setVisible(false);
            titre_article.setText("");
            availablecategories();

            content_article1.setText("");
            home_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            blog_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
            comments_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
            sp.setId("");
//
//            availableFDShowData();
//            availableFDSearch();
        } else if (event.getSource() == comments_btn) {
            home_form.setVisible(false);
            blog_form.setVisible(false);
            comment_form.setVisible(true);
            showarticle_form.setVisible(false);
            sp.setVisible(false);
//            commentbox.setVisible(false);
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
            showarticle_form.setVisible(false);
//            commentbox.setVisible(false);
            afficherlesarticle();
//            articleshowlist();
            availablecategories();

        } else if (event.getSource() == comments) {
            home_form.setVisible(false);
            blog_form.setVisible(false);
            comment_form.setVisible(true);
            showarticle_form.setVisible(false);
//            commentbox.setVisible(false);

        } else if (event.getSource() == reload) {
            home_form.setVisible(false);
            blog_form.setVisible(false);
            comment_form.setVisible(false);
            showarticle_form.setVisible(false);
//            commentbox.setVisible(true);

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        slider();
        // displayUsername();
//        articleshowlist();
        //availablecategories();
        home_form.setVisible(false);
        blog_form.setVisible(false);
        comment_form.setVisible(false);
        showarticle_form.setVisible(false);

        try {
            //  main_form.setVisible(false);
            afficherlesarticle();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void updatearticle(ActionEvent event) throws SQLException {

        Alert alert;
//    Connection cnx = DataSource.getInstance().getCnx();
        if (titre_article.getText().isEmpty()) {

            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();

        } else {
            ArticleService as = new ArticleService();
            System.out.println(GetData.path);
            Article ar = new Article(Integer.valueOf(sp.getId()), titre_article.getText(), java.sql.Date.valueOf(date_article.getValue()), content_article1.getText(), 0, GetData.path, category.getValue().toString(), 100);
            as.modifier(ar);
            // GetData.path="";
        }

    }

    private void afficherlesarticle() throws MalformedURLException {
        main_form.getChildren().removeAll(sp);
        sp.setVisible(true);
//        sp.setVisible(true);
//           btns.setVisible(true);
//    txs.setVisible(true);
        int x = 360;
        int y = 100;

        sp.setLayoutX(350);
        sp.setLayoutY(80);
        sp.setMaxHeight(750);
        sp.setMaxWidth(800);
        VBox vb = new VBox();
        sp.setStyle("border-radius: 25px;"
                + "border: 2px solid #73AD21;"
                + "padding: 20px;"
                + "width: 200px;"
                + "height: 150px;");

        sp.setContent(vb);

        ArticleService sr = new ArticleService();
        List<Article> la = new ArrayList();
        la = sr.getAll();
        System.out.println(la);
        for (int i = 0; i < la.size(); i++) {
            AnchorPane pn = new AnchorPane();
            pn.setStyle("-fx-background-color: linear-gradient(to bottom, #f2f2f2, #d4d4d4);"
                    + " -fx-border: 12px solid; -fx-border-color: white;"
            );

            vb.getChildren().add(pn);
            Label lb5 = new Label();
            lb5.setText(".");

            lb5.setLayoutX(x + 500);
            lb5.setStyle(" -fx-font: 40px Arial; ");
            pn.getChildren().add(lb5);
            Label lb = new Label();
            lb.setText(la.get(i).getTitreArticle());
            lb.setId("t" + String.valueOf(la.get(i).getIdArticle()));
            if (la.get(i).getTitreArticle().length() > 9) {
                lb.setLayoutX(x - 150);
                //lb.setAlignment(Pos.);
            } else {
                lb.setLayoutX(x - 50);
            }
            lb.setStyle(" -fx-font: 40px Arial; ");
            pn.getChildren().add(lb);
            pn.setVisible(true);
            File file = new File(la.get(i).getImageArticle());
            Image im = new Image(file.toURI().toURL().toString(), 200, 220, false, true);
            ImageView imageView = new ImageView(im);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            imageView.setLayoutX(x + 300);
            imageView.setLayoutY(lb.getLayoutY() + 40);
            pn.getChildren().add(imageView);
            Label ar = new Label();
            if (la.get(i).getContentArticle().length() < 80) {
                ar.setText(la.get(i).getContentArticle());
            } else if (la.get(i).getContentArticle().length() > 160) {
                String s = la.get(i).getContentArticle().substring(0, 80);

                String s1 = la.get(i).getContentArticle().substring(80, 160);
                String s2 = la.get(i).getContentArticle().substring(160);
                ar.setText(s + "\n" + s1 + "\n" + s2);

            } else {
                String s = la.get(i).getContentArticle().substring(0, 80);
                String s2 = la.get(i).getContentArticle().substring(80);
                ar.setText(s + "\n" + s2);
            }
            ar.setLayoutX(x - 350);
            ar.setLayoutY(lb.getLayoutY() + 40);
            ar.setId("c" + String.valueOf(la.get(i).getIdArticle()));
            pn.getChildren().add(ar);
            Label lbd = new Label();
            lbd.setText(String.valueOf(la.get(i).getDateArticle()));
            lbd.setId("d" + String.valueOf(la.get(i).getIdArticle()));
            lbd.setLayoutX(x - 30);
            lbd.setLayoutY(ar.getLayoutY() + 100);
            lbd.setStyle(" -fx-font: 10px Arial; ");
           
            Article a = new Article(Integer.valueOf(la.get(i).getIdArticle()), la.get(i).getTitreArticle(), la.get(i).getDateArticle(), la.get(i).getContentArticle(), Integer.valueOf(la.get(i).getNbrLikesArticle()), la.get(i).getImageArticle(), la.get(i).getCategoryArticle(), Integer.valueOf(la.get(i).getIdUser()));
             pn.getChildren().add(lbd);
            Button btndel = new Button();
            btndel.setText("delete");
            btndel.setLayoutX(x + 190);
            btndel.setLayoutY(lb.getLayoutY() +110);
            pn.getChildren().add(btndel);
            btndel.setStyle("    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
            btndel.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {

                    sr.supprimer(a.getIdArticle());
                    try {
                        afficherlesarticle();
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(AfficherArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            });

//             
            Button btnapi = new Button();
            btnapi.setText("Translate");
            btnapi.setLayoutX(x +50);
            btnapi.setLayoutY(ar.getLayoutY() + 70);
            pn.getChildren().add(btnapi);
            btnapi.setStyle("    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
            btnapi.setOnAction(new EventHandler() {
                @Override
                public void handle(Event event) {
                    String s = ar.getText() + "\n";
                    try {
                        s = sr.ApiTranslate(s);
                    } catch (IOException ex) {
                        Logger.getLogger(AfficherArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    ar.setText(s);
                }

            });

            Button btnc = new Button();
            btnc.setText("commenter");
            btnc.setStyle(
                    "    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
            GetData.path = a.getImageArticle();
            btnc.setLayoutX(x -100);
            btnc.setLayoutY(ar.getLayoutY() + 70);
            pn.getChildren().add(btnc);
            Button btnm = new Button();
            btnm.setStyle(
                    "    -fx-background-color: linear-gradient(to bottom ,#08747c ,#5aced6);"
                    + "    -fx-text-fill: #fff;"
                    + "    -fx-font-size: 14px;"
                    + "    -fx-font-family:Arial;"
                    + "    -fx-cursor:hand;");
            btnm.setText("modifier");
            btnm.setLayoutX(x -200);
            btnm.setLayoutY(ar.getLayoutY() + 70);
            pn.getChildren().add(btnm);
            btnc.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    comment_form.setVisible(true);
                    sp.setVisible(false);
                    content2.setText(a.getContentArticle());
                    titre.setText(a.getTitreArticle());
                    imageview_article2.setImage(image);
                    hiddenIdArticle.setText(String.valueOf(a.getIdArticle()));
                    CommentaireService cs = new CommentaireService();
                    System.out.println("Cms: " + cs.getCommentsByArticle(a.getIdArticle()));
                    List<Commentaire> aComments = cs.getCommentsByArticle(a.getIdArticle());
                    for (int i = 0; i < aComments.size(); ++i) {
                        vboxcomment.getChildren().add(new Label(aComments.get(i).getContentCommentaire()));
                    }
                    commentaireScrollPane.setContent(vboxcomment);
//                    pn.getChildren().add(lb);
                    ImageView imageview_article2 = new ImageView(im);

                    AnP.getChildren().add(imageview_article2);
                    //  CommentaireService cs = new CommentaireService();
                    Date d = Date.valueOf(LocalDate.now());
                    sp.setId(String.valueOf(a.getIdArticle()));

                }
            });

            btnm.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    home_form.setVisible(false);
                    blog_form.setVisible(true);
                    comment_form.setVisible(false);
                    showarticle_form.setVisible(false);

                    home_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
                    blog_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
                    comments_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
                    titre_article.setText(a.getTitreArticle());
                    content_article1.setText(a.getContentArticle());
                    File file = new File(GetData.path);
                    Image im;
                    try {
                        im = new Image(file.toURI().toURL().toString(), 200, 220, false, true);
                        imageview_article.setImage(im);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(AfficherArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    sp.setVisible(false);
                    sp.setId(String.valueOf(a.getIdArticle()));
                    availablecategories();

                }
            });
        }
        main_form.getChildren().add(sp);
        main_form.setStyle("-fx-background-color: linear-gradient(to bottom right ,#002427,#08747c);");
        // home_form.setVisible(true);
    }
///////////////////////////////////ADD COMMENT////////////////////////////////////////////////////////////////////////////

    @FXML
    private void cadd(ActionEvent event) {
        String checkData = "SELECT content_commentaire FROM commentaire WHERE content_commentaire='" + ctf.getText().isEmpty() + "'";
        Alert alert;
        Connection cnx = DataSource.getInstance().getCnx();
        if (ctf.getText().isEmpty()) {
            alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill the comment field");
            alert.showAndWait();
        } else {
            CommentaireService cms = new CommentaireService();
            Article ar = new Article();
            ar.setIdUser(Integer.valueOf(sp.getId()));
            Commentaire cm = new Commentaire(ar, ctf.getText());
            cms.ajouter(cm);
            cms.filtrerMotsInappropriesCommentaires();
            ctf.setText("");
            alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(null);
            alert.setContentText("Comment added successfully");
            alert.showAndWait();
        }
    }
    /////////////////////////////////////////////SHOW COMMENT/////////////////////////////////////////////////////////////////////////////////////

    private void showComments() {
        CommentaireService cs = new CommentaireService();
        System.out.println("Cms: " + cs.getCommentsByArticle(Integer.parseInt(hiddenIdArticle.getText())));
    }

    private void afficherlesarticle1() throws MalformedURLException {
        main_form.getChildren().removeAll(sp);
        System.out.println("jhvgb");
        sp.setVisible(true);
//    txs.setVisible(true);
//       btns.setVisible(true);
        int x = 360;
        int y = 100;

        sp.setLayoutX(150);
        sp.setLayoutY(80);
        sp.setMaxHeight(950);
        sp.setMaxWidth(800);
        VBox vb = new VBox();
        sp.setContent(vb);

        ArticleService sr = new ArticleService();
        List<Article> la = new ArrayList();
//        la = sr.chercherArticle(txs.getText(),txs.getText());
        System.out.println("LAAA: " + la);
        for (int i = 0; i < la.size(); i++) {
            AnchorPane pn = new AnchorPane();
            pn.setStyle("-fx-background-color: linear-gradient(to bottom,  #c8fff6 ,#002427);"
                    + " -fx-border: 12px solid; -fx-border-color: white;");

            vb.getChildren().add(pn);
            Label lb5 = new Label();
            lb5.setText(".");

            lb5.setLayoutX(x + 500);
            lb5.setStyle(" -fx-font: 40px Arial; ");
            pn.getChildren().add(lb5);
            Label lb = new Label();
            lb.setText(la.get(i).getTitreArticle());
            lb.setId("t" + String.valueOf(la.get(i).getIdArticle()));
            if (la.get(i).getTitreArticle().length() > 9) {
                lb.setLayoutX(x - 250);
                //lb.setAlignment(Pos.);
            } else {
                lb.setLayoutX(x - 50);
            }
            lb.setStyle(" -fx-font: 40px Arial; ");
            pn.getChildren().add(lb);
            pn.setVisible(true);
            File file = new File(la.get(i).getImageArticle());
            Image im = new Image(file.toURI().toURL().toString(), 200, 220, false, true);
            ImageView imageView = new ImageView(im);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            imageView.setLayoutX(x + 300);
            imageView.setLayoutY(lb.getLayoutY() + 40);
            pn.getChildren().add(imageView);
            Label ar = new Label();
            if (la.get(i).getContentArticle().length() < 80) {
                ar.setText(la.get(i).getContentArticle());
                Button btnapi = new Button();
                btnapi.setText("Translate");
                btnapi.setLayoutX(x + 60);
                btnapi.setLayoutY(ar.getLayoutY() + 60);
                pn.getChildren().add(btnapi);

            } else if (la.get(i).getContentArticle().length() > 160) {
                String s = la.get(i).getContentArticle().substring(0, 80);

                String s1 = la.get(i).getContentArticle().substring(80, 160);
                String s2 = la.get(i).getContentArticle().substring(160);
                ar.setText(s + "\n" + s1 + "\n" + s2);

            } else {
                String s = la.get(i).getContentArticle().substring(0, 80);
                String s2 = la.get(i).getContentArticle().substring(80);
                ar.setText(s + "\n" + s2);
            }
            ar.setLayoutX(x - 350);
            ar.setLayoutY(lb.getLayoutY() + 40);
            ar.setId("c" + String.valueOf(la.get(i).getIdArticle()));
            pn.getChildren().add(ar);
            Label lbd = new Label();
            lbd.setText(String.valueOf(la.get(i).getDateArticle()));
            lbd.setId("d" + String.valueOf(la.get(i).getIdArticle()));
            lbd.setLayoutX(x - 30);
            lbd.setLayoutY(ar.getLayoutY() + 100);
            lbd.setStyle(" -fx-font: 10px Arial; ");
            pn.getChildren().add(lbd);

            Button btnc = new Button();
            btnc.setText("commenter");
            Article a = new Article(Integer.valueOf(la.get(i).getIdArticle()), la.get(i).getTitreArticle(), la.get(i).getDateArticle(), la.get(i).getContentArticle(), Integer.valueOf(la.get(i).getNbrLikesArticle()), la.get(i).getImageArticle(), la.get(i).getCategoryArticle(), Integer.valueOf(la.get(i).getIdUser()));
            GetData.path = a.getImageArticle();
            btnc.setLayoutX(x + 30);
            btnc.setLayoutY(ar.getLayoutY() + 60);
            pn.getChildren().add(btnc);
            Button btnm = new Button();
            btnm.setText("modifier");
            btnm.setLayoutX(x - 50);
            btnm.setLayoutY(ar.getLayoutY() + 60);
            pn.getChildren().add(btnm);
            btnc.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    comment_form.setVisible(true);
                    sp.setVisible(false);
                    content2.setText(a.getContentArticle());
                    titre.setText(a.getTitreArticle());
                    imageview_article2.setImage(image);
                    hiddenIdArticle.setText(String.valueOf(a.getIdArticle()));

//                    pn.getChildren().add(lb);
                    ImageView imageview_article2 = new ImageView(im);

                    AnP.getChildren().add(imageview_article2);
                    CommentaireService cs = new CommentaireService();
                    Date d = Date.valueOf(LocalDate.now());
                    sp.setId(String.valueOf(a.getIdArticle()));

                }
            });

            btnm.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    home_form.setVisible(false);
                    blog_form.setVisible(true);
                    comment_form.setVisible(false);
                    showarticle_form.setVisible(false);

                    home_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
                    blog_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
                    comments_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
                    titre_article.setText(a.getTitreArticle());
                    content_article1.setText(a.getContentArticle());
                    File file = new File(GetData.path);
                    Image im;
                    try {
                        im = new Image(file.toURI().toURL().toString(), 200, 220, false, true);
                        imageview_article.setImage(im);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(AfficherArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    sp.setVisible(false);
                    sp.setId(String.valueOf(a.getIdArticle()));
                    availablecategories();

                }
            });

            main_form.getChildren().add(sp);
            main_form.setStyle("-fx-background-color: linear-gradient(to bottom right ,#002427,#08747c);");
            // home_form.setVisible(true);
        }
    }

    private void search(ActionEvent event) throws MalformedURLException {

        try {
            //  main_form.setVisible(false);
            afficherlesarticle1();
        } catch (MalformedURLException ex) {
            Logger.getLogger(AfficherArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
///////////////////////////////////////////////////chercherArticle//////////////////////////////////////////

    @FXML
    private void searchArticle() throws MalformedURLException {
        main_form.getChildren().removeAll(sp);

        sp.setVisible(true);
//    txs.setVisible(true);
//       btns.setVisible(true);
        int x = 360;
        int y = 100;

        sp.setLayoutX(350);
        sp.setLayoutY(80);
        sp.setMaxHeight(750);
        sp.setMaxWidth(800);
        VBox vb = new VBox();
        sp.setContent(vb);

        ArticleService sr = new ArticleService();
        List<Article> la = new ArrayList();
        la = sr.chercherArticle(searchArt.getText(), "");

        for (int i = 0; i < la.size(); i++) {
            AnchorPane pn = new AnchorPane();
            pn.setStyle("-fx-background-color: linear-gradient(to bottom, white,#08747c);"
                    + " -fx-border: 12px solid; -fx-border-color: white;");

            vb.getChildren().add(pn);
            Label lb5 = new Label();
            lb5.setText(".");

            lb5.setLayoutX(x + 500);
            lb5.setStyle(" -fx-font: 40px Arial; ");
            pn.getChildren().add(lb5);
            Label lb = new Label();
            lb.setText(la.get(i).getTitreArticle());
            lb.setId("t" + String.valueOf(la.get(i).getIdArticle()));
            if (la.get(i).getTitreArticle().length() > 9) {
                lb.setLayoutX(x - 150);
                //lb.setAlignment(Pos.);
            } else {
                lb.setLayoutX(x - 50);
            }
            lb.setStyle(" -fx-font: 40px Arial; ");
            pn.getChildren().add(lb);
            pn.setVisible(true);
            File file = new File(la.get(i).getImageArticle());
            Image im = new Image(file.toURI().toURL().toString(), 200, 220, false, true);
            ImageView imageView = new ImageView(im);
            imageView.setFitHeight(100);
            imageView.setFitWidth(100);
            imageView.setPreserveRatio(true);
            imageView.setLayoutX(x + 300);
            imageView.setLayoutY(lb.getLayoutY() + 40);
            pn.getChildren().add(imageView);
            Label ar = new Label();
            if (la.get(i).getContentArticle().length() < 80) {
                ar.setText(la.get(i).getContentArticle());
            } else if (la.get(i).getContentArticle().length() > 160) {
                String s = la.get(i).getContentArticle().substring(0, 80);

                String s1 = la.get(i).getContentArticle().substring(80, 160);
                String s2 = la.get(i).getContentArticle().substring(160);
                ar.setText(s + "\n" + s1 + "\n" + s2);

            } else {
                String s = la.get(i).getContentArticle().substring(0, 80);
                String s2 = la.get(i).getContentArticle().substring(80);
                ar.setText(s + "\n" + s2);
            }
            ar.setLayoutX(x - 350);
            ar.setLayoutY(lb.getLayoutY() + 40);
            ar.setId("c" + String.valueOf(la.get(i).getIdArticle()));
            pn.getChildren().add(ar);
            Label lbd = new Label();
            lbd.setText(String.valueOf(la.get(i).getDateArticle()));
            lbd.setId("d" + String.valueOf(la.get(i).getIdArticle()));
            lbd.setLayoutX(x - 30);
            lbd.setLayoutY(ar.getLayoutY() + 100);
            lbd.setStyle(" -fx-font: 10px Arial; ");
            pn.getChildren().add(lbd);

            Button btnc = new Button();
            btnc.setText("commenter");
            Article a = new Article(Integer.valueOf(la.get(i).getIdArticle()), la.get(i).getTitreArticle(), la.get(i).getDateArticle(), la.get(i).getContentArticle(), Integer.valueOf(la.get(i).getNbrLikesArticle()), la.get(i).getImageArticle(), la.get(i).getCategoryArticle(), Integer.valueOf(la.get(i).getIdUser()));
            GetData.path = a.getImageArticle();
            btnc.setLayoutX(x + 30);
            btnc.setLayoutY(ar.getLayoutY() + 60);
            pn.getChildren().add(btnc);
            Button btnm = new Button();
            btnm.setText("modifier");
            btnm.setLayoutX(x - 50);
            btnm.setLayoutY(ar.getLayoutY() + 60);
            pn.getChildren().add(btnm);
            btnc.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    comment_form.setVisible(true);
                    sp.setVisible(false);
                    content2.setText(a.getContentArticle());
                    titre.setText(a.getTitreArticle());
                    imageview_article2.setImage(image);
                    hiddenIdArticle.setText(String.valueOf(a.getIdArticle()));
//                    pn.getChildren().add(lb);
                    ImageView imageview_article2 = new ImageView(im);

                    AnP.getChildren().add(imageview_article2);
                    CommentaireService cs = new CommentaireService();
                    Date d = Date.valueOf(LocalDate.now());
                    sp.setId(String.valueOf(a.getIdArticle()));

                }
            });

            btnm.setOnAction(new EventHandler() {

                @Override
                public void handle(Event event) {
                    home_form.setVisible(false);
                    blog_form.setVisible(true);
                    comment_form.setVisible(false);
                    showarticle_form.setVisible(false);

                    home_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
                    blog_btn.setStyle("-fx-background-color: #3796a7; -fx-text-fill: #fff; -fx-border-width: 0px;");
                    comments_btn.setStyle("-fx-background-color: transparent; -fx-border-width: 1px; -fx-text-fill: #000;");
                    titre_article.setText(a.getTitreArticle());
                    content_article1.setText(a.getContentArticle());
                    File file = new File(GetData.path);
                    Image im;
                    try {
                        im = new Image(file.toURI().toURL().toString(), 200, 220, false, true);
                        imageview_article.setImage(im);
                    } catch (MalformedURLException ex) {
                        Logger.getLogger(AfficherArticleFXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    sp.setVisible(false);
                    sp.setId(String.valueOf(a.getIdArticle()));
                    availablecategories();

                }
            });
        }
        main_form.getChildren().add(sp);
        main_form.setStyle("-fx-background-color: linear-gradient(to bottom right , #c8fff6 ,#002427);");
    }

    @FXML
    public void shareArticleFB() throws IOException {
        ArticleService as = new ArticleService();
        Article a = new Article();
        a.setContentArticle(content_article1.getText());
        a.setTitreArticle(titre_article.getText());
        as.shareOnPage(a);
    }

}
