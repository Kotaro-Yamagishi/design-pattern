package com.headfirst.designpattern.factory.Pizza;

public abstract class PepperoniPizza extends Pizza {
    public PepperoniPizza() {
        name = "Pepperoni Pizza";
        dough = "Crust";
        sauce = "Marinara Sauce";
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

}
