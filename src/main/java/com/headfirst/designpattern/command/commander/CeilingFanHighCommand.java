package com.headfirst.designpattern.command.commander;

import com.headfirst.designpattern.command.receiver.CeilingFan;

public class CeilingFanHighCommand implements Command {
    private CeilingFan ceilingFan;
    private int prevSpeed;

    public void setCeilingFan(CeilingFan ceilingFan) {
        this.ceilingFan = ceilingFan;
    }

    @Override
    public void execute() {
        if (ceilingFan != null) {
            prevSpeed = ceilingFan.getSpeed();
            ceilingFan.high();
        } else {
            System.out.println("Ceiling fan is not set.");
        }
    }

    @Override
    public void undo() {
        if (ceilingFan != null) {
            if (prevSpeed == CeilingFan.HIGH) {
                ceilingFan.high();
            } else if (prevSpeed == CeilingFan.MEDIUM) {
                ceilingFan.medium();
            } else if (prevSpeed == CeilingFan.LOW) {
                ceilingFan.low();
            } else if (prevSpeed == CeilingFan.OFF) {
                ceilingFan.off();
            }
        } else {
            System.out.println("Ceiling fan is not set.");
        }
    }
    
}
