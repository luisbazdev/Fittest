package com.example.demo.routine;

import com.example.demo.exercises.Exercise;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
public class Routine {
    @Id()
    private String id;
    private Integer user_id;
    private Date createdAt;
    private String description;
    private List<Exercise> exercises;
    // n sets, stations
    private Integer units;
    // sets, circuit, emom
    private String style;
    // weighted, calisthenics, weighted calisthenics
    private String type;

    public Routine(){
        
    }

    public Routine(String id, Integer user_id, Date createdAt, String description, List<Exercise> exercises, Integer units, String style, String type) {
        this.id = id;
        this.user_id = user_id;
        this.createdAt = createdAt;
        this.description = description;
        this.exercises = exercises;
        this.units = units;
        this.style = style;
        this.type = type;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(List<Exercise> exercises) {
        this.exercises = exercises;
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
}
