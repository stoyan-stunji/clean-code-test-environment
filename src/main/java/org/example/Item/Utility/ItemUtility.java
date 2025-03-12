package org.example.Item.Utility;

import org.example.Item.Item;

public interface ItemUtility<T extends Item> {
    boolean saveItemDetailsToFile(String username, T item);
}
