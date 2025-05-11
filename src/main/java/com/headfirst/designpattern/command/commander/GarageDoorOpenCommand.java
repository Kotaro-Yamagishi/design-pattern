package com.headfirst.designpattern.command.commander;

import com.headfirst.designpattern.command.receiver.GarageDoor;

public class GarageDoorOpenCommand implements Command {
    private GarageDoor garageDoor;

    public void setGarageDoor(GarageDoor garageDoor) {
        this.garageDoor = garageDoor;
    }

    @Override
    public void execute() {
        if (garageDoor != null) {
            garageDoor.up();
        } else {
            System.out.println("Garage door is not set.");
        }
    }

}
