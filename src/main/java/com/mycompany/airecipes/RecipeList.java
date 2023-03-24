/*
Used for Spoonacular API call
*/
package com.mycompany.airecipes;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Edwar
 */
public class RecipeList {
    private ArrayList<Recipe> recipes;
    
    /*
    Creates new RecipeList object from JSON (used as final step of API retrieval)
    @param tring name: name of Recipe object,
           ArrayList<Recipe> recipes: list of Recipe objects retrieved from API,
    */
    public RecipeList(ArrayList<Recipe> recipes){
        this.recipes = recipes;
    }
    /*
    Retrieves recipes ArrayList
    */
    public ArrayList<Recipe> getRecipes(){
        return this.recipes;
    }
    
    /*
    Used to test Spoonacular API *WILL NOT BE IN FINAL PROJECT*
    */
    public static void main(String[] args) {
        
        Gson gson = new Gson();
        
        try{
            URL url2 = new URL("https://api.spoonacular.com/recipes/324694/analyzedInstructions?&apiKey=ecf813497d49484187d848b720e3baff");
            HttpsURLConnection connection2 = (HttpsURLConnection)url2.openConnection();
            connection2.setRequestMethod("GET");
            connection2.setRequestProperty("Content-Type", "application/json");
            connection2.connect();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection2.getInputStream()));
            Recipe[] recipes = gson.fromJson(reader2, Recipe[].class);
            System.out.print(recipes[0].getSteps().get(0).getStep());
            
            
        } catch(MalformedURLException ex){System.out.println("nah");}
        catch(IOException io){System.out.println(io);}
    }
}
