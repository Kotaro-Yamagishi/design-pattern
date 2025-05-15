package com.headfirst.designpattern.facade;

public class StreamingPlayer {
    public void on() {
        System.out.println("Streaming player is on");
    }

    public void off() {
        System.out.println("Streaming player is off");
    }
    public void play(String movie) {
        System.out.println("Streaming player is playing " + movie);
    }

    public void stop() {
        System.out.println("Streaming player is stopped");
    }
}
