/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.artisty.services;

//import okhttp3.FormBody;
import java.io.IOException;
import okhttp3.Credentials;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONObject;
import java.io.IOException;
import okhttp3.*;


/**
 *
 * @author User
 */
public class ApiTranslate {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient();

//        Response response = client.newCall(request).execute();
        RequestBody body = new FormBody.Builder()
                .add("q", "hello")
                .add("target", "fr")
                .build();

        Request request = new Request.Builder()
                .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .addHeader("Accept-Encoding", "application/gzip")
                .addHeader("X-RapidAPI-Key", "c18264e0acmshb87e57a497397a6p1f81d3jsnf873e4869b66")
                .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        if (response.isSuccessful()) {
            String responseBody = response.body().string();
            JSONObject json = new JSONObject(responseBody);
            String translatedText = json.getJSONObject("data")
                    .getJSONArray("translations")
                    .getJSONObject(0)
                    .getString("translatedText");
            System.out.println(translatedText);
        } else {
            System.out.println("Request failed");
        }
    }
}

//
//MediaType mediaType = MediaType.parse("application/json");
//String value = "{\n  \"messages\": [\n    {\n      \"source\": \"mashape\",\n      \"from\": \"Test\",\n      \"body\": \"This is a test\",\n      \"to\": \"+21656699958\",\n      \"schedule\": \"1452244637\",\n      \"custom_string\": \"this is a test\"\n    }\n  ]\n}";
//RequestBody body = RequestBody.create(mediaType, value);
//Request request = new Request.Builder()
//        .url("https://clicksend.p.rapidapi.com/sms/send")
//        .post(body)
//        .addHeader("content-type", "application/json")
//        .addHeader("Authorization", " Basic bWFyaWVtIGtobGlmaTptYXJpZW1raGxpZmkyODIzODExNA==")
//        .addHeader("Content-Type", "application/json")
//        .addHeader("X-RapidAPI-Key", "b56bb89a07msh6f8377f22380a43p1ae033jsn119c07392b4e")
//        .addHeader("X-RapidAPI-Host", "clicksend.p.rapidapi.com")
//        .build();
//
//Response response = client.newCall(request).execute();
//
//String responseBody = response.body().string();
//System.out.println(responseBody);
//
//
//        
//        
////          OkHttpClient client = new OkHttpClient();
////
////        MediaType mediaType = MediaType.parse("application/json");
////        String value = "{\n  \"messages\": [\n    {\n      \"source\": \"mashape\",\n      \"from\": \"Test\",\n      \"body\": \"This is a test\",\n      \"to\": \"+21656699958\",\n      \"schedule\": \"1452244637\",\n      \"custom_string\": \"this is a test\"\n    }\n  ]\n}";
////        RequestBody body = RequestBody.create(mediaType, value);
////        Request request = new Request.Builder()
////                .url("https://clicksend.p.rapidapi.com/sms/send")
////                .post(body)
////                .addHeader("content-type", "application/json")
////                .addHeader("Authorization", "undefined")
////                .addHeader("Content-Type", "application/json")
////                .addHeader("X-RapidAPI-Key", "b56bb89a07msh6f8377f22380a43p1ae033jsn119c07392b4e")
////                .addHeader("X-RapidAPI-Host", "clicksend.p.rapidapi.com")
////                .build();
////
////        Response response = client.newCall(request).execute();
//        
//           RequestBody body = new FormBody.Builder()
//            .add("q", "hello dear friends !")
//            .add("target", "fr")
//            .build();
//
//           Request request = new Request.Builder()
//           .url("https://google-translate1.p.rapidapi.com/language/translate/v2")
//           .post(body)
//           .addHeader("content-type", "application/x-www-form-urlencoded")
//           .addHeader("Accept-Encoding", "application/gzip")
//           .addHeader("X-RapidAPI-Key", "b56bb89a07msh6f8377f22380a43p1ae033jsn119c07392b4e")
//           .addHeader("X-RapidAPI-Host", "google-translate1.p.rapidapi.com")
//           .build();
//
//            Response response = client.newCall(request).execute();
//            if (response.isSuccessful()){
//            String responseBody = response.body().string();
//            JSONObject json = new JSONObject(responseBody);
//            String translatedText = json.getJSONObject("data")
//                .getJSONArray("translations")
//                .getJSONObject(0)
//                .getString("translatedText");
//            System.out.println(translatedText); 
//            } else {
//            System.out.println("Request failed");
//            }
//          
//        public static final String ACCOUNT_SID = "AC02908980344cad799b56226aeb5ccf96";
//    public static final String AUTH_TOKEN = "0c96f35933aab8618fdbe45fd5b3418b";
//
//    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//
//        MessageCreator message = Message.creator(
//                new PhoneNumber("+21656699958"),
//                new PhoneNumber("+19035467504"),
//                "Salut Mr/Mme est bien etablie"
//        );
//        message.create();
//    }
