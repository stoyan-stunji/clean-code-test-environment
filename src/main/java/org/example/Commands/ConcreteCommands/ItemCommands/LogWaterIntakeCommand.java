package org.example.Commands.ConcreteCommands.ItemCommands;

import org.example.Commands.Command;
import org.example.Item.Items.Water;
import org.example.Item.Utility.Water.WaterUtility;

public class LogWaterIntakeCommand implements Command
{
    private final WaterUtility itemUtility;
    private final String username;
    private final Water item;

    public LogWaterIntakeCommand(WaterUtility itemUtility, String username, Water item) {
        this.itemUtility = itemUtility;
        this.username = username;
        this.item = item;
    }

    public boolean execute() {
        return itemUtility.saveItemDetailsToFile(username, item);
    }
}
