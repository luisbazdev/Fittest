package com.example.demo.workout;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class Workout {
    @Id
    private String id;
    private Integer user_id;
    private String routine_id;
    // n sets, stations
    private Integer units;
    // sets, circuit, emom
    private String style;
    // weighted, calisthenics, weighted calisthenics
    private String type;
    private List<String> training_days;

    public Workout(){

    }

    public Workout(String id, Integer user_id, String routine_id, Integer units, String style, String type, List<String> training_days) {
        this.id = id;
        this.user_id = user_id;
        this.routine_id = routine_id;
        this.units = units;
        this.style = style;
        this.type = type;
        this.training_days = training_days;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public String getRoutine_id() {
        return routine_id;
    }

    public void setRoutine_id(String routine_id) {
        this.routine_id = routine_id;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getTraining_days() {
        return training_days;
    }

    public void setTraining_days(List<String> training_days) {
        this.training_days = training_days;
    }
}
