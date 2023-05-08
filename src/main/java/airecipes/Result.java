/*
Used for Spoonacular API to retrieve API JSON within API call
*/
package airecipes;

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
    
    /**
     * Returns Result id
     * @return id - id of this Result
     */
    public int getResultId(){
        return id;
    }
    
    /**
     * Returns Result title
     * @return title - title of this Result
     */
    public String getResultTitle(){
        return title;
    }
}
