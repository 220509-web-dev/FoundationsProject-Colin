package data;

import entities.data.User;

import java.util.List;

public interface UserDAO {

    List<User> getUsers();

    User createUser(User user);

    User getUserById(int userId);

    User getUserByUsername(String username);

    boolean updateUser(User user);

    Boolean deletedUser(int userId);

}
