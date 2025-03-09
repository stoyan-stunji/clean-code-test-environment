package org.example.Item.Utility;

import org.example.Item.Bank.ItemBank;
import org.example.Item.Item;

public class ItemUtilityImpl<T extends Item> implements ItemUtility<T> {
    private final ItemBank<T> itemBank;

    public ItemUtilityImpl(ItemBank<T> itemBank) {
        this.itemBank = itemBank;
    }

    public boolean saveItemDetailsToFile(String username, T item) {
        return itemBank.save(item);
    }
}
