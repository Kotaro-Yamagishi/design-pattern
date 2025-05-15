package com.headfirst.designpattern.facade;

public class Projector {
    StreamingPlayer player;

    public Projector(StreamingPlayer player) {
        this.player = player;
    }

    public void on() {
        System.out.println("Projector is on");
    }

    public void off() {
        System.out.println("Projector is off");
    }

    public void wideScreenMode() {
        System.out.println("Projector is in wide screen mode");
    }   
}
