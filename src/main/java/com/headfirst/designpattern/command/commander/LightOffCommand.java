package com.headfirst.designpattern.command.commander;

import com.headfirst.designpattern.command.receiver.Light;

public class LightOffCommand implements Command {
    private Light light;

    public void setLight(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        if (light != null) {
            light.off();
        } else {
            System.out.println("Light is not set.");
        }
    }

    @Override
    public void undo() {
        if (light != null) {
            light.on();
        } else {
            System.out.println("Light is not set.");
        }
    }
    
}
