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
import javafx.scene.control.Alert;


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
     public List getEmailsOfParticipants(int idE){
        String query = "SELECT utilisateur.email_utilisateur from utilisateur " +
                "INNER JOIN reservation on (utilisateur.id_utilisateur = reservation.id_us AND " +
                "reservation.id_event =" + idE + ")";
        List<String> Emails = new ArrayList<>();
        try{
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()){
                Emails.add(rs.getString("email_utilisateur"));
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Emails;
    }
     
      //////////////////////////////FACEBOOK///////////////////////////////////////////////
 public void shareOnPage(Article p) throws IOException {
        String accessToken = "EAAHZA0q5BuxkBAEwezFPshZCOIX6K3KSaBasqe1wWv5S5STxrIN7ZCqmVWc6mJmLALKOKJBHN7kNgs8X7sSkz7DFnlraYJG0sWT38FgESNYou9Rdu21EU4kPihI35ZA9oWfeVTWG1OpPoFgZAS3pWYKvtDInxQ5FTqWm4l4ZCkq9tDCG29dTWZBODKFjYhKhvUZD";
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
     /////////////////////////////////////////////////////////////////////////

     
//     public void sendMail(String recepient) throws Exception {
//    System.out.println("Preparing to send email");
//    Properties properties = new Properties();
//
//    //Enable authentication
//    properties.put("mail.smtp.auth", "true");
//    //Set TLS encryption enabled
//    properties.put("mail.smtp.starttls.enable", "true");
//    //Set SMTP host
//    properties.put("mail.smtp.host", "smtp.gmail.com");
//    //Set smtp port
//    properties.put("mail.smtp.port", "587");
//
//    //Your gmail address
//    String myAccountEmail = "khlifi.mariem@esprit.tn"; // Replace with your email address
//    //Your gmail password
//    String password = "mariemkhlifi28238114"; // Replace with your gmail password
//
//    //Create a session with account credentials
//    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//            return new javax.mail.PasswordAuthentication(myAccountEmail, password);
//        }
//    });
//
//    //Prepare email message
//    Message message = prepareMessage(session, myAccountEmail, recepient);
//
//    //Send mail
//    Transport.send(message);
//    System.out.println("Message sent successfully");
//}
//
//private Message prepareMessage(Session session, String myAccountEmail, String recepient) {
//    try {
//        Message message = new MimeMessage(session);
//        message.setFrom(new InternetAddress(myAccountEmail));
//        message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//        message.setSubject("Event Canceled");
//        String htmlCode = "<h1> Ouuuuups </h1> <br/> <h2><b>The Event Has been Canceled </b></h2>";
//        message.setContent(htmlCode, "text/html");
//        return message;
//    } catch (Exception ex) {
//        System.out.println(ex.getMessage());
//    }
//    return null;
//}


//    public  void sendMail(String recepient) throws Exception {
//        System.out.println("Preparing to send email");
//        Properties properties = new Properties();
//
//        //Enable authentication
//        properties.put("mail.smtp.auth", "true");
//        //Set TLS encryption enabled
//        properties.put("mail.smtp.starttls.enable", "true");
//        //Set SMTP host
//        properties.put("mail.smtp.host", "smtp.gmail.com");
//        //Set smtp port
//        properties.put("mail.smtp.port", "587");
//
//        //Your gmail address
//        String myAccountEmail = "khlifi.mariem@esprit.tn";
//        //Your gmail password
//        String password = "mariemkhlifi28238114";
//
//        //Create a session with account credentials
//       Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
//    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
//        return new javax.mail.PasswordAuthentication(myAccountEmail, password);
//    }
//});
//
//        //Prepare email message
//        Message message = prepareMessage(session, myAccountEmail, recepient);
//
//        //Send mail
//        Transport.send(message);
//        System.out.println("Message sent successfully");
//    }
//
//    private  Message prepareMessage(Session session, String myAccountEmail, String recepient) {
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(myAccountEmail));
//            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
//            message.setSubject("Event Canceled");
//            String htmlCode = "<h1> Ouuuuups </h1> <br/> <h2><b>The Event Has been Canceled </b></h2>";
//            message.setContent(htmlCode, "text/html");
//            return message;
//        } catch (Exception ex) {
//            System.out.println(ex.getMessage());
//        }
//        return null;
//    }
      
    
}

    



