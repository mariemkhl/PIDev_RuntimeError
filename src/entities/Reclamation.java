/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
  import java.sql.Date;
/**
 *
 * @author user
 */
public class Reclamation {

    private int numero;
  
    private String commentaire ;
      
     private String typeReclamation;

    public Reclamation( int numero,String commentaire, String typeReclamation ) {
       
        this.numero = numero;
        this.commentaire = commentaire;
       
        this.typeReclamation = typeReclamation;
    }

    public Reclamation() {
    }

    

    public Reclamation(  String commentaire, String typeReclamation ) {
       
        this.commentaire = commentaire;
              this.typeReclamation = typeReclamation;
    }

    

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

      public void setTypeReclamation(String typeReclamation) {
        this.typeReclamation = typeReclamation;
    }

    public int getNumero() {
        return numero;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public String getTypeReclamation() {
        return typeReclamation;
    }
    

     

    
  
  
   
    
}
