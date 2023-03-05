/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;
import java.util.Date;
/**
 *
 * @author user
 */
public class PAIMENT {

private int num_carte ;
private String nom_carte;
private Date date_ex ;
private int CV_code ;
private int commande ;
private int prix_tot ;

   
private enum commande {
  idcommande, prix_tot, userid, payment,date_creation }

 public PAIMENT(int num_carte, String nom_carte, int CV_code) {
     this.num_carte=num_carte;
        this.nom_carte= nom_carte;
        
        this.CV_code=CV_code;
    }
    public PAIMENT(int num_carte, String nom_carte, Date date_ex, int CV_code, int prix_tot, int commande) {
        this.num_carte=num_carte;
        this.nom_carte= nom_carte;
        this.date_ex = date_ex;
        this.CV_code=CV_code;
        this.prix_tot=prix_tot;
        this.commande=commande;
    
    }
    
        public PAIMENT(int num_carte, String nom_carte, int CV_code, int prix_tot, int commande) {
        this.num_carte=num_carte;
        this.nom_carte= nom_carte;
        this.CV_code=CV_code;
        this.prix_tot=prix_tot;
        this.commande=commande;
    
    }
        
         public PAIMENT(int num_carte, String nom_carte, Date date_ex, int CV_code, int prix_tot) {
        this.num_carte=num_carte;
        this.nom_carte= nom_carte;
        this.date_ex = date_ex;
        this.CV_code=CV_code;
        this.prix_tot=prix_tot;
           
    }
        
         public PAIMENT(int num_carte, String nom_carte, int CV_code, int prix_tot) {
        this.num_carte=num_carte;
        this.nom_carte= nom_carte;
        this.CV_code=CV_code;
        this.prix_tot=prix_tot;
           
    }
        
        public PAIMENT() {
    }

    public int getPrix_tot() {
        return prix_tot;
    }

    public void setPrix_tot(int prix_tot) {
        this.prix_tot = prix_tot;
    }
    
    
    public int getNum_carte() {
        return num_carte;
    }

    public void setNum_carte(int num_carte) {
        this.num_carte = num_carte;
    }

    public int getCV_code() {
        return CV_code;
    }

    public void setCV_code(int CV_code) {
        this.CV_code = CV_code;
    }

    public Date getDate_ex() {
        return date_ex;
    }

    public void setDate_ex(Date date_ex) {
        this.date_ex = date_ex;
    }

    public String getNom_carte() {
        return nom_carte;
    }

    public void setNom_carte(String nom_carte) {
        this.nom_carte = nom_carte;
    }

    public int getcommande() {
        return commande;
    }

    public void setcommande(int commande) {
        this.commande = commande;
    }

    @Override
    public String toString() {
        return "PAIMENT{" + "num_carte=" + num_carte + ", nom_carte=" + nom_carte + ", date_ex=" + date_ex + ", CV_code=" + CV_code + ", commande=" + commande + ", prix_tot=" + prix_tot + '}';
    }

   

    @Override
    public int hashCode() {
        int hash = 7;
              return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PAIMENT other = (PAIMENT) obj;
        if (this.commande != other.commande) {
            return false;
        }
        return true;
    }
   


}

