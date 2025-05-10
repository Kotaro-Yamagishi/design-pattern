package com.headfirst.designpattern.factory.Pizza.NYStyle;

import com.headfirst.designpattern.factory.Pizza.CheesePizza;

public class NYStyleCheesePizza extends CheesePizza {

    public NYStyleCheesePizza() {
        name = "NY Style Sauce and Cheese Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Grated Reggiano Cheese");
        toppings.add("Fresh Mozzarella Cheese");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
    
}
