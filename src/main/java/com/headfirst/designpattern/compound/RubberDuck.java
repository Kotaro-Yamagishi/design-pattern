package com.headfirst.designpattern.compound;

public class RubberDuck implements Quackable {

    @Override
    public void quack() {
        System.out.println("Squeak");
    }

    @Override
    public void registerObserver(Observer observer) {
        // RubberDuck does not notify observers
    }

    @Override
    public void notifyObservers() {
        // RubberDuck does not notify observers
    }

    @Override
    public String toString() {
        return "Rubber Duck";
    }
    
}
