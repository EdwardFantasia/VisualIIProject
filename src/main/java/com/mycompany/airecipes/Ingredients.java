/*
Used for Spoonacular API to retrieve API JSON within API call
*/
package com.mycompany.airecipes;

/**
 *
 * @author Edwar
 */
public class Ingredients {
    private int id;
    private String image;
    private String name;
    
    /*
    Creates new Ingredients object from JSON
    @param int id: id of Ingredients object,
           String image: url of Ingredients object photo,
           String results: results of the Ingredients object,
    */
    public Ingredients(int id, String image, String name){
        this.id = id;
        this.image = image;
        this.name = name;
    }
    
}
