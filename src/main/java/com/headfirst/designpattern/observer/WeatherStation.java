package com.headfirst.designpattern.observer;

public class WeatherStation {

    public void test() {
        WeatherData weatherData = new WeatherData();
        new CurrentConditionsDisplay(weatherData);
        new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80, 65, 30.4f);
        weatherData.setMeasurements(82, 70, 29.2f);
        weatherData.setMeasurements(78, 90, 29.2f);
    }
}
