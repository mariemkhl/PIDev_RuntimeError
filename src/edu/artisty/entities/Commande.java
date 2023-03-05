/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;
import java.util.Date;

/**
 *
 * @author yessmine
 */
public class Commande {
    private int idcommande;
    private int prix_tot;
    private String userid ;
    private String payment ; 
    private String date_creation ;

    private enum userid {
    nom, prenom, id
    }
    public Commande() {
    }
    
    public  Commande(int prix_tot, String userid, String payment) {
        this.prix_tot = prix_tot ;
        this.userid= userid;
        this.payment= payment;
    }
    

    public  Commande(int idcommande, int prix_tot, String userid, String payment, String date_creation) {
        this.idcommande = idcommande;
        this.prix_tot = prix_tot;
        this.userid = userid;
        this.payment= payment;
        this.date_creation = date_creation;
        
    }

    public Commande(int idcommande, int prix_tot, String userid) {
        this.idcommande = idcommande;
        this.prix_tot = prix_tot;
        this.userid = userid;    }

    public Commande(String userid, String payment) {
        this.userid=userid;
        this.payment=payment;
    }

        public Commande(String userid, int prix_tot, String payment, String date_creation) {
        this.userid=userid;
        this.prix_tot=prix_tot;
        this.payment=payment;
        this.date_creation = date_creation;
    }

           public Commande(int idcommande, int prix_tot, String payment, String date_creation) {
        this.idcommande=idcommande;
        this.prix_tot=prix_tot;
        this.payment=payment;
        this.date_creation = date_creation;
           }
        
    public Commande( int prix_tot, String userid, String payment, String date_creation) {
        this.idcommande = idcommande;
        this.prix_tot = prix_tot;
        this.userid = userid;
        this.payment= payment;
        this.date_creation = date_creation;
    }



    public int getId() {
        return idcommande;
    }

    public int getPrix_tot() {
        return prix_tot;
    }

    public String getPayment() {
        return payment;
    }

    public String getDate_creation() {
        return date_creation;
    }

    

    public String getuserid() {
        return userid;
    }

    public void setId(int idcommande) {
        this.idcommande = idcommande;
    }

    public void setPrix_tot(int prix_tot) {
        this.prix_tot = prix_tot;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public void setDate_creation(String date_creation) {
        this.date_creation = date_creation;
    }

    public void setuserid(String userid) {
        this.userid = userid;
    }


    @Override
    public String toString() {
        return "Commande{" + "idcommande=" + idcommande + ", prix_tot=" + prix_tot + ", userid=" + userid + ", payment=" + payment + ", date_creation=" + date_creation + '}';
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
        final Commande other = (Commande) obj;
        if (this.idcommande != other.idcommande) {
            return false;
        }
        return true;
    }
    
}
