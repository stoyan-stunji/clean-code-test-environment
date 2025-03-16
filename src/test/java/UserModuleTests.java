
import org.example.User.Repository.UserFileRepoImpl;
import org.example.User.Service.UserServiceImpl;
import org.example.User.User;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import static org.junit.jupiter.api.Assertions.*;

public class UserModuleTests {
    private static final String TEST_FILE = "test_users.txt";
    private UserFileRepoImpl userRepository;
    private UserServiceImpl userService;

    @BeforeEach
    public void setUp() {
        try {
            Files.write(Path.of(TEST_FILE), "".getBytes());
        } catch (IOException e) {
            throw new RuntimeException("Failed to create test file");
        }
        userRepository = new UserFileRepoImpl();
        userService = new UserServiceImpl(userRepository);
    }

    @Test
    public void testCreateUserSuccessfully() {
        boolean result = userService.createUser("testUser", "testPass");
        assertTrue(result);
    }

    @Test
    public void testCreateUserAlreadyExists() {
        userService.createUser("testUser", "testPass");
        boolean result = userService.createUser("testUser", "newPass");
        assertFalse(result);
    }

    @Test
    public void testGetUserByUsernameSuccessfully() {
        userService.createUser("testUser", "testPass");
        User user = userService.getUserByUsername("testUser");
        assertNotNull(user);
        assertEquals("testUser", user.getUsername());
    }

    @Test
    public void testGetUserByUsernameNotFound() {
        User user = userService.getUserByUsername("nonExistentUser");
        assertNull(user);
    }

    @Test
    public void testGetByUsernameCaseSensitivity() {
        userService.createUser("testUser", "testPass");
        assertNull(userService.getUserByUsername("testuser"));
    }

    @AfterEach
    public void tearDown() {
        new File(TEST_FILE).delete();
    }
}
