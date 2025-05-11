package com.headfirst.designpattern.factory.Pizza.ChicagoStyle;

import com.headfirst.designpattern.factory.Pizza.CheesePizza;

public class ChicagoStyleCheesePizza extends CheesePizza {
    public ChicagoStyleCheesePizza() {
        name = "Chicago Style Deep Dish Cheese Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
    }

    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
}
