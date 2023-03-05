/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package Pidev1;
import javafx.stage.FileChooser;
import services.servicemap;
import entities.emplacement;
import com.sothawo.mapjfx.*;
import com.sothawo.mapjfx.event.MapLabelEvent;
import com.sothawo.mapjfx.event.MapViewEvent;
import com.sothawo.mapjfx.event.MarkerEvent;
import entities.Likes;
import java.awt.image.BufferedImage;
import java.sql.Connection;

import javafx.animation.AnimationTimer;
import javafx.animation.Transition;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import utils.DataConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.Event;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import services.chatbot;

/**
 * Controller for the FXML defined code.
 *
 * @author P.J. Meisch (pj.meisch@sothawo.com).
 */
public class Controller {
    
   //Connection cnx = DataConnection.getInstance().getCnx();
    /** logger for the class. */
   // private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    /** some coordinates from around town. */
   
    /** default zoom value. */
    private static final int ZOOM_DEFAULT = 14;
List<String> categories = new ArrayList();
    /** the markers. */
     private  Marker markerClick;
       List<Marker> m=new ArrayList<>();
       Marker markerClick1;
       List <emplacement> l= new ArrayList<>();
       MapCircle circleCastle;
  //@FXML
    //private Label labelEvent;

    /** the MapView containing the map */
    @FXML
    private MapView mapView;
    @FXML
    private BorderPane Borderpane;
    @FXML
    private CheckBox checkClickMarker;
    Double lat;
       Double longi;
       int f=1;
       @FXML 
       private Button btnlike;
       @FXML 
       private Button btndelete;
       @FXML
       private Label liena;
       @FXML
       private Label nomplacea;
       @FXML
       private Label descriptiona;
       @FXML
       private Label categoriea;
       @FXML
       private Label nblikesa;
       @FXML
       private Label nomplaceE;
       @FXML
       private Label lienE;
       @FXML
       private Label descriptionE;
       @FXML
       private Label categorieE;
       @FXML
       private Label nblikesE;
       @FXML
       private Label nomplace;
       @FXML
       private Label lien;
       @FXML
       private Label description;
       @FXML
       private Label categorie;
       @FXML
       private TextField nomplacet;
       @FXML
       private TextField lient;
       @FXML
       private TextField descriptiont;
       @FXML
       private TextField categoriet;
       @FXML
    private TextField searchField;

    @FXML
    private Button searchButton;

    @FXML
    private ImageView thumbnailView;
       emplacement em=new emplacement();
       servicemap mp=new servicemap();
       private static final String API_KEY = "AIzaSyBKTfNGZWOEUq81tcgvymiIS8AIJizMx44";
    
    private String videoId;
    private static final String SEARCH_URL = "https://www.googleapis.com/youtube/v3/search";
    private static final String VIDEO_URL = "https://www.googleapis.com/youtube/v3/videos";
   // private static final String SEARCH_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&type=video&q=%s&key=%s";
private static final String SEARCH_QUERY = "java tutorial";

    private WebView webView;
    private WebEngine webEngine;
    WebView webView1 = new WebView();
    @FXML
    private Accordion leftControls;
    @FXML
    private TitledPane optionsMarkers;
    @FXML
    private TitledPane emplacement;
    @FXML
    private TextArea txchat;
    @FXML
    private TextField tfchat;
    @FXML
    private Button btnchat;
    @FXML
    private Button btnimage;
    @FXML
    private Label path;
    @FXML
    private ImageView imgplace;
     public Stage stage;
    /** the box containing the top controls, must be enabled when mapView is initialized */
   public Controller(){ 
       markerClick = Marker.createProvided(Marker.Provided.RED).setVisible(false);
    categories.add("cinema");
            categories.add("museum");
            categories.add("monument");
            categories.add("theatre");
           
   }
    
   

