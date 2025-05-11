package com.headfirst.designpattern.command.commander;

import com.headfirst.designpattern.command.receiver.Light;

public class LightOnCommand implements Command {
    private Light light;

    public void setLight(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        if (light != null) {
            light.on();
        } else {
            System.out.println("Light is not set.");
        }
    }
    
}
