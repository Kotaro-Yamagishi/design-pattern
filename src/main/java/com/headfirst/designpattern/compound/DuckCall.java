package com.headfirst.designpattern.compound;

public class DuckCall implements Quackable {

    @Override
    public void quack() {
        System.out.println("Kwak");
    }

    @Override
    public void registerObserver(Observer observer) {
        // No implementation needed for DuckCall
    }

    @Override
    public void notifyObservers() {
        // No implementation needed for DuckCall
    }

    @Override
    public String toString() {
        return "Duck Call";
    }
    
}
