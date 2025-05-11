package com.headfirst.designpattern.factory.PizzaStore;

import com.headfirst.designpattern.factory.Pizza.Pizza;
import com.headfirst.designpattern.factory.Pizza.ChicagoStyle.ChicagoStyleCheesePizza;
import com.headfirst.designpattern.factory.Pizza.ChicagoStyle.ChicagoStyleClamPizza;
import com.headfirst.designpattern.factory.Pizza.ChicagoStyle.ChicagoStylePepperoniPizza;
import com.headfirst.designpattern.factory.Pizza.ChicagoStyle.ChicagoStyleVeggiePizza;

public class ChicagoPizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")) {
            return new ChicagoStyleCheesePizza();
        } else if(type.equals("pepperoni")) {
            return new ChicagoStylePepperoniPizza();
        } else if(type.equals("clam")) {
            return new ChicagoStyleClamPizza();
        } else if(type.equals("veggie")) {
            return new ChicagoStyleVeggiePizza();
        } else {
            return null;
        }
    }
    
}
