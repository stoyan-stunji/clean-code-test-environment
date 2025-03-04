package org.example.Commands;

public class CommandInvoker
{
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public boolean executeCommand() {
        return command != null && command.execute();
    }

    public Command getCommand() {
        return this.command;
    }
}