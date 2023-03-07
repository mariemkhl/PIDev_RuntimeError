/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import entities.Category;
import java.awt.Image;
import java.io.File;
import java.sql.Blob;
import java.util.Objects;

/**
 *
 * @author Nour Benkairia
 */
public class Product {
    private int id_p;
    private String nom;
    private String description;
    private double prix;
    private File img;
    private Category cat_p;
    private int user_id;
    private String url;

    public Product() {
    }

    
    
    public Product(int id_p, String nom, double prix) {
        this.id_p = id_p;
        this.nom = nom;
        this.prix = prix;
    }

    public Product(int id_p, String nom, double prix, File img, String url) {
        this.id_p = id_p;
        this.nom = nom;
        this.prix = prix;
        this.img = img;
        this.url = url;
    }
    
    

    public Product(String nom, String description, double prix, Category cat_p, int user_id) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.cat_p = cat_p;
        this.user_id = user_id;
    }
    

    public Product(String nom, String description, double prix, File img, Category cat_p, int user_id) {
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.cat_p = cat_p;
        this.user_id = user_id;
    }

    public Product(int id_p, String nom, String description, double prix, File img, Category cat_p, int user_id) {
        this.id_p = id_p;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.cat_p = cat_p;
        this.user_id = user_id;
    }

    public Product(int id_p, String nom, String description, double prix, File img, Category cat_p, int user_id, String url) {
        this.id_p = id_p;
        this.nom = nom;
        this.description = description;
        this.prix = prix;
        this.img = img;
        this.cat_p = cat_p;
        this.user_id = user_id;
        this.url = url;
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

    public Category getCat_p() {
        return cat_p;
    }

    public void setCat_p(Category cat_p) {
        this.cat_p = cat_p;
    }

   
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Product{" + "id_p=" + id_p + ", nom=" + nom + ", description=" + description + ", prix=" + prix + ", img=" + img + ", cat_p=" + cat_p + ", user_id=" + user_id + ", url=" + url + '}';
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
