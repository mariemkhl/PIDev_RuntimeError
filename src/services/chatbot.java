/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package services;
import Jwiki.Jwiki;
import Pidev1.Controller;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import utils.DataConnection;
import java.sql.*;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.web.WebEngine;
import javax.imageio.ImageIO;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

/**
 *
 * @author iheb debbech
 */
public class chatbot {
    Connection conn = DataConnection.getInstance().getCnx();
    private  String tf="";
    private  String ta="";
    public Statement st;
   public ResultSet rs;
   private static final String API_KEY = "AIzaSyBKTfNGZWOEUq81tcgvymiIS8AIJizMx44";
    Jwiki jw;
    
    private String videoId;
    private static final String SEARCH_URL = "https://www.googleapis.com/youtube/v3/search";
    private static final String VIDEO_URL = "https://www.googleapis.com/youtube/v3/videos";
   // private static final String SEARCH_URL = "https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults=1&type=video&q=%s&key=%s";
private static final String SEARCH_QUERY = "java tutorial";
List<String> chatbot2 = new ArrayList();
          List<String> cine = new ArrayList();
           List<String> theatre = new ArrayList();
            List<String> museum = new ArrayList();
             List<String> monument = new ArrayList();
              List<String> categories = new ArrayList();
	String[][] chatBot={
		//standard greetings
		{"hi","hello","hola","ola","howdy","hey"},
		{"hi","hello","hey"},
		//question greetings
		{"how are you","how r you","how r u","how are u"},
		{"good","doing well","idk","meh"},
		//yes
		{"yes"},
		{"no","NO","NO!!!!!!!"},
		//default
		{"shut up","you're bad","stop talking",
		" lol what u talking about","can't understand you"}
	};
	
	
        String[] verbs={"is","was","think","mean","are","see","'re"};
		
	
    public chatbot() {
       
        categories.add("cinema");
            categories.add("museum");
            categories.add("monument");
            categories.add("theatre");
            
            
        //selcetion du nomplace DB
           
                
                
                
		
             
    }
    public String firsttext()
    {
         
         
           addText("-->artisty: hello sir pls speak english   :)  \n im here to help you in finding a place where you can see art    :)\n "
                        + " artistic places are seperated in 4 categories here : \n cinema,   museum, monument ,  theatre"
                        + "\n please select the type you are looking for and we will give you the best place for it  :) \n  or you can give us a place and we will tell you more about it   :) \n we can play you a song of your choice or tell you about a specific movie :) ");
	return this.ta;
    }
    public String chattest(String tf, String ta) throws IOException{
     
	 this.tf = tf;
        this.ta = ta;
	try{
		String query="SELECT `nomplace` FROM `map_art`";
                String cinema="SELECT `nomplace` FROM `map_art` WHERE `categorie` = 'cinema' ;";
                String museum1="SELECT `nomplace` FROM `map_art` WHERE `categorie` = 'museum' ;";
                String monument1="SELECT `nomplace` FROM `map_art` WHERE `categorie` = 'monument' ;";
                String theatre1="SELECT `nomplace` FROM `map_art` WHERE `categorie` = 'theatre' ;";
                st=conn.createStatement();
                rs=st.executeQuery(query);
                while(rs.next())
                {
                    chatbot2.add(rs.getString(1));
                }
                rs=st.executeQuery(cinema);
                while(rs.next())
                {
                   cine.add(rs.getString(1));
                }
                rs=st.executeQuery(museum1);
                while(rs.next())
                {
                    museum.add(rs.getString(1));
                }
                rs=st.executeQuery(monument1);
                while(rs.next())
                {
                    monument.add(rs.getString(1));
                }
                rs=st.executeQuery(theatre1);
                while(rs.next())
                {
                    theatre.add(rs.getString(1));
                }
                System.out.println(chatbot2);
            }catch(SQLException e)
                 { System.out.println(e.getMessage());}
	
           // super("Chat Bot");
            
	
	       System.out.println(tf);
		
			
			//-----grab quote-----------
			String quote=tf;
			
                        quote=quote.trim();
			addText("\n -->You:\t"+quote);
			     //System.out.println(ta);
                        
			while(
				quote.charAt(quote.length()-1)=='!' ||
				quote.charAt(quote.length()-1)=='.' ||
				quote.charAt(quote.length()-1)=='?'
			){
				quote=quote.substring(0,quote.length()-1);
			}
			quote=quote.trim();
			byte response=0;
			/*
			0:we're searching through chatBot[][] for matches
			1:we didn't find anything
			2:we did find something
			*/
			//-----check for matches----
			int j=0;//which group we're checking
			while(response==0){
                            
				if(inArray(quote.toLowerCase(),chatBot[j*2])){
					response=2;
					int r=(int)Math.floor(Math.random()*chatBot[(j*2)+1].length);
					addText("\n-->artisty\t"+chatBot[(j*2)+1][r]);
				}
                                j++;
           //detection du nom en general                    
                                String quoteWords[]=quote.split("[ ']");
                               if(quoteWords[0].equals("play") && quoteWords.length >1 ){
                                   Controller music=new Controller();
                                   String m=quote.substring(5);
                          String query = m.replaceAll(" ", "+");
                JSONObject searchResults = music.searchYouTube(query);
                videoId = searchResults.getJSONArray("items")
                        .getJSONObject(0)
                        .getJSONObject("id")
                        .getString("videoId");
                JSONObject videoDetails = getVideoDetails(videoId);
                String videoTitle = videoDetails.getJSONArray("items")
                        .getJSONObject(0)
                        .getJSONObject("snippet")
                        .getString("title");
                String thumbnailUrl = videoDetails.getJSONArray("items")
                        .getJSONObject(0)
                        .getJSONObject("snippet")
                        .getJSONObject("thumbnails")
                        .getJSONObject("medium")
                        .getString("url");
                                return "play"+videoId;   
                                   
                               }
                               else if(quoteWords[0].equals("stop") && quoteWords.length ==1 )
                               {
                                   return"stopsong";
                               }
                               if(quote.startsWith("take me to")){
                                   try{
                                       String sr="";
		                String query="SELECT `Latitude`,`Longitude` FROM `map_art` WHERE `nomplace`=?";
                                PreparedStatement ps = conn.prepareStatement(query);
                                  ps.setString(1,quote.substring(11));
                                  
                                rs=ps.executeQuery();
                                while(rs.next()) {
                                        sr="map"+rs.getString(1)+","+rs.getString(2);
                                        return sr;
                                        }
                                
                                 }catch(SQLException ex)
                                 { System.out.println(ex.getMessage());}
                                
                                
                                   
                               }
                                for(int i=0;i<quoteWords.length;i++)
                              {
                                if(chatbot2.contains(quoteWords[i]) &&quoteWords.length!=1)
                                {response= 2;
                               try{
                                   String nommodif=quoteWords[i].replaceAll("_"," ");
                                   jw=new Jwiki(nommodif);
                                   String jwdesc=jw.getExtractText().substring(0, 40)+"\n";
                                  addText("\n-->artisty\t : " +quoteWords[i]+":"+jwdesc+jw.getExtractText().substring(40));
                               
                               }catch(Exception  f){
                                //int r=(int)Math.floor(Math.random()*chatbot2.size());
                                try{
		                String query="SELECT `description` FROM `map_art` WHERE `nomplace`=?";
                                PreparedStatement ps = conn.prepareStatement(query);
                                  ps.setString(1,quoteWords[i]);
                                  
                                rs=ps.executeQuery();
                                while(rs.next()) {
                               addText("\n-->artisty\t"+quoteWords[i] + ": " + rs.getString(1));
}
                                 }catch(SQLException ex)
                                 { System.out.println(ex.getMessage());}
                                break;
                                }}
                                else if(categories.contains(quoteWords[i]) && quoteWords.length!=1)
                                {
                                    response= 2;
                                   String desc=descriptdetect(quoteWords[i]);
                                   System.out.println(desc.length());
                                   if(5 < desc.length())
                                   {System.out.println(desc.length());
                                       String p1=desc.substring(0,5);
                                   String p2=desc.substring(5);
                                   addText("\n-->artisty:\t for "+quoteWords[i] + " we propose " +p1 +" \n "+p2 +"it's a good place recommended by all users");
                                   }else{
                                   addText("\n-->artisty:\t for "+quoteWords[i] + " we propose " +desc +"it's a good place recommended by all users");
                                         }
                                   break;
                                }
            
				if(j*2==chatBot.length-1 && response==0){
					response=1;
				}
			}
                                
			//-----try counter----------
			if(response==1){
				String quoteWords1[]=quote.split("[ ']");
				int c=counter(quoteWords1);
				if(c!=-1){
					String ext=quote.split(verbs[c])[1];
					addText("\n-->artisty:\tYou"+verbs[c]+ext);
					response=2;
				}
			}
			//-----default--------------
			if(response==1 && quoteWords.length !=1 ){
				int r=(int)Math.floor(Math.random()*chatBot[chatBot.length-1].length);
				addText("\n-->artisty\t"+chatBot[chatBot.length-1][r]);
			}
                        else if (response==1 && quoteWords.length ==1)
                        {
                            addText("\n-->artisty:\t PLEAASSE FINISH YOUR SENTENCE ");
                        }
			
		}
    return this.ta;
    }
	
	
	
	
	
	
	public  void addText(String str){
		ta=ta+str;
                System.out.println(ta);
	}
	
