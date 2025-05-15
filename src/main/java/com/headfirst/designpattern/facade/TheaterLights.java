package com.headfirst.designpattern.facade;

public class TheaterLights {
    public void on() {
        System.out.println("Theater lights are on");
    }
    public void off() {
        System.out.println("Theater lights are off");
    }
    public void dim(int level) {
        System.out.println("Theater lights dimming to " + level + "%");
    }
}
