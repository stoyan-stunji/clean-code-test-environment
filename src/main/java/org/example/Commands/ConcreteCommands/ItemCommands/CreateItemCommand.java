package org.example.Commands.ConcreteCommands.ItemCommands;

import org.example.Commands.Command;
import org.example.Item.Item;
import org.example.Item.Utility.ItemUtility;

public class CreateItemCommand<T extends Item> implements Command {
    private final ItemUtility<T> itemUtility;
    private final String username;
    private final T item;

    public CreateItemCommand(ItemUtility<T> itemUtility, String username, T item) {
        this.itemUtility = itemUtility;
        this.username = username;
        this.item = item;
    }

    public boolean execute() {
        return itemUtility.saveItemDetailsToFile(username, item);
    }
}
