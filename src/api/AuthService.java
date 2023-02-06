package api;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.User;

public class AuthService {
    // TODO: bekot a laravel backendbe (inso formaju keresek)

    String host = "http://[::11]:8000/api/";

    public AuthService () {}

    public void register(String name, String email, String pass) throws MalformedURLException {
        URL url = new URL(this.host + "register"); // lara?

        User user = new User(name, email, pass, pass);

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        Gson gson = builder.create();
        String json = gson.toJson(user);


        HashMap<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");

        HttpClient myClient = new HttpClient();
        // String result = myClient.post(url, json, headers);

        System.out.println("JSON: \n" + json);
        // System.out.println(myClient.getResCode());


        // assembe json headers 
    }


    


}
