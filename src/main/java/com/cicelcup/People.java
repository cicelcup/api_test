package com.cicelcup;
import java.util.ArrayList;

import lombok.Data;

@Data
public class People{
    private String name;
    private String height;
    private String mass;
    private String hair_color;
    private String skin_color;
    private String eye_color;
    private String birth_year;
    private String gender;
    private String homeworld;
    private ArrayList<String> films;
    private ArrayList<String> species;
    private ArrayList<Object> vehicles;
    private ArrayList<Object> starships;
    private String url;
}
