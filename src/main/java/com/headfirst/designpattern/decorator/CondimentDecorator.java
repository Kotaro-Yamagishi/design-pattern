package com.headfirst.designpattern.decorator;

public abstract class CondimentDecorator extends Beverage {
    Beverage beverage;
    // このクラスを継承したDecoratorは再度getDescription()をオーバーライドする必要がある
    public abstract String getDescription();
    
}
