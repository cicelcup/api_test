package com.cicelcup;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import com.Film;
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
    public void checkPeopleFilms(){
        Assert.assertEquals(people.getFilms().size(), 6);
    }

    @Test(priority =  3)
    public void checkFilm2(){
        response = given().when().get(people.getFilms().get(1));
        Film film = gson.fromJson(response.body().asString(), Film.class);
        Assert.assertFalse(film.getCharacters().isEmpty());
        Assert.assertFalse(film.getPlanets().isEmpty());
        Assert.assertFalse(film.getStarships().isEmpty());
        Assert.assertFalse(film.getVehicles().isEmpty());
        Assert.assertFalse(film.getSpecies().isEmpty());
    }
}