	public boolean inArray(String in,String[] str){
		boolean match=false;
		for(int i=0;i<str.length;i++){
			if(str[i].equals(in)){
				match=true;
			}
		}
		return match;
	}
	
	public int counter(String str[]){
		int verbID=-1;
		for(int i=0;i<str.length;i++){
			for(int j=0;j<verbs.length;j++){
				if(str[i].equals(verbs[j])){
					verbID=j;
				}
			}
		}
		return verbID;
	}
        ///recuperer un emplacemnt du categorie
        public String descriptdetect(String s)
        {SecureRandom rand = new SecureRandom();
            int r=0;
            try{
		                String query="SELECT `nomplace`,`description` FROM `map_art` WHERE `categorie`=?";
                                PreparedStatement ps = conn.prepareStatement(query);
                                  ps.setString(1,s);
                                  int size=0;
                                rs=ps.executeQuery();
                                while(rs.next()) {
                                    size++;                      
                                }
                                System.out.println(size);
                                if(size>1){                                
                                 r=rand.nextInt(size)+1;
                                 }
                                else {r=1;}
                                rs.absolute(r);
                                s=rs.getString(1)+":"+rs.getString(2);
                                 }catch(SQLException ex)
                                 { System.out.println(ex.getMessage());}
            
            return s;
        }
           

    private JSONObject searchYouTube(String query) throws IOException {
        String searchUrl = SEARCH_URL + "?key=" + API_KEY + "&part=snippet&maxResults=1&q=" + query;
        String searchResponse = IOUtils.toString(new URL(searchUrl), "UTF-8");
        JSONObject searchResults = new JSONObject(searchResponse);
        return searchResults;
    }

    private JSONObject getVideoDetails(String videoId) throws IOException {
        String videoUrl = VIDEO_URL + "?key=" + API_KEY + "&part=snippet&id=" + videoId;
        String videoResponse = IOUtils.toString(new URL(videoUrl), "UTF-8");
        JSONObject videoDetails = new JSONObject(videoResponse);
        return videoDetails;
    } 
}
