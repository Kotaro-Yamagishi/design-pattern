package com.headfirst.designpattern.factory.Pizza.NYStyle;

import com.headfirst.designpattern.factory.Pizza.ClamPizza;

public class NYStyleClamPizza extends ClamPizza {
    public NYStyleClamPizza() {
        name = "NY Style Clam Pizza";
        dough = "Thin Crust Dough";
        sauce = "Marinara Sauce";

        toppings.add("Clams from Long Island Sound");
        toppings.add("Chopped Garlic");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
    
}
