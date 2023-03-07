/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

/**
 *
 * @author ASUS
 */
public interface IServiceR<T> {

    public void ajouter(T e);

    public void supprimer(int id_event);

    public void modifier(T e,int i);

    public List<T> getAll();

    public T getOneByName(String nameEv);
}
