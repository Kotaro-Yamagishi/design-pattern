package com.headfirst.designpattern.decorator;

public class HouseBlend extends Beverage {
    // Constructor
    public HouseBlend() {
        description = "House Blend Coffee";
    }

    @Override
    public double cost() {
        return 0.89;
    }
}
