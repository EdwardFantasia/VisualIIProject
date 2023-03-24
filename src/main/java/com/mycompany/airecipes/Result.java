/*
Used for Spoonacular API to retrieve API JSON within API call
*/
package com.mycompany.airecipes;

/**
 *
 * @author Edwar
 */
public class Result {
    private int id;
    private String title;
   
    /*
    Creates new Result object from JSON
    @param int id: id of Result object,
           String title: title of Result object,
    */
    public Result(int id, String title){
        this.id = id;
        this.title = title;
    }
    
    /*
    Retrieves id field of Result object
    */
    public int getResultId(){
        return id;
    }
    
    /*
    Retrieves title field of Result object
    */
    public String getResultTitle(){
        return title;
    }
}
