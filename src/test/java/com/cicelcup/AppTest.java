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
    Film film;
    Gson gson = new Gson();

    @AfterTest
    public void tearDown()
    {
        response = null;
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

    @Test(priority =  3)
    public void checkFilm2(){
        response = given().when().get(people.getFilms().get(1));
        film = gson.fromJson(response.body().asString(), Film.class);
        Assert.assertFalse(film.getCharacters().isEmpty());
        Assert.assertFalse(film.getPlanets().isEmpty());
        Assert.assertFalse(film.getStarships().isEmpty());
        Assert.assertFalse(film.getVehicles().isEmpty());
        Assert.assertFalse(film.getSpecies().isEmpty());
    }

    @Test(priority = 4)
    public void checkFirstPlanet(){
        response = given().when().get(film.getPlanets().get(0));
        Planet planet = gson.fromJson(response.body().asString(), Planet.class);
        Assert.assertEquals(planet.getGravity(), "1.1 standard");
    }
}
