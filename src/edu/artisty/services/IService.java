/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author yessmine
 * @param <T>
 */
public interface IService <T>{
    public void ajouter(T c);
    public void supprimer( int idcommande);
    public void modifier(T c, int idcommande);
    public List<T> getAll();
    public T getOneById(int idcommande);
    
    
    
 }
