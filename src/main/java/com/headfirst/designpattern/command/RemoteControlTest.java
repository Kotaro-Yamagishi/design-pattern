package com.headfirst.designpattern.command;

import com.headfirst.designpattern.command.client.RemoteControl;
import com.headfirst.designpattern.command.commander.GarageDoorOpenCommand;
import com.headfirst.designpattern.command.commander.LightOnCommand;
import com.headfirst.designpattern.command.receiver.GarageDoor;
import com.headfirst.designpattern.command.receiver.Light;

public class RemoteControlTest {
    public void test() {
        RemoteControl remote = new RemoteControl();
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();
        
        // Using the Command pattern
        LightOnCommand lightOnCommand = new LightOnCommand();
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand();
        lightOnCommand.setLight(light);
        garageDoorOpenCommand.setGarageDoor(garageDoor);
        
        // remote.setCommand(lightOnCommand);
        // remote.buttonWasPressed(); // Output: "Light is ON"
        // remote.setCommand(garageDoorOpenCommand);
        // remote.buttonWasPressed(); // Output: "Garage door is OPEN"
    }
}
