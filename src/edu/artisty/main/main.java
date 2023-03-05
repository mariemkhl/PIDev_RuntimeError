/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.main;

import edu.artisty.entities.Artist;
import edu.artisty.entities.Customer;
import edu.artisty.entities.Utilisateur;
import edu.artisty.services.Mail;
import edu.artisty.services.ServiceUtilisateur;
import edu.artisty.utils.Datasource;
import java.util.List;


/**
 *
 * @author khoul
 */
public class main {
     
         public static void main(String[] args) throws Exception {
        
        /*Utilisateur u1 = new Utilisateur("Nour", "****","***","***",Type.Customer);*/
        
        
        ServiceUtilisateur sp = new ServiceUtilisateur();
        int code=20;
             Mail.sendMail("khlifi.mariem@esprit.tn", code);
             
            /* System.out.println("_____________________");
       System.out.println("Tri ASC :");
      List<Utilisateur> listc=sp.getAll() ; 
       List<Utilisateur> list_tri=sp.trier(listc) ; 
       System.out.println(list_tri);
      
       System.out.println("_____________________");
       System.out.println("Recherche :");
       List<Utilisateur> listr=sp.getAll() ;
       List<Utilisateur> listr_recherche=sp.rechercher(listr,"lotfi") ; 
              System.out.println(listr_recherche);
       /* sp.ajouter(u);*/
           // sp.supprimer(1);
           
           //System.out.println(sp.getOneById(1));
       // Customer c=new Customer();
     //  Artist a=new Artist(" ff","","","","",);
   
       //  Customer c = new Customer("Nour", "mypassword", "nour@example.com", "123 Main St.", "Paris", "555-1234");


      // Utilisateur u = new Utilisateur(7, "ahllmed", "sarrra", "chaima@esprit.tn","5552045");
       
//Customer c = new Customer(7,"khouloud", "boussaha", "khouloud.boussaha99@gmailcom", "hend", "555-1234");
//System.out.println(c.getClass());
//Artist a= new Artist(7,"rajel","mchrajel","khouloud","dhia","hjk","fefa");
//sp.ajouter(c);
//System.out.println(a.getDomaine());
 //Artist a = new Artist("John", "password123", "john@example.com", "456 Park Ave.", "555-5678", "Music");
           /// sp.modifier(u,25);
         //   System.out.println(sp.getAllById(25));
         // for (Utilisateur i : sp.getAll()){
          // System.out.println(i);}
           //sp.RechercherUtilisateur(26);
      }
}
