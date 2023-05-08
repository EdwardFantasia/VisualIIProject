/*
Used for Spoonacular API to retrieve API JSON within API call
*/
package airecipes;

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
       
    /**
     * Returns Recipe's steps
     * @return steps - ArrayList of Step objects from this Recipe
     */
    public ArrayList<Step> getSteps(){
        return this.steps;
    }
    
    /**
     * Returns Recipe name
     * @return name - name of this Recipe
     */
    public String getName(){
        return this.name;
    }
}
