package com.headfirst.designpattern.command.receiver;

public class Stereo {
    public void on() {
        System.out.println("Stereo is ON");
    }

    public void off() {
        System.out.println("Stereo is OFF");
    }

    public void setCD() {
        System.out.println("Stereo is set for CD input");
    }

    public void setDVD() {
        System.out.println("Stereo is set for DVD input");
    }

    public void setRadio() {
        System.out.println("Stereo is set for Radio input");
    }

    public void setVolume(int volume) {
        System.out.println("Stereo volume set to " + volume);
    }
}
