/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.tests;

import edu.artisty.entities.Commande;
import edu.artisty.services.ReceiptPrinter;
import edu.artisty.services.ServiceCommande;
import edu.artisty.utils.DataSource;
import java.io.IOException;
import java.util.Date;
/**
 *
 * @author yessmine
 */
public class MainClass {
    
    public static void main(String[] args) {
  
//    launch(args);  
    Commande c1 = new Commande(4,50,"Yasmine","carte");
    Commande c2 = new Commande (6,"hajer","cash");
                
        ServiceCommande sc = new ServiceCommande();
        sc.modifier(c1, 38);
    } 
    }

  //  String receiptText = "-------------------------\n" +
//                         "REÇU\n" +
//                         "-------------------------\n" +
//                         "Article 1\t$10.00\n" +
//                         "Article 2\t$15.00\n" +
//                         "Article 3\t$20.00\n" +
//                         "-------------------------\n" +
//                         "TOTAL\t$45.00\n" +
//                         "-------------------------";

    // Créer une instance de ReceiptPrinter avec le texte du reçu
   // ReceiptPrinter receiptPrinter = new ReceiptPrinter(receiptText);

    // Appeler la méthode printReceipt pour imprimer le reçu
    //receiptPrinter.printReceipt();
    
//    }
//    
      
    

