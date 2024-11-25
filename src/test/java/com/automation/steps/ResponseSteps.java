package com.automation.steps;

import com.automation.pojo.CreateUserResponsePojo;
import com.automation.pojo.CreateuserRequestPojo;
import com.automation.utils.ConfigReader;
import com.automation.utils.RestAssuredUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.junit.Assert;

public class ResponseSteps {

    @Then("verify status code is {int}")
    public void verify_status_code_is(int statusCode) {
        Assert.assertEquals(statusCode, RestAssuredUtils.getStatusCode());
    }

    @Then("verify response has body same as request")
    public void verify_response_has_body_same_as_request() {
        Response response = RestAssuredUtils.getResponse();
        CreateuserRequestPojo requestPojo = (CreateuserRequestPojo) ConfigReader.getObject("request_pojo");
        CreateUserResponsePojo response_pojo = response.as(CreateUserResponsePojo.class);
        Assert.assertEquals(requestPojo.getName(), response_pojo.getName());
        Assert.assertEquals(requestPojo.getEmail(), response_pojo.getEmail());
        Assert.assertEquals(requestPojo.getGender(), response_pojo.getGender());
        Assert.assertEquals(requestPojo.getStatus(), response_pojo.getStatus());
    }

    @Then("verify response has schema same as {string}")
    public void verify_response_has_schema_same_as(String fileName) {
        Response response = RestAssuredUtils.getResponse();
        response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("data/"+fileName));
    }

    @Then("store the id from response")
    public void store_the_id_from_response() {
        String userId = RestAssuredUtils.getResponseFieldValue("id");
        ConfigReader.setProperty("user.id", userId);
    }

    @And("store the {string} from response into {string}")
    public void storeTheFromResponseInto(String jsonPath, String key) {
        String value = RestAssuredUtils.getResponseFieldValue(jsonPath);
        ConfigReader.setProperty(key, value);
    }

    @And("store the {string} of first two users from response into {string} and {string}")
    public void storeTheOfFirstTwoUsersFromResponseIntoAnd(String jsonPath, String key1, String key2) {
        String value1 = RestAssuredUtils.getResponseFieldValue("[0]."+jsonPath);
        String value2 = RestAssuredUtils.getResponseFieldValue("[1]."+jsonPath);
        ConfigReader.setProperty(key1, value1);
        ConfigReader.setProperty(key2, value2);
    }
}
