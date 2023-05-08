/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package airecipes;

import java.util.ArrayList;

/**
 *
 * @author Edwar
 */
public class AIResponse extends Choice{
    private Choice[] choices;
    
    /**
     * Creates AIResponse instance
     * @param choices - the response that the OpenAI API returns
     */
    public AIResponse(Choice[] choices){
        this.choices = choices;
    }
    
    /**
     * returns choice text at i
     * @param i - the choice text to return
     * @return 
     */
    public String getChoiceText(int i){
        return this.choices[i].getText();
    }
}
