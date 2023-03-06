/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.main;

import edu.artisty.entities.Collection;
import edu.artisty.entities.Product;
import edu.artisty.services.CategoryService;
import edu.artisty.services.CollectionService;
import edu.artisty.services.ProductService;
import edu.artisty.utiles.DataSource;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.net.*;
import java.io.*;
import org.json.*;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import java.util.Scanner;

/**
 *
 * @author Nour Benkairia
 */
public class Main {
     public static void main(String args[]) throws IOException{
         //DataSource.getInstance();

         // Collection c1 = new Collection("music",1,"Prod1");
         //Collection c2 = new Collection(2,"sculpture",2,"Prod2");
         // Collection c3 = new Collection(2,"sculpture",2,"Prod2");
         //Troupe t3= new Troupe(3,"TAB",4,"TEST1");
        // CollectionService CS = new CollectionService();
        // CS.ajouter(c3);
         // CS.supprimer(c3);
         // CS.getAll();
         // CS.getOneById(1);
         // CS.modifier(c2);
         //CS.getOneByNom("sculpture");
        // CS.getOneByNom("bb");
        
         
         
         
//         Image myImage = Toolkit.getDefaultToolkit().getImage("C:/Users/Nour Benkairia/Documents/NetBeansProjects/ArtistyProject/src/edu/artisty/images.png");
//        Product p = new Product("dessin","art",12.00,myImage,1,2);
//
 //      ProductService Ps = new ProductService();
//Ps.ajouter(p);
//Ps.supprimer(8);
//Ps.getOneById(1);
//Ps.getAll();
//CategoryService cs= new CategoryService();
//         System.out.println(cs.getOneByName("Edition"));
//System.out.println(Ps.getOneByName("SS")) ;
//Ps.RechercherProduitByNom("SS");
//Ps.RechercherProduit(23);





//  Scanner scanner = new Scanner(System.in);
//        System.out.print("Enter the product name to search: ");
//        String productName = scanner.nextLine();
//
//        try {
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                .url("https://amazon-web-scraping-api.p.rapidapi.com/products/B091J3NYVF/reviews?=" + productName + "&page=1&countryCode=US&languageCode=EN")
//                .get()
//                .addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
//                .addHeader("X-RapidAPI-Host", "amazon-web-scraping-api.p.rapidapi.com")
//                .build();
//            Response response = client.newCall(request).execute();
//            System.out.println(response.body().string());
//        } catch (Exception e) {
//            System.err.println("Error: " + e.getMessage());
//        } finally {
//            scanner.close();
//        }
        
        
        
        
        
        
        
   
 
  Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the product name to search: ");
        String productName = scanner.nextLine();

        try {
//            OkHttpClient client = new OkHttpClient();
//
//            Request request = new Request.Builder()
//                    .url("https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/Search/ImageSearchAPI?q="+productName+"/%20grande&pageNumber=1&pageSize=10&autoCorrect=true")
//                    .get()
//                    .addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
//                    .addHeader("X-RapidAPI-Host", "contextualwebsearch-websearch-v1.p.rapidapi.com")
//                    .build();
//
//            Response response = client.newCall(request).execute();




//OkHttpClient client = new OkHttpClient();
//
//Request request = new Request.Builder()
//	.url("https://contextualwebsearch-websearch-v1.p.rapidapi.com/api/search/NewsSearchAPI?q=lady%20gaga&pageNumber=1&pageSize=10&autoCorrect=true&fromPublishedDate=null&toPublishedDate=null")
//	.get()
//	.addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
//	.addHeader("X-RapidAPI-Host", "contextualwebsearch-websearch-v1.p.rapidapi.com")
//	.build();
//
//Response response = client.newCall(request).execute();




OkHttpClient client = new OkHttpClient();

Request request = new Request.Builder()
	.url("https://large-text-to-speech.p.rapidapi.com/tts?id=5425b1c0-357b-47cf-a06c-69c5260ea890")
	.get()
	.addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
	.addHeader("X-RapidAPI-Host", "large-text-to-speech.p.rapidapi.com")
	.build();

Response response = client.newCall(request).execute();



           
            System.out.println(response.body().string());
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
        }








        
    }
}


 
     



         
     



