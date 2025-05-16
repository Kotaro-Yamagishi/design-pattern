package com.headfirst.designpattern.templatemethod.composition;

public class Coffee implements Beverage {
    @Override
    public void brew() {
        System.out.println("Dripping Coffee through filter");
    }

    @Override
    public void boilWater() {
        System.out.println("Boiling water");
    }

    @Override
    public void pourInCup() {
        System.out.println("Pouring coffee into cup");
    }

    @Override
    public void addCondiments() {
        System.out.println("Adding Sugar and Milk");
    }
    
}
