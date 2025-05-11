package com.headfirst.designpattern.factory.Pizza.ChicagoStyle;

import com.headfirst.designpattern.factory.Pizza.VeggiePizza;

public class ChicagoStyleVeggiePizza extends VeggiePizza {
    public ChicagoStyleVeggiePizza() {
        name = "Chicago Style Veggie Pizza";
        dough = "Extra Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
        toppings.add("Black Olives");
        toppings.add("Spinach");
        toppings.add("Eggplant");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
    
}
