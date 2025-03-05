package org.example.LogHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class LogHandler {
    private static Integer level = 0;
    private final List<String> logMessages = new ArrayList<>();

    public void publish(String record) {
        logMessages.add((level++).toString() + " : " + record + '\n');
    }

    public void flush() {
    }

    public void close() throws SecurityException {
        logMessages.clear();
    }

    public List<String> getLogMessages() {
        return new ArrayList<>(logMessages);
    }
}
