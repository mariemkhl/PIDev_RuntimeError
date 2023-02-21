/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;

import java.util.Objects;

/**
 *
 * @author Nour Benkairia
 */
public class Collection {
    public int id_col;
    public String nom_col;
    public int id_p;
    public String nom_p;

    public Collection() {
    }

    public Collection(int id_col) {
        this.id_col = id_col;
    }

    public Collection(String nom_col) {
        this.nom_col = nom_col;
    }
    

    public Collection(String nom_col, int id_p, String nom_p) {
        this.nom_col = nom_col;
        this.id_p = id_p;
        this.nom_p = nom_p;
    }

    public Collection(int id_col, String nom_col, int id_p, String nom_p) {
        this.id_col = id_col;
        this.nom_col = nom_col;
        this.id_p = id_p;
        this.nom_p = nom_p;
    }

    public int getId_col() {
        return id_col;
    }

    public void setId_col(int id_col) {
        this.id_col = id_col;
    }

    public String getNom_col() {
        return nom_col;
    }

    public void setNom_col(String nom_col) {
        this.nom_col = nom_col;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getNom_p() {
        return nom_p;
    }

    public void setNom_p(String nom_p) {
        this.nom_p = nom_p;
    }

    @Override
    public String toString() {
        return "Collection{" + "nom_col=" + nom_col + ", id_p=" + id_p + ", nom_p=" + nom_p + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Collection other = (Collection) obj;
        if (this.id_col != other.id_col) {
            return false;
        }
        if (!Objects.equals(this.nom_col, other.nom_col)) {
            return false;
        }
        return true;
    }
    
    
    
    
}
