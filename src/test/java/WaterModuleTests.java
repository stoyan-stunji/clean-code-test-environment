import org.example.Item.Items.Water;
import org.example.Item.Repository.Water.WaterRepoFileImpl;
import org.example.Item.Service.Water.WaterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class WaterServiceImplTest {
    private WaterRepoFileImpl waterRepo;
    private WaterServiceImpl waterService;

    @BeforeEach
    void setUp() {
        waterRepo = new WaterRepoFileImpl();
        waterService = new WaterServiceImpl(waterRepo);
        new File("user_water_data").mkdirs();
    }

    @Test
    void testSaveWaterDetailsToRepo_Success() {
        Water water = new Water("user1", "500", "2025-03-16");
        assertTrue(waterService.saveWaterDetailsToRepo(water));
    }


    @Test
    void testGetWaterForUserByDate_Exception() {
        assertThrows(RuntimeException.class, () -> waterService.getWaterForUserByDate("user1", "invalid-date"));
    }

    @Test
    void testSaveMultipleEntries_Success() {
        Water water1 = new Water("user2", "300", "2025-03-16");
        Water water2 = new Water("user2", "200", "2025-03-16");
        assertTrue(waterService.saveWaterDetailsToRepo(water1));
        assertTrue(waterService.saveWaterDetailsToRepo(water2));
    }

    @Test
    void testReadMultipleEntries_Success() {
        Water water1 = new Water("user3", "300", "2025-03-16");
        Water water2 = new Water("user3", "400", "2025-03-16");
        waterService.saveWaterDetailsToRepo(water1);
        waterService.saveWaterDetailsToRepo(water2);

        String result = waterService.getWaterForUserByDate("user3", "2025-03-16");
        assertTrue(result.contains("300"));
        assertTrue(result.contains("400"));
    }

    @Test
    void testSaveAndRetrieveDifferentUsers() {
        Water water1 = new Water("user4", "150", "2025-03-16");
        Water water2 = new Water("user5", "250", "2025-03-16");
        waterService.saveWaterDetailsToRepo(water1);
        waterService.saveWaterDetailsToRepo(water2);

        assertEquals("150\n", waterService.getWaterForUserByDate("user4", "2025-03-16"));
        assertEquals("250\n", waterService.getWaterForUserByDate("user5", "2025-03-16"));
    }

    @Test
    void testRetrieveDataFromNonExistentUser() {
        assertThrows(RuntimeException.class, () -> waterService.getWaterForUserByDate("ghost_user", "2025-03-16"));
    }

    @Test
    void testRetrieveFromEmptyFile() {
        Water water = new Water("user7", "", "2025-03-16");
        waterService.saveWaterDetailsToRepo(water);
        String result = waterService.getWaterForUserByDate("user7", "2025-03-16");
        assertEquals("\n", result);
    }

    @Test
    void testSaveVeryLargeWaterIntake() {
        Water water = new Water("user8", "1000000", "2025-03-16");
        assertTrue(waterService.saveWaterDetailsToRepo(water));
    }

    @Test
    void testReadVeryLargeWaterIntake() {
        Water water = new Water("user9", "1000000", "2025-03-16");
        waterService.saveWaterDetailsToRepo(water);
        String result = waterService.getWaterForUserByDate("user9", "2025-03-16");
        assertEquals("1000000\n", result);
    }
}
