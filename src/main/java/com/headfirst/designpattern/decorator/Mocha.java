package com.headfirst.designpattern.decorator;

public class Mocha extends CondimentDecorator {
    Beverage beverage;

    // Constructor
    public Mocha(Beverage beverage) {
        this.beverage = beverage;
    }

    @Override
    public String getDescription() {
        return beverage.getDescription() + ", Mocha";
    }

    @Override
    public double cost() {
        return 0.20 + beverage.cost();
    }
    
}
