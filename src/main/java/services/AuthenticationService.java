package services;

import data.UserDAO;
import entities.data.User;

public class AuthenticationService extends UserServiceImpl{

    public AuthenticationService(UserDAO userDAO) {
        super(userDAO);
    }

    public User login(String username, String password){
        User checkUser = super.getUserByUsername(username);

        if (checkUser != null && checkUser.getPassword().equals(password)) {
            return checkUser;
        } else {
            return null;
        }

    }

    public User register(User newUser) {
        User checkUser = super.getUserByUsername(newUser.getUsername());

        if (checkUser == null) {
            return super.createUser(newUser);
        } else {
            return null;
        }

    }


}
