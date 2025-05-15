package com.headfirst.designpattern.facade;

public class HomeTheaterTestDrive {
    public void test(){
        Tuner tuner = new Tuner();
        StreamingPlayer player = new StreamingPlayer();
        Amplifier amp = new Amplifier(tuner, player);
        Projector projector = new Projector(player);
        TheaterLights lights = new TheaterLights();
        Screen screen = new Screen();
        PopcornPopper popper = new PopcornPopper();
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(amp, tuner, player, projector, lights, screen, popper);   

        homeTheater.watchMovie("Inception");
        homeTheater.endMovie();
    }
}
