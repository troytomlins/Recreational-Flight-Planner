package seng202.group10.model;

import java.util.ArrayList;

public class UserModel {
    private ArrayList<User> users;

    /**
     * adds new user to ArrayList users
     * @param user Class User
     */
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
        }
    }

    public ArrayList<User> getUsers() {
        return(users);
    }


    /**
     * removes user from ArrayList users
     * @param user Class User
     */
    public void deleteUser(User user) {
        users.remove(user);
    }
}
