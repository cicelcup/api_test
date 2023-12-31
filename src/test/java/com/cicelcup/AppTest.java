package com.cicelcup;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.google.gson.Gson;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AppTest 
{
    static int OK = 200;
    static int NOT_FOUND = 404;
    static String API_URL = "https://swapi.dev/api/";
    static String peopleEndPoint = "people/2";
    static String filmEndPoint = "films/7/";
    
    Response response;
    People people;
    Film film;
    Planet planet;
    Gson gson = new Gson();

    @AfterTest
    public void tearDownResponse()
    {
        response = null;
    }
    @AfterClass
    public void tearDown(){
        people = null;
        film = null;
        planet = null;
    }


    @Test(priority =  0)
    public void checkPeople2IsOK(){
        response = given().when().get(API_URL + peopleEndPoint);
        Assert.assertEquals(response.getStatusCode(), OK);
        people = gson.fromJson(response.body().asString(), People.class);
    }

    @Test(priority = 1)
    public void checkPeople2Skin(){
        Assert.assertEquals(people.getSkin_color(), "gold");
    }

    @Test(priority =  2)
    public void checkPeople2Films(){
        Assert.assertEquals(people.getFilms().size(), 6);
    }

    @Test(priority = 3)
    public void checkFilm2IsOk(){
        response = given().when().get(people.getFilms().get(1));
        Assert.assertEquals(response.getStatusCode(), OK);
        film = gson.fromJson(response.body().asString(), Film.class);
    }

    @Test(priority =  4)
    public void checkFilm2Info(){
        Assert.assertFalse(film.getCharacters().isEmpty());
        Assert.assertFalse(film.getPlanets().isEmpty());
        Assert.assertFalse(film.getStarships().isEmpty());
        Assert.assertFalse(film.getVehicles().isEmpty());
        Assert.assertFalse(film.getSpecies().isEmpty());
    }

    @Test(priority = 5)
    public void checkPlanet1IsOk(){
        response = given().when().get(film.getPlanets().get(0));
        Assert.assertEquals(response.getStatusCode(), OK);
        planet = gson.fromJson(response.body().asString(), Planet.class);
    }

    @Test(priority = 6)
    public void checkFirstPlanetInfo(){
        Assert.assertEquals(planet.getGravity(), "1.1 standard");
    }

    @Test(priority =  7)
    public void checkFirstPlanetUrl(){
        response = given().when().get(planet.getUrl());
        Planet planetToCheck = gson.fromJson(response.body().asString(), Planet.class);
        Assert.assertEquals(planetToCheck, planet);
    }

    @Test(priority = 8)
    public void checkNotFound(){
        response = given().when().get(API_URL + filmEndPoint);
        Assert.assertEquals(response.getStatusCode(), NOT_FOUND);
    }
}
