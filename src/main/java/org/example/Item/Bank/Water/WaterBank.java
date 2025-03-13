package org.example.Item.Bank.Water;

import org.example.Item.Items.Water;

import java.util.Optional;

public interface WaterBank
{
    boolean save(Water water);
    Optional<String> returnContentFromFile(String username, String date);
}
