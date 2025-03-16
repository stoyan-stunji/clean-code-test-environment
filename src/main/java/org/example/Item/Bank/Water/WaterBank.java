package org.example.Item.Bank.Water;

import org.example.Item.Items.Water;

import java.util.Optional;

public interface WaterBank
{
    boolean saveForUser(Water water);
    String returnContentFromFileForUser(String username, String date);
}
