package com.example.demo.routine;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.demo.exercise.Exercise;

import java.util.Date;
import java.util.List;

@Document
public class Routine {
    @Id()
    private String id;
    private Integer userId;
    private Date createdAt;
    private String title;
    private String description;
    private List<Exercise> exercises;
    // n sets, stations
    private Integer units;
    // sets, circuit, emom
    private String style;
    // weighted, calisthenics, weighted calisthenics, hybrid
    private String type;
    private Boolean shared;

    public Routine(){
        
    }

    public Routine(String id, Integer userId, Date createdAt, String title, String description, List<Exercise> exercises, Integer units, String style, String type, Boolean shared) {
        this.id = id;
        this.userId = userId;
        this.createdAt = createdAt;
        this.title = title;
        this.description = description;
        this.exercises = exercises;
        this.units = units;
        this.style = style;
        this.type = type;
        this.shared = shared;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Boolean getShared() {
        return shared;
    }

    public void setShared(Boolean shared) {
        this.shared = shared;
    }
}
