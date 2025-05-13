package com.headfirst.designpattern.command;

import com.headfirst.designpattern.command.client.RemoteControlWithUndo;
import com.headfirst.designpattern.command.commander.Command;
import com.headfirst.designpattern.command.commander.GarageDoorCloseCommand;
import com.headfirst.designpattern.command.commander.GarageDoorOpenCommand;
import com.headfirst.designpattern.command.commander.LightOffCommand;
import com.headfirst.designpattern.command.commander.LightOnCommand;
import com.headfirst.designpattern.command.commander.MacroCommand;
import com.headfirst.designpattern.command.receiver.GarageDoor;
import com.headfirst.designpattern.command.receiver.Light;

public class RemoteControlTest {
    public void test() {
        RemoteControlWithUndo remote = new RemoteControlWithUndo();
        Light light = new Light();
        GarageDoor garageDoor = new GarageDoor();
        
        // Using the Command pattern
        LightOnCommand lightOnCommand = new LightOnCommand();
        LightOffCommand lightOffCommand = new LightOffCommand();
        GarageDoorOpenCommand garageDoorOpenCommand = new GarageDoorOpenCommand();
        GarageDoorCloseCommand garageDoorCloseCommand = new GarageDoorCloseCommand();
        lightOnCommand.setLight(light);
        lightOffCommand.setLight(light);
        garageDoorOpenCommand.setGarageDoor(garageDoor);
        garageDoorCloseCommand.setGarageDoor(garageDoor);

        Command[] partyOn={
                lightOnCommand,
                garageDoorOpenCommand
        };
        Command[] partyOff={
                lightOffCommand,
                garageDoorCloseCommand
        };
        
        MacroCommand partyOnMacro = new MacroCommand(partyOn);
        MacroCommand partyOffMacro = new MacroCommand(partyOff);

        remote.setCommand(0,lightOnCommand,lightOffCommand);
        remote.setCommand(1,garageDoorOpenCommand,garageDoorCloseCommand);
        remote.setCommand(2,partyOnMacro,partyOffMacro);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);
        System.out.println(remote);
        remote.undoButtonWasPushed();
        remote.onButtonWasPushed(1);
        remote.offButtonWasPushed(1);
        System.out.println(remote);
        remote.undoButtonWasPushed();

        remote.onButtonWasPushed(2);
        remote.offButtonWasPushed(2);
        System.out.println(remote);
    }
}
