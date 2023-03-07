/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;


import java.util.Objects;

/**
 *
 * @author iheb debbech
 */
public class Likes {
    private String refL;
    private int RefU;
    private String nom_u;

    public int getRefU() {
        return RefU;
    }

    public String getRefL() {
        return refL;
    }

    public void setRefL(String refL) {
        this.refL = refL;
    }

    public String getNom_u() {
        return nom_u;
    }

    public void setNom_u(String nom_u) {
        this.nom_u = nom_u;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Likes other = (Likes) obj;
        
        if (!Objects.equals(this.refL, other.refL)) {
            return false;
        }
        
        return true;
    }
    public Likes()
    {
        
    }
    public Likes(String refL, int RefU, String nom_u) {
        this.refL = refL;
        this.RefU = RefU;
        this.nom_u = nom_u;
    }

    public void setID_u(int ID_u) {
        this.RefU = RefU;
    }

    @Override
    public String toString() {
        return "Likes{" + "refL=" + refL + ", RefU=" + RefU + ", nom_u=" + nom_u + '}';
    }
    
    
}
