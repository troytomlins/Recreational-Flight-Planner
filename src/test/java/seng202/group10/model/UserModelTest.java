package seng202.group10.model;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class UserModelTest {

    private UserModel testUsers = new UserModel();

    @Test
    public void makeUser(){
        ArrayList<User> users;
        boolean found = false;
        User newUser = new User("John");
        testUsers.addUser(newUser);
        users = testUsers.getUsers();
        for (User user : users) {
            if (user.getUserName().equals("John")) {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }
}
