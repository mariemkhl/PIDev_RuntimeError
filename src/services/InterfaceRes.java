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
public interface InterfaceRes<I> {

    public void ajouter(I r);

    public void supprimer(int id_res);

    public void modifier(I r);

    public List<I> getAll();

    public I getOneById(int id_res);

}
