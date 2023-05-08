/*
Used for Spoonacular API to retrieve API JSON within API cal
*/
package airecipes;

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
    public Equipment(int id, String image, String name, Temperature temp){
        this.id = id;
        this.image = image;
        this.name = name;
        this.temp = temp;
    }
    
    /**
     * Returns Equipment temp
     * @return temp - temperature of this Equipment
     */
    public Temperature getEquipTemp(){
        return this.temp;
    }
    
   /**
     * Returns Equipment name
     * @return name - name of this Equipment
     */
    public String getEquipName(){
        return this.name;
    }
    
    /**
     * Returns Equipment image
     * @return image - image of this Equipment
     */
    public String getEquipImage(){
        return this.image;
    }
    
    /**
     * Returns Equipment id
     * @return id - id of this Equipment
     */
    public int getEquipId(){
        return this.id;
    }
    
}
