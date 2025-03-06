package org.example.Water.Bank;

import org.example.Water.Water;
import java.util.Optional;

public interface WaterBank {
    boolean save(Water water);
    Optional<String> getByBothUsernameAndDate(String username, String date);
}
