package com.headfirst.designpattern.compound;

public class RedheadDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Quack");
    }

    @Override
    public void registerObserver(Observer observer) {
        // No implementation needed for RedheadDuck
    }

    @Override
    public void notifyObservers() {
        // No implementation needed for RedheadDuck
    }

    @Override
    public String toString() {
        return "Redhead Duck";
    }
    
}
