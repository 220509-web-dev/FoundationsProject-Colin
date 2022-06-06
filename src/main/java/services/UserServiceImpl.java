package services;

import data.UserDAO;
import entities.data.User;

import java.util.List;

public class UserServiceImpl implements UserService{

    private UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUsers() {
        return this.userDAO.getUsers();
    }

    @Override
    public User createUser(User user) {
        return null;
    }

    @Override
    public User getUserById(int userId) {
        return this.userDAO.getUserById(userId);
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userDAO.getUserByUsername(username);
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public Boolean deletedUser(int userId) {
        return null;
    }
}
