package com.headfirst.designpattern.templatemethod.composition;

public class CaffeineBeverage {
    private Beverage beverage;

    public CaffeineBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public void prepareRecipe() {
        beverage.boilWater();
        beverage.brew();
        beverage.pourInCup();
        beverage.addCondiments();
    }
}
