package com.automation.steps;

import com.automation.pojo.CreateuserRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.FileNotFoundException;

public class RequestSteps {
    @Given("user calls {string} endpoint")
    public void user_calls_endpoint(String endPoint) {
        RestAssuredUtils.clear();
        RestAssuredUtils.setEndpoint(endPoint);
    }

    @Given("user calls {string} endpoint with {string}")
    public void userCallsEndpointWith(String endPoint, String value) {
        RestAssuredUtils.clear();
        if(endPoint.contains("@id")){
            if (ConfigReader.getProperty(value)!=null){
                endPoint = endPoint.replace("@id", ConfigReader.getProperty(value));
            }
            else {
                endPoint = endPoint.replace("@id", value);
            }
        }
        RestAssuredUtils.setEndpoint(endPoint);
    }

    @Given("set header {string} to {string}")
    public void set_header_to(String key, String value) {
        if (value.contains("@token")) {
            value = value.replace("@token", ConfigReader.getProperty("auth.token"));
        }
        RestAssuredUtils.setHeader(key, value);
    }

    @Given("set request body from file {string} using pojo")
    public void set_request_body_from_file_using_pojo(String fileName) throws Exception {
        String content = RestAssuredUtils.getJsonDataFromFile(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        CreateuserRequestPojo request_pojo = objectMapper.readValue(content, CreateuserRequestPojo.class);
        RestAssuredUtils.setRequestBody(request_pojo);
        ConfigReader.setObject("request_pojo", request_pojo);
    }

    @When("user makes post request")
    public void user_makes_post_request() {
        RestAssuredUtils.post();
    }

    @When("user makes get request")
    public void userMakesGetRequest() {
        RestAssuredUtils.get();
    }

    @When("user makes put request")
    public void userMakesPutRequest() {
        RestAssuredUtils.put();
    }

    @When("user makes delete request")
    public void userMakesDeleteRequest() {
        RestAssuredUtils.delete();
    }

}
