/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import java.util.List;


/**
 *
 * @author khoul
 */
public interface IService <T>{
    public void ajouter( T u   );
    public void supprimer(int Id_user ); 
    public void modifier(T u, int Id_user);
    public List<T> getAll();
    public T getOneById(int Id_user );
    public boolean RechercherUtilisateur(int Id_user);
    public void Authentification(String email , String Password)  ;
    
   
    
}