    /**
     * called after the fxml is loaded and all objects are created. This is not called initialize any more,
     * because we need to pass in the projection before initializing.
     *
     * @param projection
     *     the projection to use in the map.
     */
    public void initMapAndControls(Projection projection) {
       chatbot cb =new chatbot();
        txchat.setText(cb.firsttext());
         path.setVisible(false);
//        logger.trace("begin initialize");

        // init MapView-Cache
//        final OfflineCache offlineCache = mapView.getOfflineCache();
       
//        logger.info("using dir for cache: " + cacheDir);
//        try {
//            Files.createDirectories(Paths.get(cacheDir));
//            offlineCache.setCacheDirectory(cacheDir);
//            offlineCache.setActive(true);
//        } catch (IOException e) {
//            logger.warn("could not activate offline cache", e);
//        }

        // set the custom css file for the MapView
       

        // set the controls to disabled, this will be changed when the MapView is intialized
       // setControlsDisable(true);

        // wire up the location buttons
        

       // buttonAllLocations.setOnAction(event -> mapView.setExtent(extentAllLocations));
//        logger.trace("location buttons done");

        // wire the zoom button and connect the slider to the map's zoom
       // buttonZoom.setOnAction(event -> mapView.setZoom(ZOOM_DEFAULT));
       // sliderZoom.valueProperty().bindBidirectional(mapView.zoomProperty());

        // add a listener to the animationDuration field and make sure we only accept int values
       

        // bind the map's center and zoom properties to the corresponding labels and format them
      

        // observe the map type radiobuttons
       
       //txchat.setText("fcv");
       txchat.setEditable(false);
        setupEventHandlers();

        // add the graphics to the checkboxes
      
       // logger.trace("marker checks done");

        // load two coordinate lines
       
        //logger.trace("tracks loaded");
  
       // logger.trace("tracks checks done");
        // get the extent of both tracks
        checkClickMarker.setGraphic(
            new ImageView(new Image(markerClick.getImageURL().toExternalForm(), 16.0, 16.0, true, true)));
   
        checkClickMarker.selectedProperty().bindBidirectional(markerClick.visibleProperty());
         /* for(int i=0;i<5;i++)
       {
           
           mapView.addMarker(m.get(i));
           System.out.println(m.get(i));
       }
          mapView.addMarker(markerClick1);*/
        // add the polygon check handler
       
     //   checkDrawPolygon.selectedProperty().addListener(polygonListener);

        // add the constrain listener
        

        // finally initialize the map view
       // logger.trace("start map initialization");
       mapView.setZoom(ZOOM_DEFAULT);
       mapView.setCenter(new Coordinate(36.8510414,10.159122));
        mapView.initialize(Configuration.builder()
            .projection(projection)
            .showZoomControls(false)
            .build());
                        
        //logger.debug("initialization finished");
        
           mapView.addEventHandler(MapViewEvent.MAP_POINTER_MOVED,event -> {
         
            event.consume();
            if(f!=0){
                
                for(int i=0;i<m.size();i++)
                    {
                        
                    mapView.removeMarker(m.get(i));
                   
                       
                            }
                
                      m.removeAll(m);
                     l.removeAll(l); 
              
         l=mp.getAll();
            for(int i=0;i<l.size();i++)
            {
            //  rs.getString(2), rs.getInt("RefU"), rs.getString(4));
                markerClick1 = Marker.createProvided(Marker.Provided.RED).setVisible(true);
            
           markerClick1.setPosition(new Coordinate(l.get(i).getLatitude(),l.get(i).getLongitude()));
           MapLabel ml= new MapLabel(l.get(i).getNomplace()).setVisible(true).setCssClass("green-label");
                          markerClick1.attachLabel(ml);
                          
           m.add(markerClick1);
           mapView.addMarker(markerClick1);
           
            }
            f=0;}
           
         });
     searchButton.setOnAction(event -> {
            try {
                webView1.isDisable();
                
                String query = searchField.getText().replaceAll(" ", "+");
                JSONObject searchResults = searchYouTube(query);
                videoId = searchResults.getJSONArray("items")
                        .getJSONObject(0)
                        .getJSONObject("id")
                        .getString("videoId");
                JSONObject videoDetails = getVideoDetails(videoId);
                String videoTitle = videoDetails.getJSONArray("items")
                        .getJSONObject(0)
                        .getJSONObject("snippet")
                        .getString("title");
                String thumbnailUrl = videoDetails.getJSONArray("items")
                        .getJSONObject(0)
                        .getJSONObject("snippet")
                        .getJSONObject("thumbnails")
                        .getJSONObject("medium")
                        .getString("url");
                BufferedImage thumbnailImage = ImageIO.read(new URL(thumbnailUrl));
                Image thumbnail = SwingFXUtils.toFXImage(thumbnailImage, null);
                thumbnailView.setImage(thumbnail);
                initializePlayer(videoId);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
     btnchat.setOnAction(event -> {
         
           try {
               String ms=cb.chattest(tfchat.getText(), txchat.getText());
               if(ms.startsWith("play"))
               {
                   txchat.setText(txchat.getText()+"\n -->music player:IT's My time to shine *^*");
                 initializePlayer(ms.substring(4));
                 tfchat.setText("");
                 
               }
               else if (ms.startsWith("stopsong")){
                   txchat.setText(txchat.getText()+"\n -->music playergoodnight :(");
                   webView1.getEngine().load(null);
                   tfchat.setText("");
               }
               else if(ms.startsWith("map"))
               {
                   String m[]=ms.substring(3).split(",");
                   mapView.setCenter(new Coordinate(Double.valueOf(m[0]),Double.valueOf(m[1])));
                   tfchat.setText("");
               }
               else{
                   
               txchat.setText(ms);
               tfchat.setText("");}
           } catch (IOException ex) {
               Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
           }
         
     });
       
    }
     private void setupEventHandlers() {
        mapView.addEventHandler(MarkerEvent.MARKER_DOUBLECLICKED, event -> {
            event.consume();
           // l.removeAll(l);
           
            // l=mp.getAll();
            
             for( int i=0;i<l.size();i++)
               {
              /* Double minx=event.getMarker().getPosition().getLatitude();
               Double maxx=newPosition.getLatitude()+0.001;
               Double miny=newPosition.getLongitude()-0.001;
               Double maxy=newPosition.getLongitude()+0.001;*/
               //Double d=Math.pow((x2-x1), 2)+Math.pow((y2-y1), 2);
                  
               if(l.get(i).getLatitude() == event.getMarker().getPosition().getLatitude() && l.get(i).getLongitude()== event.getMarker().getPosition().getLongitude())
               {
                   
                   nomplaceE.setVisible(true);
                   lienE.setVisible(true);
                   descriptionE.setVisible(true);
                   nblikesE.setVisible(true);
                    categorieE.setVisible(true);
                    nomplacea.setVisible(true);
                    liena.setVisible(true);
                    descriptiona.setVisible(true);
                     nblikesa.setVisible(true);
                    categoriea.setVisible(true);
                    btnlike.setVisible(true);
                   btndelete.setVisible(true);
                   nomplacea.setText(l.get(i).getNomplace());
                   liena.setText(l.get(i).getLien());
                   descriptiona.setText(l.get(i).getDescription());
                   categoriea.setText(l.get(i).getCategorie());
                   nblikesa.setText(Integer.toString(l.get(i).getNblikes()));
                   try {
                      
                   String s[]=l.get(i).getImage().split("/");;
                   String imageURL = "http://127.0.0.1:2224/"+s[s.length-1].replaceAll(" ","%20");
            Image image = new Image(new URL(imageURL).openStream());

            // Create an ImageView object and set the image as its content
                 imgplace.setImage(image);

           
        } catch (IOException e) {
            e.printStackTrace();
        }
               em=l.get(i);
               }      
                       }
        });
        // add an event handler for singleclicks, set the click marker to the new position when it's visible
        mapView.addEventHandler(MapViewEvent.MAP_CLICKED, event -> {
         
            event.consume();
             
             Coordinate newPosition = event.getCoordinate().normalize();
           //    Extent getloc=Extent.forCoordinates(newPosition);
              // circleCastle = new MapCircle(newPosition, 0_400).setVisible(true);
               //Extent ex=new Extent(newPosition.getLatitude())
               
       
       
            //labelEvent.setText("Event: map clicked at: " + newPosition);
            
            
                //final Coordinate oldPosition = markerClick.getPosition();
                if(markerClick.getVisible() && categories.contains(categoriet.getText()))
                {
                    markerClick.setPosition(new Coordinate(newPosition.getLatitude(),newPosition.getLongitude()));
                   
                    // adding can only be done after coordinate is set
                    mapView.addMarker(markerClick);
                    
                    
           // servicemap mp=new servicemap();
            emplacement e= new emplacement(nomplacet.getText(),descriptiont.getText(),lient.getText(),path.getText(),newPosition.getLatitude(),newPosition.getLongitude(),categoriet.getText());
                      mp.ajouter(e);
                      m.add(markerClick);                   
                      f=1;
                     
                }
                else if(!categories.contains(categoriet.getText()) && !categoriet.getText().isEmpty())
                        {
                            Alert a = new Alert(AlertType.NONE);
                            a.setAlertType(AlertType.ERROR);
 
                // set content text
                            a.setContentText("please put a valid category");
 
                // show the dialog
                            a.show();
                        }
            
            
            
        });
        btnlike.setOnAction( e -> {
        //servicemap mp=new servicemap();
        mp.modifier(em);
                nomplaceE.setVisible(false);
      lienE.setVisible(false);
      descriptionE.setVisible(false);
      nblikesE.setVisible(false);
      categorieE.setVisible(false);
       nomplacea.setVisible(false);
      liena.setVisible(false);
      descriptiona.setVisible(false);
      nblikesa.setVisible(false);
      categoriea.setVisible(false);
      btnlike.setVisible(false);
      btndelete.setVisible(false);
        });
        btndelete.setOnAction( e -> {
        //servicemap mp=new servicemap();
        mp.supprimer(em.getNomplace());
                nomplaceE.setVisible(false);
      lienE.setVisible(false);
      descriptionE.setVisible(false);
      nblikesE.setVisible(false);
      categorieE.setVisible(false);
       nomplacea.setVisible(false);
      liena.setVisible(false);
      descriptiona.setVisible(false);
      nblikesa.setVisible(false);
      categoriea.setVisible(false);
      btnlike.setVisible(false);
      btndelete.setVisible(false);
        });
        btnimage.setOnAction(e -> {
         FileChooser fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            String filePath = selectedFile.getAbsolutePath();
            System.out.println("Selected file: " + filePath); 
            path.setText(selectedFile.getAbsolutePath());
            
        }
        String s[]=path.getText().split("\\\\");
        File destinationFile = new File("C:/Users/iheb debbech/Desktop/webserver image/"+s[s.length-1]);
        File sourceFile = new File(path.getText());
        try {
            // Copy the file using the Files.copy() method
            Files.copy(sourceFile.toPath(), destinationFile.toPath());
            System.out.println("File copied successfully.");
            path.setText("C:/Users/iheb debbech/Desktop/webserver image/"+s[s.length-1]);
             } catch (IOException ex) {
            ex.printStackTrace();
             }
        
        });
}
      public void initializePlayer(String videoId) {
             //   String playerUrl = "https://www.youtube.com/embed/iicfmXFALM8?autoplay=1";
          
        String playerUrl = "https://youtu.be/"+videoId+"?autoplay=1";
        
        WebEngine webEngine1 = webView1.getEngine();
        webEngine1.load(playerUrl);
        Borderpane.getChildren().add(webView1);
        webView1.setVisible(false);
          
        //webView1.isDisable();
    }

    public JSONObject searchYouTube(String query) throws IOException {
        String searchUrl = SEARCH_URL + "?key=" + API_KEY + "&part=snippet&maxResults=1&q=" + query;
        String searchResponse = IOUtils.toString(new URL(searchUrl), "UTF-8");
        JSONObject searchResults = new JSONObject(searchResponse);
        return searchResults;
    }

    public JSONObject getVideoDetails(String videoId) throws IOException {
        String videoUrl = VIDEO_URL + "?key=" + API_KEY + "&part=snippet&id=" + videoId;
        String videoResponse = IOUtils.toString(new URL(videoUrl), "UTF-8");
        JSONObject videoDetails = new JSONObject(videoResponse);
        return videoDetails;
    } 

}
