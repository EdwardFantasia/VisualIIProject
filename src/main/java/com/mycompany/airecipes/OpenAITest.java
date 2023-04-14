/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.airecipes;

import java.io.IOException;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 *
 * @author Edwar
 * TEST ONLY
 */
public class OpenAITest {
    public static void main(String[] args) throws IOException {

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        String jsonInputString = "{\"model\": \"text-davinci-003\", \"prompt\": \"Say this is a test\", \"max_tokens\": 7, \"temperature\": 0}";

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, jsonInputString);

        Request request = new Request.Builder()
          .url("https://api.openai.com/v1/completions")
          .addHeader("Authorization", "Bearer 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")
          .post(body)
          .build();

        Call call = client.newCall(request);
        Response response = call.execute();
        System.out.println(response.body().string());

        }
    }

