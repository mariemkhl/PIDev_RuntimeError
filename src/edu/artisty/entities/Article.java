/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;

import java.sql.Date;

/**
 *
 * @author MariemKhlifi
 */
public class Article {

    private int idArticle;
    private String titreArticle;
    private Date dateArticle;
    private String contentArticle;
    private int nbrLikesArticle;
    private String imageArticle;
    private String categoryArticle;
    // TODO
    private int idUser;

    public Article() {
    }

    public Article(String titreArticle, Date dateArticle, String contentArticle, int nbrLikesArticle, String imageArticle, String categoryArticle, int idUser) {
        this.titreArticle = titreArticle;
        this.dateArticle = dateArticle;
        this.contentArticle = contentArticle;
        this.nbrLikesArticle = nbrLikesArticle;
        this.imageArticle = imageArticle;
        this.categoryArticle = categoryArticle;
        this.idUser = idUser;
    }

    public Article(int idArticle, String titreArticle, Date dateArticle, String contentArticle, int nbrLikesArticle, String imageArticle, String categoryArticle, int idUser) {
        this.idArticle = idArticle;
        this.titreArticle = titreArticle;
        this.dateArticle = dateArticle;
        this.contentArticle = contentArticle;
        this.nbrLikesArticle = nbrLikesArticle;
        this.imageArticle = imageArticle;
        this.categoryArticle = categoryArticle;
        this.idUser = idUser;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getTitreArticle() {
        return titreArticle;
    }

    public void setTitreArticle(String titreArticle) {
        this.titreArticle = titreArticle;
    }

    public Date getDateArticle() {
        return dateArticle;
    }

    public void setDateArticle(Date dateArticle) {
        this.dateArticle = dateArticle;
    }

    public String getContentArticle() {
        return contentArticle;
    }

    public void setContentArticle(String contentArticle) {
        this.contentArticle = contentArticle;
    }

    public int getNbrLikesArticle() {
        return nbrLikesArticle;
    }

    public void setNbLikesArticle(int nbrLikesArticle) {
        this.nbrLikesArticle = nbrLikesArticle;
    }

    public String getImageArticle() {
        return imageArticle;
    }

    public void setImageArticle(String imageArticle) {
        this.imageArticle = imageArticle;
    }

    public String getCategoryArticle() {
        return categoryArticle;
    }

    public void setCategoryArticle(String categoryArticle) {
        this.categoryArticle = categoryArticle;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Article{" + "idArticle=" + idArticle + ", titreArticle=" + titreArticle + ", dateArticle=" + dateArticle + ", contentArticle=" + contentArticle + ", nbrLikesArticle=" + nbrLikesArticle + ", imageArticle=" + imageArticle + ", categoryArticle=" + categoryArticle + ", idUser=" + idUser + '}';
    }

}
