package stepDefinitions;

import au.com.telstra.simcardactivator.SimCardActivator;
import au.com.telstra.simcardactivator.models.SimRequest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ContextConfiguration(classes = SimCardActivator.class, loader = SpringBootContextLoader.class)
public class SimCardActivatorStepDefinitions {

    @Autowired
    private RestTemplate restTemplate;

    private String iccid;
    private ResponseEntity<String> response;

    @Given("the SIM card ICCID is {string}")
    public void theSimCardICCIDIs(String iccid) {
        this.iccid = iccid;
    }

    @When("I submit the activation request")
    public void iSubmitTheActivationRequest() {
        String url = "http://localhost:8080/";
        String requestPayload = String.format("{\"iccid\":\"%s\",\"customerEmail\":\"test@example.com\"}", iccid);
        response = restTemplate.postForEntity(url, requestPayload, String.class);
    }

    @Then("the activation response should be {string}")
    public void theActivationResponseShouldBe(String expectedResponse) {
        assertEquals(expectedResponse, response.getBody());
    }

    @And("the database record should show the SIM card is {string}")
    public void theDatabaseRecordShouldShowTheSIMCardIs(String activeStatus) {
        boolean isActive = activeStatus.equalsIgnoreCase("active");
        String queryUrl = "http://localhost:8080/?simCardId=1";
        ResponseEntity<SimRequest> dbResponse = restTemplate.getForEntity(queryUrl, SimRequest.class);

        assertEquals(iccid, dbResponse.getBody().getIccid());
        assertEquals(isActive, dbResponse.getBody().getActive());
    }
}