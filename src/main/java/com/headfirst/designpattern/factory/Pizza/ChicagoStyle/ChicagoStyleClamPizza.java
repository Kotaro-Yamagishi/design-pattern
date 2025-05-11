package com.headfirst.designpattern.factory.Pizza.ChicagoStyle;

import com.headfirst.designpattern.factory.Pizza.ClamPizza;

public class ChicagoStyleClamPizza extends ClamPizza {
    public ChicagoStyleClamPizza() {
        name = "Chicago Style Clam Pizza";
        dough = "Thick Crust Dough";
        sauce = "Plum Tomato Sauce";

        toppings.add("Shredded Mozzarella Cheese");
        toppings.add("Frozen Clams from Chesapeake Bay");
    }

    @Override
    public void cut() {
        System.out.println("Cutting the pizza into square slices");
    }
    
}
