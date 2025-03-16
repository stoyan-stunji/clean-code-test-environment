package org.example.Item.Repository.Water;

import org.example.Item.Items.Water;

public interface WaterRepo
{
    boolean saveForUser(Water water);
    String returnContentFromFileForUser(String username, String date);
}
