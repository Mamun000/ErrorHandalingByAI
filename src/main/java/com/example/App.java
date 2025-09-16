package com.example;

import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

public class App {
  public static void main(String[] args) {
    
    try {
      int a = 10;
      int b = 0;
      int c = a / b; 
      System.out.println("Result: " + c);

    } catch (Exception e) {
      System.out.println("⚠️ Error occurred: " + e.getMessage());
      fixError(e.getMessage());
      
    }
  }

  public static void fixError(String errorMessage) {
    String apiKey = System.getenv("GEMINI_API_KEY");
    if (apiKey == null || apiKey.isEmpty()) {
      throw new RuntimeException("❌ API key not set. Please set GEMINI_API_KEY in environment variables.");
    }
    Client client = Client.builder().apiKey(apiKey).build();

    // Code to fix the error goes here
    try {
        // Send the error message to Gemini for explanation/fix
        String prompt = "I got this Java error: \"" + errorMessage + "\". Explain why this happens and how to fix it.";
        
        GenerateContentResponse response =
            client.models.generateContent("gemini-2.5-flash", prompt, null);

        System.out.println("\n🤖 Gemini’s Suggestion:");
        System.out.println(response.text());

      } catch (Exception geminiError) {
        System.out.println("❌ Failed to query Gemini API: " + geminiError.getMessage());
      }
  }
}
