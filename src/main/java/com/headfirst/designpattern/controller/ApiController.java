package com.headfirst.designpattern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.headfirst.designpattern.adapter.DuckTestDrive;
import com.headfirst.designpattern.command.RemoteControlTest;
import com.headfirst.designpattern.decorator.DecoratorExec;
import com.headfirst.designpattern.facade.HomeTheaterTestDrive;
import com.headfirst.designpattern.factory.FactoryTestDrive;
import com.headfirst.designpattern.introducation.KnifeBehavior;
import com.headfirst.designpattern.introducation.Queen;
import com.headfirst.designpattern.observer.WeatherStation;
import com.headfirst.designpattern.singleton.ChocolateBoiler;
import com.headfirst.designpattern.singleton.EnumSingleton;
import com.headfirst.designpattern.templatemethod.inheritance.BeverageTestDrive;

@RestController
public class ApiController {

    @GetMapping("/introduction")
    public void callExternalApi() {
        Queen queen = new Queen();
		queen.setWweapon(new KnifeBehavior());
		queen.fight();
    }

    @GetMapping("/observer")
    public void weatherStation() {
        WeatherStation weatherStation = new WeatherStation();
        weatherStation.test();
    }

    @GetMapping("/decorator")
    public void decorator() {
        DecoratorExec decoratorExec = new DecoratorExec();
        decoratorExec.test();
    }

    @GetMapping("/factory") 
    public void factory() {
        FactoryTestDrive factoryTestDrive = new FactoryTestDrive();
        factoryTestDrive.test();
    }

    @GetMapping("/singleton")
    public void singleton() {
        ChocolateBoiler boiler = ChocolateBoiler.getInstance();
        boiler.fill();
        boiler.boil();
        boiler.drain();
        System.out.println("Boiler is empty: " + boiler.isEmpty());
        System.out.println("Boiler is boiled: " + boiler.isBoiled());

        EnumSingleton enumSingleton = EnumSingleton.UNIQUE_INSTANCE;
        System.out.println("Enum Singleton instance: " + enumSingleton);
    }

    @GetMapping("/command")
    public void command() {
        RemoteControlTest remote = new RemoteControlTest();
        remote.test();
    }

    @GetMapping("/adapter")
    public void adapter() {
        DuckTestDrive duckTestDrive = new DuckTestDrive();
        duckTestDrive.test();
    }

    @GetMapping("/facade")
    public void facade() {
        HomeTheaterTestDrive homeTheaterTestDrive = new HomeTheaterTestDrive();
        homeTheaterTestDrive.test();
    }

    @GetMapping("/templatemethod/inheritance")
    public void templateMethodInheritance() {
        BeverageTestDrive beverageTestDrive = new BeverageTestDrive();  
        beverageTestDrive.test();
    }

    
}
