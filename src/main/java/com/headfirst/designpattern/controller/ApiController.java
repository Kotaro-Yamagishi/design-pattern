package com.headfirst.designpattern.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.headfirst.designpattern.introducation.KnifeBehavior;
import com.headfirst.designpattern.introducation.Queen;
import com.headfirst.designpattern.observer.WeatherStation;

@RestController
public class ApiController {

    @GetMapping("/")
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
}
