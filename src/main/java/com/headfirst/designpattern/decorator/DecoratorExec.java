package com.headfirst.designpattern.decorator;

public class DecoratorExec {
        public void test() {
            Beverage beverage = new Espresso();
            System.out.println(beverage.getDescription() + " $" + beverage.cost());
    
            Beverage beverage2 = new HouseBlend();
            beverage2 = new Mocha(beverage2);
            beverage2 = new Soy(beverage2);
            beverage2 = new Whip(beverage2);
            System.out.println(beverage2.getDescription() + " $" + beverage2.cost());
        }
}
