/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import edu.artisty.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import edu.artisty.entities.Article;
import edu.artisty.entities.Commentaire;

/**
 *
 * @author MariemKhlifi
 */
public class CommentaireService implements IService<Commentaire> {

    Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Commentaire commentaire) {
        String query = "INSERT INTO COMMENTAIRE(id_article,content_commentaire,date_commentaire,nb_likes_commentaire,etat_commentaire,"
                + "id_user) VALUES(?,?,?,?,?,?)";
        try {
            PreparedStatement ste = cnx.prepareStatement(query);
            ste.setInt(1, commentaire.getArticle().getIdArticle());
            ste.setString(2, commentaire.getContentCommentaire());
            ste.setDate(3, commentaire.getDateCommentaire());
            ste.setInt(4, commentaire.getNbLikesCommentaire());
            ste.setBoolean(5, commentaire.isEtatCommentaire());
            ste.setInt(6, commentaire.getIdUser());
            ste.executeUpdate();
            System.out.println("Commentaire Added Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void supprimer(int idComentaire) {
        String query = "DELETE FROM COMMENTAIRE WHERE id_commentaire = " + idComentaire + "";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Commentaire Deleted Successfully");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void modifier(Commentaire commentaire) {
        String query = "UPDATE COMMENTAIRE SET content_commentaire = '" + commentaire.getContentCommentaire()
                + "', date_commentaire= '" + commentaire.getDateCommentaire() + "', nb_likes_commentaire= '"
                + commentaire.getNbLikesCommentaire() + "', etat_commentaire= '" + (commentaire.isEtatCommentaire() ? 1 : 0) + "'"
                + "WHERE id_commentaire = " + commentaire.getIdCommentaire() + " ";
        try {
            Statement ste = cnx.createStatement();
            ste.executeUpdate(query);
            System.out.println("Commentaire Updated Successfully ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Commentaire> getAll() {
        List<Commentaire> listeCommentaires = new ArrayList<>();
        String query = "SELECT * FROM COMMENTAIRE";
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Commentaire commentaire = new Commentaire();
                ArticleService articleService = new ArticleService();
                Article article = new Article();
                article = articleService.getOneById(rs.getInt("id_article"));
                commentaire.setArticle(article);
                commentaire.setContentCommentaire(rs.getString("content_commentaire"));
                commentaire.setDateCommentaire(rs.getDate("date_commentaire"));
                commentaire.setNbLikesCommentaire(rs.getInt("nb_likes_commentaire"));
                commentaire.setEtatCommentaire(rs.getBoolean("etat_commentaire"));
                commentaire.setIdUser(rs.getInt("id_user"));
                listeCommentaires.add(commentaire);
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return listeCommentaires;
    }

    @Override
    public Commentaire getOneById(int idCommentaire) {
        String query = "SELECT * FROM COMMENTAIRE WHERE id_commentaire = " + idCommentaire + "";
        Commentaire commentaire = new Commentaire();
        try {
            Statement ste = cnx.createStatement();
            ResultSet rs = ste.executeQuery(query);
            while (rs.next()) {
                Article article = new Article();
                ArticleService articleService = new ArticleService();
                article = articleService.getOneById(rs.getInt("id_article"));
                commentaire.setArticle(article);
                commentaire.setContentCommentaire(rs.getString("content_commentaire"));
                commentaire.setDateCommentaire(rs.getDate("date_commentaire"));
                commentaire.setNbLikesCommentaire(rs.getInt("nb_likes_commentaire"));
                commentaire.setEtatCommentaire(rs.getBoolean("etat_commentaire"));
                commentaire.setIdUser(rs.getInt("id_user"));
            }
        } catch (SQLException e) {
            e.getMessage();
        }
        return commentaire;
    }

}
