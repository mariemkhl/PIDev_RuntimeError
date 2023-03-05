/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.entities;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author Nour Benkairia
 */
public class ProductListCell extends ListCell<Product> {

    Product product = new Product();
    private ImageView imageView = new ImageView();
    private Label nameLabel = new Label();
    private Label priceLabel = new Label();
    private Label urlLabel = new Label();
    private VBox vBox = new VBox(nameLabel, priceLabel, urlLabel, imageView);

    public ProductListCell() {
        super();
        vBox.getStyleClass().add("cart1");
        nameLabel.getStyleClass().add("PROD1");
        priceLabel.getStyleClass().add("prix1");
        urlLabel.getStyleClass().add("url1");
//        vBox.getChildren().addAll(nameLabel, priceLabel, urlLabel, imageView);
        setGraphic(vBox);
    }
    

    @Override
    protected void updateItem(Product product, boolean empty) {
        super.updateItem(product, empty);

        if (empty || product == null) {
            setText(null);
            setGraphic(null);
        } else {

            nameLabel.setText(product.getNom());
            nameLabel.setPrefWidth(50); 
            nameLabel.setPrefHeight(50);
         priceLabel.setText("$ "+ product.getPrix());
            urlLabel.setText(product.getUrl());
            File file = new File(""+ product.getImg());
            Image img = new Image(file.toURI().toString());
//            File imageFile = product.getImg();
//            Image image = new Image(imageFile.toURI().toString());
//            imageView.setImage(image);
            imageView.setImage(img);
                    imageView.setFitWidth(400);
                    imageView.setFitHeight(200);
 
            setText(null);
            setGraphic(vBox);
        }
    }

    public Label getUrlLabel() {
        return urlLabel;
    }

    public void setUrlLabel(Label urlLabel) {
        this.urlLabel = urlLabel;
    }
    
    
    
}
   

