package com.headfirst.designpattern.factory.Pizza;

public abstract class CheesePizza extends Pizza {
    public CheesePizza() {
        name = "Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }
    
}
