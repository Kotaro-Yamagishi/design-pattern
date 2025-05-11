package com.headfirst.designpattern.command.commander;

import com.headfirst.designpattern.command.receiver.Stereo;

public class StereoOnWithCDCommand implements Command {
    private Stereo stereo;

    public void setStereo(Stereo stereo) {
        this.stereo = stereo;
    }

    @Override
    public void execute() {
        if (stereo != null) {
            stereo.on();
            stereo.setCD();
            stereo.setVolume(11);
        } else {
            System.out.println("Stereo is not set.");
        }
    }
    
}
