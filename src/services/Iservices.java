/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package services;

import java.util.List;

/**
 *
 * @author iheb debbech
 * @param <T>
 */

    public interface Iservices <T>{
    public void ajouter(T p);
    public void supprimer(String RefL);
    public void modifier(T p);
    public List<T> getAll();
    public T getOneById(String ref);
    
}
