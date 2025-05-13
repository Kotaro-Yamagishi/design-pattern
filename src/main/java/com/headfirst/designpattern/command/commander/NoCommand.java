package com.headfirst.designpattern.command.commander;

// NoCommandを作成することでnullチェックをする必要がなくなる
public class NoCommand implements Command {
    @Override
    public void execute() {
        System.out.println("No command assigned to the button.");
    }

    @Override
    public void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'undo'");
    }
}
