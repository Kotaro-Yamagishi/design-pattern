package com.headfirst.designpattern.facade;

public class Tuner {
    public void on() {
        System.out.println("Tuner is on");
    }
    public void off() {
        System.out.println("Tuner is off");
    }
    public void setAm(int frequency) {
        System.out.println("Tuner is set to AM frequency " + frequency);
    }
    public void setFm(int frequency) {
        System.out.println("Tuner is set to FM frequency " + frequency);
    }
}
