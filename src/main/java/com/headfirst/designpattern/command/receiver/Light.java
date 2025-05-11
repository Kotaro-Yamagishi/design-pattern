package com.headfirst.designpattern.command.receiver;

public class Light {
    public void on() {
        System.out.println("Light is ON");
    }

    public void off() {
        System.out.println("Light is OFF");
    }

    public void dim(int level) {
        System.out.println("Light is dimmed to " + level + "%");
    }

    public void brighten(int level) {
        System.out.println("Light is brightened to " + level + "%");
    }
}
