package com.headfirst.designpattern.compound;

public  class GooseAdapter implements Quackable {

    private Goose goose;

    public GooseAdapter(Goose goose) {
        this.goose = goose;
    }

    @Override
    public void quack() {
        goose.honk();
    }

    @Override
    public void registerObserver(Observer observer) {
        // No implementation needed for GooseAdapter
    }

    @Override
    public void notifyObservers() {
        // No implementation needed for GooseAdapter
    }

    @Override
    public String toString() {
        return "Goose pretending to be a Duck";
    }
    
}
