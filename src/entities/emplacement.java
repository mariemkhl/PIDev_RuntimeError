/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 *
 * @author iheb debbech
 */
public class emplacement {
        private String nomplace;
    private String description;
    private String lien;
    private String image;
    private int nblikes;
    private Double Latitude;
    private Double Longitude;
    private String categorie;

    public emplacement() {
    }
      
    public emplacement(String nomplace,String description, String lien, String image, int nblikes, Double Latitude, Double Longitude, String categorie) {
        this.nomplace = nomplace;
        this.lien = lien;
        this.image = image;
        this.description=description;
        this.nblikes = nblikes;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.categorie = categorie;
    }

    public emplacement(String nomplace, String description, String lien, String image, Double Latitude, Double Longitude, String categorie) {
        this.nomplace = nomplace;
        this.description = description;
        this.lien = lien;
        this.image = image;
        this.Latitude = Latitude;
        this.Longitude = Longitude;
        this.categorie = categorie;
    }

   

    public void setNomplace(String nomplace) {
        this.nomplace = nomplace;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
    }

    public void setLien(String lien) {
        this.lien = lien;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNblikes(int nblikes) {
        this.nblikes = nblikes;
    }

    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }

    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getNomplace() {
        return nomplace;
    }

    public String getDescription() {
        return description;
    }

    public String getLien() {
        return lien;
    }

    public String getImage() {
        return image;
    }

    public int getNblikes() {
        return nblikes;
    }

    public Double getLatitude() {
        return Latitude;
    }

    public Double getLongitude() {
        return Longitude;
    }

    public String getCategorie() {
        return categorie;
    }
    
}
