package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserModelTest {

    private UserModel testUsers;
    private User testUser;
    private ArrayList<User> testArray;

    @BeforeEach
    public void init() {
        testUsers = new UserModel();
        testUser = new User("John");
    }
    /**
     * Creates and finds User
     */
    @Test
    public void makeUserTest(){
        ArrayList<User> users;
        boolean found = false;
        testUsers.addUser(testUser);
        testArray = testUsers.getUsers();
        for (User user : testArray) {
            if (user.getUserName().equals("John")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    /**
     * Checks if the same user can be added more than once
     */
    @Test
    public void addDuplicateUserTest(){
        ArrayList<User> compareArray = new ArrayList<>();
        compareArray.add(testUser);
        testUsers.addUser(testUser);
        testUsers.addUser(testUser);
        assertEquals(compareArray, testUsers.getUsers());
    }

    /**
     * Checks User deletion
     */
    @Test
    public void deleteUserTest() {
        int count = 0;
        testUsers.addUser(testUser);
        testUsers.deleteUser(testUser);
        testArray = testUsers.getUsers();
        for (User user : testArray) {
            count += 1;
        }
        assertEquals(0, count);
    }
}
