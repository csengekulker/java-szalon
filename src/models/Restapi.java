package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Restapi {

    String host;

    public Restapi() {
        this.host = "http://[::1]:3000/";
    }

    public ArrayList<Vehicle> getVehicles() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        String text = getVehiclesAsString();
        System.out.println("ITT" +text);
        Vehicle[] vehicleArray = gson.fromJson(text, Vehicle[].class);

        ArrayList<Vehicle> vehicles = new ArrayList<>(
            Arrays.asList(vehicleArray)
        );

        return vehicles;
    }

    public String getVehiclesAsString() {
        String response = null;
    
        try {
          response = tryGetVehiclesAsString();
        } catch (MalformedURLException e) {
          System.err.println("MalformedURLException: " + e.getMessage());
        } catch (IOException e) {
          System.err.println("IOException: " + e.getMessage());
        }
    
        return response;
      }
    
      public String tryGetVehiclesAsString()
          throws MalformedURLException, IOException {
    
        URL url = new URL(this.host + "vehicles");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
    
        int resCode = http.getResponseCode();
    
        String text;
    
        if (resCode == 200) {
          System.out.println("OK");
          InputStream inputStream = http.getInputStream();
          text = this.convertStreamToString(inputStream);
        } else {
            text = "";
        }
    
        System.out.println(text);
    
        return text;
      }
    
      private String convertStreamToString(InputStream stream) {
    
        StringBuilder builder = new StringBuilder();
        Reader reader = new InputStreamReader(stream);
        Scanner sc = new Scanner(reader);
    
        while (sc.hasNextLine()) {
          System.out.println(sc.nextLine());
          builder.append(sc.nextLine());
        }
    
        sc.close();
    
        return builder.toString();
      }
    
}
