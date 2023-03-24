/*
Used for Spoonacular API to retrieve API JSON within API call 
*/
package com.mycompany.airecipes;

import java.util.ArrayList;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import org.json.simple.JSONArray;

/**
 *
 * @author Edwar
 */
public class Food {
    private int offset;
    private int number;
    private ArrayList<Result> results;
    
    /*
    Creates new Food object from JSON
    @param int offset: offset of Food object,
           String number: number of this Food object,
           String results: number of results of Food objects,
    */
    public Food(int offset, int number, ArrayList<Result> results){
        this.offset = offset;
        this.number = number;
        this.results = results;
    }
    
    /*
    Retrieves the number field of this Food object
    */
    public int getNumber(){
        return this.number;
    }
    
    /*
    Retrieves the results field of this Food object
    */
    public ArrayList<Result> getResults(){
        return this.results;
    }
    
    /*
    Used to test Spoonacular API *WILL NOT BE IN FINAL PROJECT*
    */
    public static void main(String[] args) {
        
        Gson gson = new Gson();
        
        try{
            URL url = new URL("https://api.spoonacular.com/recipes/complexSearch?query=pasta&maxFat=25&number=2&type=maincourse&apiKey=ecf813497d49484187d848b720e3baff");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
               
            connection.connect();
            System.out.println("1");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("2");
            Food foodJson = gson.fromJson(reader, Food.class);
            System.out.println(foodJson.getNumber());
            int id = foodJson.getResults().get(0).getResultId();
            System.out.println(id);
            String stringId = Integer.toString(id);
            
            
            
        } catch(MalformedURLException ex){System.out.println("nah");}
        catch(IOException io){System.out.println(io);}
    }
}
