package com.headfirst.designpattern.observer;

// このクラスの中でObserverへの追加とupdate処理の内容を実装する（updateの内容に関してはObserver単位で具象実装される）
public class CurrentConditionsDisplay implements Observer, DisplayElement {
    private float temperature;
    private float humidity;
    private WeatherData weatherData; // 将来observerから抜ける可能性もあるため、参照を保持している

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    // updateに引数を持たせた場合、Observerによっては引数の内容が異なるため、Observer単位で具象実装した方が柔軟性が高い
    // @Override
    // public void update(float temp, float humidity, float pressure) {
    //     this.temperature = temp;
    //     this.humidity = humidity;
    //     display();
    // }

    @Override
    public void update() {
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
        display();
    }

    @Override
    public void display() {
        System.out.println("Current conditions: " + temperature + "F degrees and " + humidity + "% humidity");
    }
    
}
