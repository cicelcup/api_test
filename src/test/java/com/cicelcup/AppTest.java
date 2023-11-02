package com.cicelcup;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;

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
        String endPoint = "people/2";
        response = given().when().get(API_URL + endPoint);
        Assert.assertEquals(response.getStatusCode(), OK);

        Gson gson = new Gson();
        People people = gson.fromJson(response.body().asString(), People.class);
        System.out.println(people.toString());
    }
}
