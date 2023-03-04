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
import java.util.List;

/**
 *
 * @author MariemKhlifi
 */
public class MainClass {

    public static void main(String[] args) throws Exception {

        DataSource ds = new DataSource();
//  try {
//            ArticleService articleService = new ArticleService();
//            String recipient = "khlifi.mariem@esprit.tn";
//            articleService.sendMail(recipient);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }


 Article article = new Article(5, "new article",Date.valueOf("2023-02-07"), "new content ", 3, "image", "livre", 3);
//Article article= new Article();
        ArticleService a = new ArticleService();
        a.shareOnPage(article);
        //a.ajouter(article);
       // a.supprimer(7);
       
       
        
//***********************filtrage***********************************//
    // Call the filtrerMotsInappropries method
  //  a.filtrerMotsInappropries();
    

//****************************chercher article**********************************//

// ArticleService articleService = new ArticleService();
//    
//    // Chercher les articles ayant "new" dans leur titre
//    List<Article> articlesWithTitleNew = articleService.chercherArticle("Title 1", null);
//    
//    System.out.println("Articles avec le titre 'Title 1':");
//    System.out.println("----------------------------------");
//    articlesWithTitleNew.forEach((article) -> {
//        System.out.println(article);
//        });
//    System.out.println();



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
