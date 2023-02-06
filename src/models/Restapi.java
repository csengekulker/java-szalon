package models;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Restapi {

    String host;
    int resCode;

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
        } catch (UnsupportedEncodingException e) {
          System.err.println("UnsupportedEncodingException: " + e.getMessage());
        } catch (IOException e) {
          System.err.println("IOException: " + e.getMessage());
        } 
    
        return response;
      }

      public String post(String urlStr, String data, HashMap<String, String> headers) throws MalformedURLException, IOException {
        URL url = new URL(urlStr);
        HttpURLConnection http = (HttpURLConnection) url.openConnection();

        http.setRequestMethod("POST");

        for(Map.Entry<String, String> entry : headers.entrySet()) {
          http.setRequestProperty(entry.getKey(), entry.getValue());
        }

        http.setDoOutput(true);
        byte[] dataArraY = data.getBytes();

        OutputStream out = http.getOutputStream();
        out.write(dataArraY);
        out.close();
        return "";
      }

      public String tryPost(String url, String data, HashMap<String, String> headers) {

        try {
          post(url, data, headers);
        } catch (Exception e) {
          // TODO: handle exception
        }


        return "";
      }
    
      public String tryGetVehiclesAsString()
          throws 
            MalformedURLException, 
            UnsupportedEncodingException, 
            IOException {    
        URL url = new URL(this.host + "vehicles");
        HttpURLConnection http = (HttpURLConnection) url.openConnection();
        http.setRequestMethod("GET");
        http.connect();
    
        this.resCode = http.getResponseCode();
    
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
    
      private String convertStreamToString(InputStream stream) throws UnsupportedEncodingException {
    
        StringBuilder builder = new StringBuilder();
        Reader reader = new InputStreamReader(stream, "utf-8");
        Scanner sc = new Scanner(reader);
    
        while (sc.hasNext()) {
          System.out.println(sc.nextLine());
          builder.append(sc.nextLine());
        }
    
        sc.close();
    
        return builder.toString();
      }

      public String getHost() {
        return host;
      }

      public int getResCode() {
        return resCode;
      }

      
    
}
