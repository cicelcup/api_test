package com.cicelcup;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AppTest 
{
    static int OK = 200;
    static String API_URL = "https://swapi.dev/api/";
    
    Response response;

    @AfterTest
    public void tearDown()
    {
        response = null;
    }

    @Test
    public void checkPeople2Test(){
        response = given().when().get(API_URL + "people/2");
        Assert.assertEquals(response.getStatusCode(), OK);
    }
}
