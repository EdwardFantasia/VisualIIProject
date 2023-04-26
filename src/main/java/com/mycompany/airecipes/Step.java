/*
Used for Spoonacular API to retrieve API JSON within API cal
*/
package com.mycompany.airecipes;

import java.util.ArrayList;

/**
 *
 * @author Edwar
 */
public class Step {
    private ArrayList<Equipment> equipment;
    private ArrayList<Ingredients> ingredients;
    private int number;
    private String step;
    
    /*
    Creates new Step object from JSON
    @param ArrayList<Equipment> equipment: Equiment objects within Step object,
           ArrayList<Ingredients> ingredients: Ingredients objects within Steop object,
           int number: current Step number,
           String step: Step directions
    */
    public Step(ArrayList<Equipment> equipment, ArrayList<Ingredients> ingredients, int number, String step){
        this.equipment = equipment;
        this.ingredients = ingredients;
        this.number = number;
        this.step = step;
    }
    
    /*
    Retrieves Ingredients list from Step object
    */
    public ArrayList<Ingredients> getIngredients(){
        return this.ingredients;
    }
    
    /*
    Retrieves step Step object
    */
    public String getStep(){
        return this.step;
    }
    
    public int getNumber(){
        return this.number;
    }
    
    public ArrayList<Equipment> getEquipment(){
        return this.equipment;
    }
}
