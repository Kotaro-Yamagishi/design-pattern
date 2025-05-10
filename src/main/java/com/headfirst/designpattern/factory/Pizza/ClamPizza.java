package com.headfirst.designpattern.factory.Pizza;

public abstract class ClamPizza extends Pizza {
    public ClamPizza() {
        name = "Clam Pizza";
        dough = "Crust";
        sauce = "Marinara Sauce";
    }

    @Override
    public void prepare() {
        System.out.println("Preparing " + name);
    }

    @Override
    public void bake() {
        System.out.println("Baking " + name);
    }

    @Override
    public void cut() {
        System.out.println("Cutting " + name);
    }

    @Override
    public void box() {
        System.out.println("Boxing " + name);
    }

}
