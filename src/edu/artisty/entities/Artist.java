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
public class Artist extends Utilisateur {
    
    String domaine;

    public Artist( int id, String username, String password, String email,String adresse, String num_tel, String domaine) {
        super(id, username, password, email,  num_tel);
        this.domaine = domaine;
    }
    


    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    public String toString() {
        
        return super.toString()+"Artist{" + "domaine=" + domaine + '}';
    }
    
}
