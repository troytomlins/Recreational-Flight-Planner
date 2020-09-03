package seng202.group10.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class UserModelTest {

    private ArrayList<User> users;
    private UserModel testUsers;
    private User testUser;

    @BeforeEach
    public void init() {
        UserModel testUsers = new UserModel();
        User testUser = new User("Steve");
        testUsers.addUser(testUser);
    }

    @Test
    public void makeUser(){
        User newUser = new User("John");
        testUsers.addUser(newUser);
        users = testUsers.getUsers();
        assertTrue(users.contains(newUser));
    }
}
