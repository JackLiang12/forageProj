package au.com.telstra.simcardactivator.controllers;

import au.com.telstra.simcardactivator.models.SimRequest;
import au.com.telstra.simcardactivator.repository.SimRequestRepository;
import org.springframework.web.bind.annotation.*;

import java.net.HttpURLConnection;
import java.net.URL;

@RestController
public class simControllers {

    private final SimRequestRepository repository;

    public simControllers(SimRequestRepository repository) {
        this.repository = repository;
    }

    @PostMapping
    public String getSim(@RequestBody SimRequest request) {
        try {
            String ACTUATOR_URL = "http://localhost:8444/actuate";
            URL ActuatorURL = new URL(ACTUATOR_URL);

            HttpURLConnection connection = (HttpURLConnection) ActuatorURL.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            String input = "{\"iccid\":\"" + request.getIccid() + "\"}";
            System.out.println(input);
            connection.getOutputStream().write(input.getBytes());

            int responseCode = connection.getResponseCode();

            if (responseCode != 200) {
                request.setActivated(false);
                repository.save(request);
                return "Failed";
            }

            request.setActivated(true);
            repository.save(request);

            System.out.println(request.toString());
            return "Success";

        } catch (Exception e) {
            return "Failed";
        }
    }
    @GetMapping
    public SimRequest getSimRequest(@RequestParam("simCardId") Long simCardId) {
        return repository.findByID(simCardId);
    }
}
