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
    static String peopleEndPoint = "people/2";
    
    Response response;
    People people;
    Gson gson = new Gson();

    @AfterTest
    public void tearDown()
    {
        response = null;
        people = null;
    }

    private Response people2Response(){
        return given().when().get(API_URL + peopleEndPoint);
    }

    @Test
    public void checkPeople2IsOK(){
        response = people2Response();
        Assert.assertEquals(response.getStatusCode(), OK);
    }

    @Test
    public void checkPeople2Skin(){
        response = people2Response();
        people = gson.fromJson(response.body().asString(), People.class);
        Assert.assertEquals(people.getSkin_color(), "gold");
    }

    @Test
    public void checkPeopleFilms(){
        response = people2Response();
        people = gson.fromJson(response.body().asString(), People.class);
        Assert.assertEquals(people.getFilms().size(), 6);
    }
}
