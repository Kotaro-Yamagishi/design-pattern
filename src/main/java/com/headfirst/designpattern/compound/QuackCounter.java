package com.headfirst.designpattern.compound;

public class QuackCounter implements Quackable {
    private Quackable duck;
    private static int quackCount = 0;

    public QuackCounter(Quackable duck) {
        this.duck = duck;
    }

    @Override
    public void quack() {
        duck.quack();
        quackCount++;
    }

    @Override
    public void registerObserver(Observer observer) {
        duck.registerObserver(observer);
    }

    @Override
    public void notifyObservers() {
        duck.notifyObservers();
    }

    public static int getQuackCount() {
        return quackCount;
    }

    @Override
    public String toString() {
        return duck.toString();
    }
    
}
