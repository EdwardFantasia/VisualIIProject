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
    public int getAPINumber(){
        return this.number;
    }
    
    /*
    Retrieves the results field of this Food object
    */
    public ArrayList<Result> getAPIResults(){
        return this.results;
    }
    
    public int getAPIOffset(){
        return this.offset;
    }
}
