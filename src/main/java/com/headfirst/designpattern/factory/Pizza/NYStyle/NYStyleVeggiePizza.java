package com.headfirst.designpattern.factory.Pizza.NYStyle;

import com.headfirst.designpattern.factory.Pizza.VeggiePizza;

public class NYStyleVeggiePizza extends VeggiePizza {
    public NYStyleVeggiePizza() {
        name = "NY Style Veggie Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Veggies from NY");
    }
}
