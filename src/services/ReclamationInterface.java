/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;


import entities.Reclamation;
import java.util.List;
import javafx.collections.ObservableList;

/**
 *
 * @author 21695
 */
public interface ReclamationInterface {
      public void AjoutReclamation2(Reclamation R);
       public ObservableList <Reclamation> afficherReclamation();
       public void supprimer(Reclamation R );
       //public boolean supprimer(Reclamation R);
       public void modifier(Reclamation R);
         public List<Reclamation> rechercheReclamation(int numero);
}
    

