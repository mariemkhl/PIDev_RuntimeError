/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.main;

import edu.artisty.entities.Collection;
import edu.artisty.entities.Product;
import edu.artisty.services.CollectionService;
import edu.artisty.services.ProductService;
import edu.artisty.utiles.DataSource;
import java.awt.Image;
import java.awt.Toolkit;

/**
 *
 * @author Nour Benkairia
 */
public class Main {
     public static void main(String args[]){
         //DataSource.getInstance();

         // Collection c1 = new Collection("music",1,"Prod1");
         //Collection c2 = new Collection(2,"sculpture",2,"Prod2");
         // Collection c3 = new Collection(2,"sculpture",2,"Prod2");
         //Troupe t3= new Troupe(3,"TAB",4,"TEST1");
        // CollectionService CS = new CollectionService();
        // CS.ajouter(c3);
         // CS.supprimer(c3);
         // CS.getAll();
         // CS.getOneById(1);
         // CS.modifier(c2);
         //CS.getOneByNom("sculpture");
        // CS.getOneByNom("bb");
        
         
         
         
//         Image myImage = Toolkit.getDefaultToolkit().getImage("C:/Users/Nour Benkairia/Documents/NetBeansProjects/ArtistyProject/src/edu/artisty/images.png");
//        Product p = new Product("dessin","art",12.00,myImage,1,2);
//
       ProductService Ps = new ProductService();
//Ps.ajouter(p);
//Ps.supprimer(8);
//Ps.getOneById(1);
//Ps.getAll();
Ps.getOneByName("SS");
//Ps.RechercherProduitByNom("SS");
//Ps.RechercherProduit(23);
         
 
    
    
    
     }
    
}
