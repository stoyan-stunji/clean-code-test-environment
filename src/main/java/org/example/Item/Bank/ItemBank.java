package org.example.Item.Bank;

import org.example.Item.Item;

import java.util.Optional;

public interface ItemBank<T extends Item> {
    boolean save(T item);
    Optional<String> returnContentFromFile(T item, String username, String date);
}
