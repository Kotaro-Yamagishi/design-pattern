package com.headfirst.designpattern.observer;

public class ForecastDisplay implements Observer, DisplayElement {
    private float currentPressure = 29.92f; // inHg
    private float lastPressure;
    private WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        if (weatherData instanceof WeatherData) {
            WeatherData wd = (WeatherData) weatherData;
            lastPressure = currentPressure;
            currentPressure = wd.getPressure();
            display();
        }
    }

    @Override
    public void display() {
        System.out.print("Forecast: ");
        if (currentPressure > lastPressure) {
            System.out.println("Improving weather on the way!");
        } else if (currentPressure == lastPressure) {
            System.out.println("More of the same");
        } else if (currentPressure < lastPressure) {
            System.out.println("Watch out for cooler, rainy weather");
        }
    }
    
}
