package com.headfirst.designpattern.factory.Pizza;

public abstract class VeggiePizza extends Pizza {
    public VeggiePizza() {
        name = "Veggie Pizza";
        dough = "Crust";
        sauce = "Marinara Sauce";
    }
}
