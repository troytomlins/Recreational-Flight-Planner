package seng202.group10.model;

import java.util.ArrayList;

public class UserModel {
    private ArrayList<User> users;

    /**
     * adds new user to ArrayList users
     * @param user
     */
    public addUser(User user) {
        users.add(user);
    }

    /**
     * removes user from ArrayList users
     * @param user
     */
    public deleteUser(User user) {
        users.remove(user);
    }
}
