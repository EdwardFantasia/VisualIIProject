/*
Used for Spoonacular API to retrieve API JSON within API cal
*/
package com.mycompany.airecipes;

/**
 *
 * @author Edwar
 */
public class Equipment {
    private int id;
    private String image;
    private String name;
    private Temperature temp;
    
    /*
    Creates new Equipment object from JSON
    @param int id: id of Equipment object,
           String image: image url of Equipment object,
           String name: name of Equipment object,
           Temperature temp: Temperature of what temp this should be used at
    */
    public Equipment(int id, String image, String name, Temperature         temp){
        this.id = id;
        this.image = image;
        this.name = name;
        this.temp = temp;
    }
    
    /*
    Retrieves the temperature of this Equipment object
    */
    public Temperature getEquipTemp(){
        return this.temp;
    }
    
}
