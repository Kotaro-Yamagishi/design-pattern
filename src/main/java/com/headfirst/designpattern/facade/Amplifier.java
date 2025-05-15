package com.headfirst.designpattern.facade;

public class Amplifier {
    Tuner tuner;
    StreamingPlayer player;

    public Amplifier(Tuner tuner, StreamingPlayer player) {
        this.tuner = tuner;
        this.player = player;
    }

    public void on() {
        System.out.println("Amplifier is on");
    }
    public void off() {
        System.out.println("Amplifier is off");
    }
    public void setStreamingPlayer(StreamingPlayer player) {
        this.player = player;
        System.out.println("Amplifier is set to streaming player");
    }
    public void setStereoSound(int volume) {
        System.out.println("Amplifier volume set to " + volume);
    }

    public void setTuner(Tuner tuner) {
        this.tuner = tuner;
        System.out.println("Amplifier is set to tuner");
    }
    public void setVolume(int volume) {
        System.out.println("Amplifier volume set to " + volume);
    }
}
