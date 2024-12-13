package au.com.telstra.simcardactivator.controllers;


import au.com.telstra.simcardactivator.models.SimRequest;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class simControllers {

    @PostMapping
    public String getSim(@RequestBody SimRequest request) {

        try{
            String ACTUATOR_URL = "http://localhost:8444/actuate";
            URL ActuatorURL = new URL(ACTUATOR_URL);

            HttpURLConnection connection = (HttpURLConnection) ActuatorURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String input = "{\"iccid\":\""+request.getIccid()+"\"}";
            System.out.println(input);
            connection.getOutputStream().write(input.getBytes());

            int responseCode = connection.getResponseCode();

            if(responseCode != 200){
                return "Failed";
            }
            return "Success";
        }catch (Exception e){
            return "Failed";
        }
    }
}
