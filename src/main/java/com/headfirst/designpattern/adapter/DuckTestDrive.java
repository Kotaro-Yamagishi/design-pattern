package com.headfirst.designpattern.adapter;

public class DuckTestDrive {
    
    public void test(){
        Duck duck = new MallardDuck();
        Turkey turkey = new WildTurkey();
        Duck turkeyAdapter = new TurkeyAdapter(turkey);

        System.out.println("The Turkey says...");
        turkey.gobble();
        turkey.fly();
        System.out.println("\nThe Duck says...");
        testDuck(duck);
        System.out.println("\nThe TurkeyAdapter says...");
        testDuck(turkeyAdapter);
    }

    void testDuck(Duck duck) {
        duck.quack();
        duck.fly();
    }
}
