/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;
import edu.artisty.entities.Paying;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import edu.artisty.services.ServiceCommande;
import edu.artisty.entities.Commande;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import edu.artisty.services.ServiceCommande;
import edu.artisty.entities.Commande;

/**
 *
 * @author user
 */
public class Service_Paiment {
    
    public static void main(String[] args) {
        try {
      String apiKey = "your_api_key";
      String apiUsername = "your_api_username";
      String programId = "your_program_id";
      String apiUrl = "https://api.payoneer.com/v2/programs/" + programId + "/payouts";
      
      // Set up the request body
      String requestBody = "client_reference_id=123456789&amount=100.00&currency=USD&description=Test+payment";
      
      // Set up the request headers
      String authorization = "Basic " + encodeBase64(apiUsername + ":" + apiKey);
      String contentType = "application/x-www-form-urlencoded";
      
      // Send the request
      URL url = new URL(apiUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("POST");
      connection.setRequestProperty("Authorization", authorization);
      connection.setRequestProperty("Content-Type", contentType);
      connection.setDoOutput(true);
      OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
      writer.write(requestBody);
      writer.flush();
      
      StringBuilder response = new StringBuilder();
        try ( // Get the response
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            response = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                response.append(line);
            } }
      
      // Print the response
      System.out.println(response.toString());
      
    } catch (Exception e) {
      e.printStackTrace();
    }
    }
  
  
  private static String encodeBase64(String text) {
    return new String(java.util.Base64.getEncoder().encode(text.getBytes()));
  }
}
    

