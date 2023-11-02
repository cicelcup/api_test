package com.cicelcup;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Planet{
    private String name;
    private String rotation_period;
    private String orbital_period;
    private String diameter;
    private String climate;
    private String gravity;
    private String terrain;
    private String surface_water;
    private String population;
    private ArrayList<Object> residents;
    private ArrayList<String> films;
    private String url;
}
