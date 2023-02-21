/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;

/**
 *
 * @author khoul
 */
public class Customer extends Utilisateur {

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    String adresse;

    public Customer(int id, String username, String password, String email ,String adresse , String num_tel) {
        super(id, username, password, email, num_tel);
        this.adresse=adresse;
    }

    @Override
    public String toString() {
        return super.toString() + "adresse=" + adresse + '}';
    }
    
    
}
