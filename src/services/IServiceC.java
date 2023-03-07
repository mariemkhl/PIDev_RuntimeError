/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Commande;
import java.sql.Date;
import java.util.List;

/**
 *
 * @author yessmine
 * @param <T>
 */
public interface IServiceC <T>{
    public void ajouter(T c);
    public void supprimer( Commande commande);
    public void modifier(Commande c);
    public List<T> getAll();
    public T getOneById(int commande);
    
    
    
 }
