import org.example.Item.Items.Food;
import org.example.Item.Items.FoodDetails.FoodMacros;
import org.example.Item.Items.FoodDetails.FoodServingDetails;
import org.example.Item.Repository.Food.FoodRepoFileImpl;
import org.example.Item.Service.Food.FoodServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FoodServiceImplTest {
    private FoodRepoFileImpl foodRepo;
    private FoodServiceImpl foodService;

    @BeforeEach
    void setUp() {
        foodRepo = new FoodRepoFileImpl();
        foodService = new FoodServiceImpl(foodRepo);
    }

    @Test
    void testSaveFoodDetailsToRepo_Success() {
        Food food = createSampleFood();
        assertTrue(foodService.saveFoodDetailsToRepo(food, "2025-03-16"));
    }

    @Test
    void testSaveFoodDetailsToAllFoodsFile() {
        Food food = createSampleFood();
        assertTrue(foodService.saveFoodDetailsToAllFoodsFile(food));
    }

    @Test
    void testFindFoodById_Found() {
        Food food = foodService.findFoodById(1);
        assertNotNull(food);
    }

    @Test
    void testFindFoodById_NotFound() {
        Food food = foodService.findFoodById(999);
        assertNull(food);
    }

    @Test
    void testGetAllFoodsFromRepo() {
        String result = foodService.getAllFoodsFromRepo();
        assertNotNull(result);
    }

    @Test
    void testSaveMultipleFoodsToRepo() {
        Food food1 = createSampleFood();
        Food food2 = new Food("user1", "Banana", "A fruit", createServingDetails(), createMacros());
        assertTrue(foodService.saveFoodDetailsToRepo(food1, "2025-03-16"));
        assertTrue(foodService.saveFoodDetailsToRepo(food2, "2025-03-16"));
    }

    @Test
    void testSaveFoodWithoutDescription() {
        Food food = new Food("user1", "Orange", "", createServingDetails(), createMacros());
        assertTrue(foodService.saveFoodDetailsToRepo(food, "2025-03-16"));
    }

    @Test
    void testGetFoodForUserByDate_MultipleEntries() {
        Food food1 = createSampleFood();
        Food food2 = new Food("user1", "Banana", "A fruit", createServingDetails(), createMacros());
        foodService.saveFoodDetailsToRepo(food1, "2025-03-16");
        foodService.saveFoodDetailsToRepo(food2, "2025-03-16");

        String result = foodService.getFoodForUserByDate("user1", "2025-03-16");
        assertTrue(result.contains("Apple") && result.contains("Banana"));
    }

    private Food createSampleFood() {
        return new Food("user1", "Apple", "A fruit", createServingDetails(), createMacros());
    }

    private FoodServingDetails createServingDetails() {
        return new FoodServingDetails("100g", "1");
    }

    private FoodMacros createMacros() {
        return new FoodMacros("52", "14", "0.2", "0.3");
    }
}
