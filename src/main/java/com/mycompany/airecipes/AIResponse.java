/*
Used for testing OpenAI API call *WILL NOT BE IN FINAL PROJECT*
*/
package com.mycompany.airecipes;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author Edwar
 */
public class AIResponse {
    
    /**
     * Runs the OpenAI API
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gson gson = new Gson();
        
        try{
            URL url = new URL("https://api.openai.com/v1/completions");
            HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Authorization", "Bearer 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");
            OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream(), "UTF-8");
            writer.write("{\"model\": \"text-davinci-003\", \"prompt\": \"Say this is a test\", \"max-tokens\": " + 7 + ", \"temperature\": " + 0 + "}");
            writer.flush();
            
            connection.connect();
            BufferedReader reader2 = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            
            
            
        } catch(MalformedURLException ex){System.out.println("nah");}
        catch(IOException io){System.out.println(io);}
    }
}
