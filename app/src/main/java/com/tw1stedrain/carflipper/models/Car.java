package com.tw1stedrain.carflipper.models;

import com.google.firebase.firestore.Exclude;
import com.google.firebase.firestore.IgnoreExtraProperties;

import java.util.ArrayList;
import java.util.List;

@IgnoreExtraProperties
public class Car {

    @Exclude
    private String id;

    private String nickname;
    private String make;
    private String model;
    private int year;
    private int initialPaid;
    private String details;
    private boolean sold = false;

    private List<Work> works = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getInitialPaid() {
        return initialPaid;
    }

    public void setInitialPaid(int initialPaid) {
        this.initialPaid = initialPaid;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    public List<Work> getWorks() {
        return works;
    }

    public void setWorks(List<Work> works) {
        this.works = works;
    }
}
