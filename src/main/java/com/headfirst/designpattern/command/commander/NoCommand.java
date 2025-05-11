package com.headfirst.designpattern.command.commander;

public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("No command assigned to the button.");
    }

}
