package com.headfirst.designpattern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.headfirst.designpattern.decorator.DecoratorExec;
import com.headfirst.designpattern.factory.FactoryTestDrive;
import com.headfirst.designpattern.introducation.KnifeBehavior;
import com.headfirst.designpattern.introducation.Queen;
import com.headfirst.designpattern.observer.WeatherStation;

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
}
