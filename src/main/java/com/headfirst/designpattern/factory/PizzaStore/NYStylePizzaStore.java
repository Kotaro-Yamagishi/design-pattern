package com.headfirst.designpattern.factory.PizzaStore;

import com.headfirst.designpattern.factory.Pizza.Pizza;
import com.headfirst.designpattern.factory.Pizza.NYStyle.NYStyleCheesePizza;
import com.headfirst.designpattern.factory.Pizza.NYStyle.NYStyleClamPizza;
import com.headfirst.designpattern.factory.Pizza.NYStyle.NYStylePepperoniPizza;
import com.headfirst.designpattern.factory.Pizza.NYStyle.NYStyleVeggiePizza;

public class NYStylePizzaStore extends PizzaStore {
    @Override
    Pizza createPizza(String type) {
        if(type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if(type.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else if(type.equals("clam")) {
            return new NYStyleClamPizza();
        } else if(type.equals("veggie")) {
            return new NYStyleVeggiePizza();
        } else {
            return null;
        }
    }
    
}
