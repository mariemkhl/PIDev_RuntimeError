/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.tests;

import edu.artisty.entities.Commande;
import edu.artisty.entities.Reclamation;
import edu.artisty.entities.PAIMENT;
import edu.artisty.services.ReclamationCRUD;
import edu.artisty.services.ServicePaiment;
import edu.artisty.services.ServiceCommande;
import edu.artisty.utils.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author yessmine
 */
public class MainClass {
    
    public static void main(String[] args) {
  
    
       
            //Commande c1 = new Commande(50,"alice","carte","12/12/2023");
            Commande c3 = new Commande (7,"ttt","ttt");
            ServiceCommande sc = new ServiceCommande();
//    Reclamation r1 = new Reclamation ("description erronée","authenticité");
//    ReclamationCRUD rc = new ReclamationCRUD();
sc.rechercheParpaiment("ttt");
//sc.exists("xcvbn");



//  sc.rechercheParpaiment("carte");
        
//    PAIMENT p1 = new PAIMENT (8425,"KATIPATI",8235,80);
//    PAIMENT p2 = new PAIMENT (8425,"yassmine",8235,80);
//    ServicePaiment cp = new ServicePaiment() ;
//    cp.getOneById(3);
      


    
    } 
    }
