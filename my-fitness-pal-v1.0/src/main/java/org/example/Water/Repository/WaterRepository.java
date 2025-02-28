package org.example.Water.Repository;

import org.example.ID.ID;
import org.example.Water.Water;
import java.util.Optional;

public interface WaterRepository {
    void save(Water water);
    Optional<String> getByUserAndDate(ID userId, String date);
}
