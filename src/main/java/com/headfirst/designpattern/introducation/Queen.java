package com.headfirst.designpattern.introducation;

public class Queen extends Charactor {

    @Override
    public void fight() {
        System.out.println("Queen is fighting");
        weaponBehavior.useWeapon();
    }
    
}
