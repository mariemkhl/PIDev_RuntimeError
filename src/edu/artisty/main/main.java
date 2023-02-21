/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.main;

import edu.artisty.entities.Artist;
import edu.artisty.entities.Customer;
import edu.artisty.entities.Utilisateur;
import edu.artisty.services.ServiceUtilisateur;
import edu.artisty.utils.Datasource;


/**
 *
 * @author khoul
 */
public class main {
     
         public static void main(String[] args) {
        
        /*Utilisateur u1 = new Utilisateur("Nour", "****","***","***",Type.Customer);*/
        
        
        ServiceUtilisateur sp = new ServiceUtilisateur();
          
       /* sp.ajouter(u);*/
           // sp.supprimer(1);
           
           //System.out.println(sp.getOneById(1));
       // Customer c=new Customer();
     //  Artist a=new Artist(" ff","","","","",);
   
       //  Customer c = new Customer("Nour", "mypassword", "nour@example.com", "123 Main St.", "Paris", "555-1234");


        Utilisateur u = new Utilisateur(7, "sondess", "t3bt", "chaima@esprit.tn","5552045");
       
//Customer c = new Customer(7,"salma", "laa", "nour@example.com", "hend", "555-1234");
//Artist a = new Artist (4, "Nourlhouda", "jrad", "nour@example.com","Paris", "555-1234","danseuese");
//sp.ajouter(u);
//System.out.println(a.getDomaine());
 //Artist a = new Artist("John", "password123", "john@example.com", "456 Park Ave.", "555-5678", "Music");
           /// sp.modifier(u,25);
         //   System.out.println(sp.getAllById(25));
         // for (Utilisateur i : sp.getAll()){
          // System.out.println(i);}
           //sp.RechercherUtilisateur(26);
      }
}
