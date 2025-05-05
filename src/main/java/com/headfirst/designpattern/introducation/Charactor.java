package com.headfirst.designpattern.introducation;

public abstract class Charactor {
    WeaponBehavior weaponBehavior;

    public void setWweapon(WeaponBehavior weaponBehavior) {
        this.weaponBehavior = weaponBehavior;
    }

    public void fight(){
        weaponBehavior.useWeapon();
        System.out.println("Charactor is fighting");
    }
}
