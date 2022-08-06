package com.example.demo.exercise;

public class Exercise {
    private String name;
    private Integer repetitions;

    public Exercise(){

    }

    public Exercise(String name, Integer repetitions) {
        this.name = name;
        this.repetitions = repetitions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(Integer repetitions) {
        this.repetitions = repetitions;
    }
}
