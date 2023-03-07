/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import entities.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nour Benkairia
 */
public class ArtsyAPI {
    
    

    private static final String API_BASE_URL = "https://api.artsy.net/api/";
    private static final String API_CLIENT_ID = "YOUR_CLIENT_ID";
    private static final String API_CLIENT_SECRET = "YOUR_CLIENT_SECRET";

    public static List<Product> searchArtworks(String query) throws IOException {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(API_BASE_URL + "search?q=" + query + "&size=10&type=artwork")
                .header("X-Xapp-Token", API_CLIENT_ID + ":" + API_CLIENT_SECRET)
                .build();

        Response response = client.newCall(request).execute();
        String responseBody = response.body().string();

        Gson gson = new Gson();
        JsonObject json = gson.fromJson(responseBody, JsonObject.class);

        List<Product> artworks = new ArrayList<>();
        for (int i = 0; i < json.getAsJsonArray("_embedded").get(0).getAsJsonObject().getAsJsonArray("results").size(); i++) {
            Product artwork = gson.fromJson(json.getAsJsonArray("_embedded").get(0).getAsJsonObject().getAsJsonArray("results").get(i), Product.class);
            artworks.add(artwork);
        }

        return artworks;
    }
}





    

