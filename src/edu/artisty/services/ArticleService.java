/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.artisty.entities.Article;
import edu.artisty.utils.DataSource;

/**
 *
 * @author MariemKhlifi
 */
public class ArticleService implements IService<Article> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Article article) {
        String query = "INSERT INTO ARTICLE(titre_article,date_article,content_article,nbr_likes_article,image_article,category_article,id_user) VALUES(?,?,?,?,?,?,?)";
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
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Article article) {
        String query = "UPDATE ARTICLE SET titre_article = '" + article.getTitreArticle() + "', date_article= '"
                + article.getDateArticle() + "', content_article= '" + article.getContentArticle() + "', nbr_likes_article= '"
                + article.getNbrLikesArticle() + "', image_article= '" + article.getImageArticle() + "', category_article= '"
                + article.getCategoryArticle() + "' WHERE id_article = " + article.getIdArticle() + "";
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
        String query = "SELECT * FROM ARTICLE";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Article article = new Article();
                article.setIdArticle(rs.getInt("id_article"));
                article.setTitreArticle(rs.getString("titre_article"));
                article.setDateArticle(rs.getDate("date_article"));
                article.setContentArticle(rs.getString("content_article"));
                article.setNbLikesArticle(rs.getInt("nbr_likes_article"));
                article.setImageArticle(rs.getString("image_article"));
                article.setCategoryArticle(rs.getString("category_article"));
                article.setIdUser(rs.getInt("id_user"));
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
                article.setNbLikesArticle(rs.getInt("nbr_likes_article"));
                article.setImageArticle(rs.getString("image_article"));
                article.setCategoryArticle(rs.getString("category_article"));
                article.setIdUser(rs.getInt("id_user"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return article;
    }

    public boolean afficher() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
