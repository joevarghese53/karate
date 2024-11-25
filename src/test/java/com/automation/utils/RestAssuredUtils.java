package com.automation.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RestAssuredUtils {
    static RequestSpecification requestSpecification = RestAssured.given();
    static String endPoint;
    static Response response;
    public static void setEndpoint(String endpoint){
        endPoint = endpoint;
    }

    public static void setHeader(String key, String value){
        requestSpecification.header(key, value);
    }

    public static void setRequestBody(String fileName) throws FileNotFoundException {
        String body = getJsonDataFromFile(fileName);
        requestSpecification.body(body);
    }

    public static void setRequestBody(Object body){
        requestSpecification.body(body);
    }

    public static String getJsonDataFromFile(String fileName) throws FileNotFoundException {
        String filePath = "src/test/resources/data/";
        Scanner sc = new Scanner(new FileInputStream(filePath + fileName));
        return sc.useDelimiter("\\Z").next();
    }

    public static void post(){
        requestSpecification.log().all();
        response = requestSpecification.post(endPoint);
        response.then().log().all();
    }
    public static void get(){
        requestSpecification.log().all();
        response = requestSpecification.get(endPoint);
        response.then().log().all();
    }
    public static void put(){
        requestSpecification.log().all();
        response = requestSpecification.put(endPoint);
        response.then().log().all();
    }
    public static void delete(){
        requestSpecification.log().all();
        response = requestSpecification.delete(endPoint);
        response.then().log().all();
    }

    public static Response getResponse(){
        return response;
    }

    public static int getStatusCode(){
        return response.statusCode();
    }

    public static String getResponseFieldValue(String jsonPath){
        return response.jsonPath().getString(jsonPath);
    }

    public static void clear(){
        requestSpecification = RestAssured.given();
    }
}
