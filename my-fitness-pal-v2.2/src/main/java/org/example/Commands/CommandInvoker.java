package org.example.Commands;

import org.example.Item.Item;

public class CommandInvoker
{
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean executeCommand() {
        return command.execute();
    }

    public Command getCommand() {
        return this.command;
    }
}