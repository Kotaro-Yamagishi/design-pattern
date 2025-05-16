package com.headfirst.designpattern.templatemethod.inheritance;

public class Tea extends CaffeineBeverageWithHook {
    @Override
    void brew() {
        System.out.println("Steeping the tea");
    }

    @Override
    void addCondiments() {
        System.out.println("Adding lemon");
    }
    
}
