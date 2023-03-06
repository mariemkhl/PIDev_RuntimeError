/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.types.FacebookType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.artisty.entities.Article;
import static edu.artisty.gui.GetData.username;
import edu.artisty.utils.DataSource;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.Properties;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;


/**
 *
 * @author MariemKhlifi
 */
public class ArticleService implements IService<Article> {
 Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Article article) {
        String query = "INSERT INTO ARTICLE(titre_article,date_article,content_article,nbrLikes_article,image_article,category_article,idUser) VALUES(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setString(1, article.getTitreArticle());
            ste.setDate(2, article.getDateArticle());
            ste.setString(3, article.getContentArticle());
            ste.setInt(4, article.getNbrLikesArticle());
            ste.setString(5, article.getImageArticle());
            ste.setString(6, article.getCategoryArticle());
            ste.setInt(7, article.getIdUser());
            ste.executeUpdate();
            System.out.println("Article Added Successfully");
        } catch (SQLException e) {
            
            Alert alert;
             alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Please fill all blank fields");
                alert.showAndWait();
                System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Article article) {
        
        String path=article.getImageArticle().replaceAll("\\\\", "\\\\\\\\");
        System.out.println(path);
        String query = "UPDATE ARTICLE SET titre_article = '" + article.getTitreArticle() + "', date_article= '"
                + article.getDateArticle() + "', content_article= '" + article.getContentArticle() + "', nbrLikes_article= '"
                + article.getNbrLikesArticle() + "', image_article= '" + path + "', category_article= '"
                + article.getCategoryArticle() + "' WHERE id_article = '" + article.getIdArticle() + "';";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Article Updated Successfully ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int idArticle) {
        String query = "DELETE FROM ARTICLE WHERE id_article = " + idArticle + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Article Deleted Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Article> getAll() {
        List<Article> listeArticles = new ArrayList<>();
        String query = "SELECT * FROM article";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Article article = new Article();
                article.setIdArticle(rs.getInt("id_article"));
                article.setTitreArticle(rs.getString("titre_article"));
                article.setDateArticle(rs.getDate("date_article"));
                article.setContentArticle(rs.getString("content_article"));
                article.setNbLikesArticle(rs.getInt("nbrLikes_article"));
                article.setImageArticle(rs.getString("image_article"));
                article.setCategoryArticle(rs.getString("category_article"));
                article.setIdUser(rs.getInt("idUser"));
                listeArticles.add(article);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        
        return listeArticles;
    }

    @Override
    public Article getOneById(int idArticle) {
        String query = "SELECT * FROM ARTICLE WHERE id_article = " + idArticle + "";
        Article article = new Article();
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                article.setIdArticle(rs.getInt("id_article"));
                article.setTitreArticle(rs.getString("titre_article"));
                article.setDateArticle(rs.getDate("date_article"));
                article.setContentArticle(rs.getString("content_article"));
                article.setNbLikesArticle(rs.getInt("nbrLikes_article"));
                article.setImageArticle(rs.getString("image_article"));
                article.setCategoryArticle(rs.getString("category_article"));
                article.setIdUser(rs.getInt("idUser"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return article;
    }

    

    public boolean afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void filtrerMotsInappropries() {
        List<String> motsInappropries = Arrays.asList("drogue", "violence", "crime", "kill");

        // Récupérer tous les articles
        List<Article> articles = getAll();

        // Parcourir chaque article et filtrer les mots inappropriés
        articles.forEach(article -> {
            String content = article.getContentArticle();
            for (String mot : motsInappropries) {
                String remplacement = "";
                for (int i = 0; i < mot.length(); i++) {
                    remplacement += "*";
                }
                content = content.replaceAll(mot, remplacement);
            }
            article.setContentArticle(content);

            // Mettre à jour l'article dans la base de données
            String query = "UPDATE ARTICLE SET content_article = ? WHERE id_article = ?";
            try {
                PreparedStatement ps = cnx.prepareStatement(query);
                ps.setString(1, article.getContentArticle());
                ps.setInt(2, article.getIdArticle());
                ps.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        });

        System.out.println("Mots inappropriés filtrés avec succès");
    } 
    public List<Article> chercherArticle(String title, String category) {
    List<Article> articles = getAll();
    
    Stream<Article> filteredStream = articles.stream();
    
    if (title != null && !title.isEmpty()) {
        filteredStream = filteredStream.filter(article -> article.getTitreArticle().toLowerCase().contains(title.toLowerCase()));
    }
    
    if (category != null && !category.isEmpty()) {
        filteredStream = filteredStream.filter(article -> article.getCategoryArticle().toLowerCase().equals(category.toLowerCase()));
    }
    
    return filteredStream.collect(Collectors.toList());
    
}
    
     
      //////////////////////////////FACEBOOK///////////////////////////////////////////////
 public void shareOnPage(Article p) throws IOException {
        String accessToken = "EAAHZA0q5BuxkBAE5hQOQZCqHrkCJLZCYrjr1EtZCjNiuWnVUJggel6ktWKmZCfcyBsFno3F3dlZC7pEIw8ClHoFcwySvkN2RNfXir4P8FNyFRg5X0q2JS6Pux5hGNnW9ElmkEjA8VfMaHLf3wuOWg4UvZCCkBkOZBji7hivU9oOIiqUuKxti0wPZB";
        String pageId = "106824712245974";
        String message =p.getTitreArticle()+"\n"+ p.getContentArticle();
        FacebookClient fbClient = new DefaultFacebookClient(accessToken, Version.LATEST);

        try {
            FacebookType result = fbClient.publish(pageId + "/feed", FacebookType.class,
                    Parameter.with("message", message));
            System.out.println("Post published on page: " + result.getId());
        } catch (FacebookOAuthException ex) {
            System.err.println("Failed to post on page: " + ex.getMessage());
        }
    }
 ///////////////////////////////////////TRANSLATE/////////////////////////////////////////////////////////////
 
 public String ApiTranslate(String p) throws IOException{
                      OkHttpClient client = new OkHttpClient();
       
//        Response response = client.newCall(request).execute();
        
           RequestBody body = new FormBody.Builder()
            .add("q",p )
            .add("target", "fr")
            .build();

           Request request = new Request.Builder()
           .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
           .post(body)
           .addHeader("content-type", "application/x-www-form-urlencoded")
           .addHeader("Accept-Encoding", "application/gzip")
           .addHeader("X-RapidAPI-Key", "c18264e0acmshb87e57a497397a6p1f81d3jsnf873e4869b66")
           .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
           .build();

            Response response = client.newCall(request).execute();
            if (response.isSuccessful()){
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            String translatedText = json.getJSONObject("data")
                .getJSONArray("translations")
                .getJSONObject(0)
                .getString("translatedText");
            return translatedText; 
            } else {
            System.out.println("Request failed");
            }
            return p;
     
 }

 
    
}

    



