package edu.artisty.gui;

import static com.sun.xml.internal.ws.api.model.wsdl.WSDLBoundOperation.ANONYMOUS.optional;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

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
    private TableColumn<?, ?> article_col1;

    @FXML
    private TableColumn<?, ?> article_col2;

    @FXML
    private TableColumn<?, ?> article_col3;

    @FXML
    private TableColumn<?, ?> article_col4;

    @FXML
    private TableColumn<?, ?> article_col5;

    @FXML
    private TableColumn<?, ?> article_col6;

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
        } catch (Exception e) {
            e.printStackTrace();
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
    }

}
