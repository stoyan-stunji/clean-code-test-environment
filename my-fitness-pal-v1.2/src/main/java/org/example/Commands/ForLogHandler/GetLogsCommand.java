package org.example.Commands.ForLogHandler;

import org.example.Commands.Command;
import org.example.LogHandler.LogHandler;

import java.util.ArrayList;
import java.util.List;

public class GetLogsCommand implements Command {
    private final LogHandler logger;

    public GetLogsCommand(LogHandler logger) {
        this.logger = logger;
    }

    public void execute() {
        List<String> logMessages = logger.getLogMessages();
        System.out.println(logMessages);
    }
}
