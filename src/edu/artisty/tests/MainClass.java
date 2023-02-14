/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.tests;

import edu.artisty.services.ArticleService;
import edu.artisty.services.CommentaireService;
import edu.artisty.utils.DataSource;
import java.sql.Date;
import edu.artisty.entities.Article;
import edu.artisty.entities.Commentaire;

/**
 *
 * @author MariemKhlifi
 */
public class MainClass {

    public static void main(String[] args) {

        DataSource ds = new DataSource();

        Article article = new Article(5, "new article",Date.valueOf("2023-02-07"), "salut", 3, "image", "livre", 3);
        ArticleService a = new ArticleService();
        //a.ajouter(article);
        a.supprimer(7);

        //articleService.ajouter(article);
        //  Article article = new Article(3,"mariemkl",Date.valueOf("2023-02-07"),"hello",0,"image","livre",1);
        //ArticleService articleService = new ArticleService();
        //System.out.println(articleService.getOneById(3));
        // System.out.println(articleService.getAll());
        // articleService.supprimer(2);
        //articleService.modifier(new Article(3,"emnouch",Date.valueOf("2023-02-07"),"hello",0,"image","livre",1));
        
        
        // *******************
        // CommentaireService commentaireService = new CommentaireService();
        // Article article = new Article();
        // Commentaire c = new Commentaire(6, article, "contentCommentaire", Date.valueOf("2023-02-07"), 2, true, 1);
        // commentaireService.ajouter(c);
        //commentaireService.modifier(c);
        //commentaireService.supprimer(2);
    }

}
