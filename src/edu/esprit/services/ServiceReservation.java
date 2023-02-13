/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

import edu.esprit.entities.Reservation;
import edu.esprit.utils.MyConnection;
import java.sql.Connection;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class ServiceReservation implements InterfaceRes <Reservation> {

        Connection cnx = MyConnection.getInstance().getCnx();

    
    @Override
    public void ajouter(Reservation r) {
        String req ="";
    }

    @Override
    public void supprimer(int id_res) {
    }

    @Override
    public void modifier(Reservation r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Reservation> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
