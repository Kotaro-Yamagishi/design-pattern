package com.headfirst.designpattern.templatemethod.composition;

public class Tea implements Beverage {
    @Override
    public void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    public void boilWater() {
        System.out.println("Boiling water");
    }

    @Override
    public void pourInCup() {
        System.out.println("Pouring into cup");
    }
    
    @Override
    public void addCondiments() {
        System.out.println("Adding lemon");
    }
}
