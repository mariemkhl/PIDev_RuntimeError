/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;

import java.awt.Image;
import java.io.File;
import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author Nour Benkairia
 */
public class Product {
    public int id_p;
    public String nom;
    public String description;
    public double prix;
    public File img;
    public int cat_p;
    public int user_id;

    public Product() {
    }

    public Product(String nom, String description, double prix, int cat_p, int user_id) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.cat_p = cat_p;
        this.user_id = user_id;
    }
    

    public Product(String nom, String description, double prix, File img, int cat_p, int user_id) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.cat_p = cat_p;
        this.user_id = user_id;
    }

    public Product(int id_p, String nom, String description, double prix, File img, int cat_p, int user_id) {
        this.id_p = id_p;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.cat_p = cat_p;
        this.user_id = user_id;
    }

    public int getId_p() {
        return id_p;
    }

    public void setId_p(int id_p) {
        this.id_p = id_p;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public File getImg() {
        return img;
    }

    public void setImg(File img) {
        this.img = img;
    }

   


    public int getCat_p() {
        return cat_p;
    }

    public void setCat_p(int cat_p) {
        this.cat_p = cat_p;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Product{" + "nom=" + nom + ", description=" + description + ", prix=" + prix + ", img=" + img + ", cat_p=" + cat_p + ", user_id=" + user_id + '}';
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
        final Product other = (Product) obj;
        if (this.id_p != other.id_p) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
    
    
    
    
}
