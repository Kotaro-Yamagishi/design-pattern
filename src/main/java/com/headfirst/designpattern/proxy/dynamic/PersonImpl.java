package com.headfirst.designpattern.proxy.dynamic;

public class PersonImpl implements Person {
    String name;
    String gender;
    String interests;
    int rating;;
    int ratingCount = 0;

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public String getInterests() {
        return interests;
    }

    @Override
    public int getGeekRating() {
        if (ratingCount == 0) {
            return 0; // Avoid division by zero
        }
        return rating / ratingCount;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public void setInterests(String interests) {
        this.interests = interests;
    }

    @Override
    public void setGeekRating(int geekRating) {
        this.rating += geekRating;
        this.ratingCount++;
    }
}
