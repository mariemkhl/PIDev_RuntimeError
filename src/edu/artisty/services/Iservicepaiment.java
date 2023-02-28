/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import java.util.List;

/**
 *
 * @author user
   * @param <T>
 */
public interface Iservicepaiment <T> {
      public void ajouter(T p);
         public List<T> getAll();
    public T getOneById(int commande);
       
    
}
