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
public class ImageItem {
    
      private String title;
    private String imageUrl;
    
    public ImageItem(String title, String imageUrl) {
        this.title = title;
        this.imageUrl = imageUrl;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }
    
    public ImageItem() {}
    
    
    
}
