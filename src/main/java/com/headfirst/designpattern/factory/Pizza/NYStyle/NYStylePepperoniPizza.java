package com.headfirst.designpattern.factory.Pizza.NYStyle;

import com.headfirst.designpattern.factory.Pizza.PepperoniPizza;

public class NYStylePepperoniPizza extends PepperoniPizza {
    public NYStylePepperoniPizza() {
        name = "NY Style Pepperoni Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Pepperoni");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
    
}
