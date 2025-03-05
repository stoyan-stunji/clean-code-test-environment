package org.example.Water.Repository;

import org.example.Water.Water;
import java.util.Optional;

public interface WaterRepository {
    void save(Water water);
    Optional<String> getByBothUsernameAndDate(String username, String date);
}
