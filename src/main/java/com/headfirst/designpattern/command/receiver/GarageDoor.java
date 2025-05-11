package com.headfirst.designpattern.command.receiver;

public class GarageDoor {
    public void up() {
        System.out.println("Garage Door is Open");
    }

    public void down() {
        System.out.println("Garage Door is Close");
    }

    public void stop() {
        System.out.println("Garage Door is Stopped");
    }

    public void lightOn() {
        System.out.println("Garage Door light is ON");
    }

    public void lightOff() {
        System.out.println("Garage Door light is OFF");
    }
}
