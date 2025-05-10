package com.headfirst.designpattern.factory;

import com.headfirst.designpattern.factory.Pizza.Pizza;
import com.headfirst.designpattern.factory.PizzaStore.NYStylePizzaStore;
import com.headfirst.designpattern.factory.PizzaStore.PizzaStore;

public class FactoryTestDrive {

    public void test() {
        PizzaStore pizzaStore = new NYStylePizzaStore();

        Pizza pizza = pizzaStore.orderPizza("cheese");
        System.out.println("Ethan ordered a " + pizza.getName() + "\n");
    }
}
