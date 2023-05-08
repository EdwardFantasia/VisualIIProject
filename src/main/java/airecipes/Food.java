/*
Used for Spoonacular API to retrieve API JSON within API call 
*/
package airecipes;

import java.util.ArrayList;

/**
 *
 * @author Edwar
 */
public class Food {
    private ArrayList<Result> results;
    
    /*
    Creates new Food object from JSON
    @param int offset: offset of Food object,
           String number: number of this Food object,
           String results: number of results of Food objects,
    */
    public Food(ArrayList<Result> results){
        this.results = results;
    }
    
    /*
    Retrieves the results field of this Food object
    */
    public ArrayList<Result> getAPIResults(){
        return this.results;
    }
}
