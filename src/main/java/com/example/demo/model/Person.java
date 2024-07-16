package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Person {
    @JsonAlias("birth_year")
    private Integer birthYear;
    @JsonAlias("death_year")
    private Integer deathYear;
    private String name;

    // Getters y Setters
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                ", name='" + name + '\'' +
                '}';
    }
}

