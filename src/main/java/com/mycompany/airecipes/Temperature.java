/*
Used for Spoonacular API to retrieve API JSON within API call
*/
package com.mycompany.airecipes;

/**
 *
 * @author Edwar
 */
public class Temperature {
    private int number;
    private String unit;
    
    /*
    Creates new Temperature object from JSON
    @param int number: degree of current Temperature objects,
           String unit: unit of measurement of current Temperature object
    */
    public Temperature(int number, String unit){
        this.number = number;
        this.unit = unit;
    }
    
    /*
    Retrieves degree of Temperature object
    */
    public int getDegree(){
        return this.number;
    }
    
}
