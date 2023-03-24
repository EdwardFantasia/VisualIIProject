/*
Used for Spoonacular API to retrieve API JSON within API call
*/
package com.mycompany.airecipes;

import java.util.ArrayList;

/**
 *
 * @author Edwar
 */
public class Recipe {
    private String name;
    private ArrayList<Step> steps;
    /*
    Creates new Recipe object from JSON
    @param tring name: name of Recipe object,
           ArrayList<Step>: steps to create Recipe,
    */
    public Recipe(String name, ArrayList<Step> steps){
        this.name = name;
        this.steps = steps;
    }
       
    /*
    Retrieves the steps field of Recipe object
    */
    public ArrayList<Step> getSteps(){
        return this.steps;
    }
    
    /*
    Retrieves the name field of Recipe object
    */
    public String getName(){
        return this.name;
    }
}
