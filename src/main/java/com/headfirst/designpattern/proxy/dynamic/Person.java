package com.headfirst.designpattern.proxy.dynamic;

public interface Person {
    String getName();
    String getGender();
    String getInterests();
    int getGeekRating();

    void setName(String name);
    void setGender(String gender);
    void setInterests(String interests);
    void setGeekRating(int geekRating);
}
