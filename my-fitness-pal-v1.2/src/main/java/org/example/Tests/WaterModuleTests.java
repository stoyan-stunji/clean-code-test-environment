package org.example.Tests;

import org.example.ID.ID;
import org.example.Water.Repository.WaterRepositoryImplementation;
import org.example.Water.Service.WaterServiceImplementation;
import org.example.Water.Water;
import org.example.Water.WaterDetails;
// import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;
/*
import static org.junit.jupiter.api.Assertions.*;

public class WaterModuleUnitTests {
    private WaterServiceImplementation waterService;
    private WaterRepositoryImplementation waterRepository;

    @BeforeEach
    public void setUp() {
        waterRepository = new WaterRepositoryImplementation();
        waterService = new WaterServiceImplementation(waterRepository);
    }

    @Test
    public void testLogWaterIntake() {
        ID userID = new ID();
        WaterDetails waterDetails = new WaterDetails(500, "2025-02-27");

        waterService.logWaterIntake(userID, waterDetails);

        Optional<String> result = waterRepository.getByUserAndDate(userID, "2025-02-27");

        assertTrue(result.isPresent());
        assertEquals("500\n", result.get());
    }

    @Test
    public void testRepositorySaveAndRetrieve() throws IOException {
        String storageDir = "water_data";
        File directory = new File(storageDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        ID userID = new ID();
        String date = "2025-02-27";
        String fileName = storageDir + "/" + userID.toString() + "_" + date + ".txt";

        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write("500\n");
        }

        Optional<String> result = waterRepository.getByUserAndDate(userID, date);

        assertTrue(result.isPresent());
        assertEquals("500\n", result.get());

        Files.deleteIfExists(new File(fileName).toPath());
    }

    @Test
    public void testGetByUserAndDateNotFound() {
        ID userID = new ID();
        String date = "2099-01-01";

        Optional<String> result = waterRepository.getByUserAndDate(userID, date);

        assertFalse(result.isPresent());
    }
}
*/
