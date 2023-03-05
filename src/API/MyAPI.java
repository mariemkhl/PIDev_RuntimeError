/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 * @author Nour Benkairia
 */
public class MyAPI {
    
    private OkHttpClient client;
    
    public MyAPI() {
        client = new OkHttpClient();
    }
    
    public Response postRequest() throws Exception {
        Request request = new Request.Builder()
            .url("https://exposure-rating.p.rapidapi.com/image/rating/exposure_rating")
            .post(null)
            .addHeader("X-RapidAPI-Key", "5161459c8bmshf008d3701904ef8p1af662jsn661edee646ab")
            .addHeader("X-RapidAPI-Host", "exposure-rating.p.rapidapi.com")
            .build();
        
        Response response = client.newCall(request).execute();
        return response;
    }
    
    
}
