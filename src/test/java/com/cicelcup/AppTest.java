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
        people = null;
    }

    private Response people2Response(){
        return given().when().get(API_URL + peopleEndPoint);
    }

    private People getPeople2(){
        return gson.fromJson(response.body().asString(), People.class);
    }

    @Test
    public void checkPeople2IsOK(){
        response = people2Response();
        Assert.assertEquals(response.getStatusCode(), OK);
    }

    @Test
    public void checkPeople2Skin(){
        response = people2Response();
        people = getPeople2();
        Assert.assertEquals(people.getSkin_color(), "gold");
    }

    @Test
    public void checkPeopleFilms(){
        response = people2Response();
        people = getPeople2();
        Assert.assertEquals(people.getFilms().size(), 6);
    }

    @Test
    public void checkFilm2(){
        response = people2Response();
        people = getPeople2();
        response = given().when().get(people.getFilms().get(1));
        Film film = gson.fromJson(response.body().asString(), Film.class);
        Assert.assertFalse(film.getCharacters().isEmpty());
        Assert.assertFalse(film.getPlanets().isEmpty());
        Assert.assertFalse(film.getStarships().isEmpty());
        Assert.assertFalse(film.getVehicles().isEmpty());
        Assert.assertFalse(film.getSpecies().isEmpty());
    }
}
