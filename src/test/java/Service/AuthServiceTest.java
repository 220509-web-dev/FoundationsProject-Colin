package Service;

import data.UserDAO;
import data.UserDaoImpl;
import entities.data.User;
import org.checkerframework.checker.fenum.qual.SwingTextOrientation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import services.AuthenticationService;

public class AuthServiceTest {
    UserDAO userDAO = new UserDaoImpl();
    AuthenticationService authService = new AuthenticationService(userDAO);

    @Test
    public void login_wrong_password() {
        User testUser = authService.login("cbuckley","test");

        Assertions.assertEquals(null, testUser);

    }

    @Test
    public void login_invalid_username() {
        User testUser = authService.login("asdfasdfkjn","test");

        Assertions.assertEquals(null, testUser);
    }

    @Test
    public void username_taken() {
        User newUser = new User();

        newUser.setFirstName("test");
        newUser.setLastName("test");
        newUser.setUsername("cbuckley");
        newUser.setPassword("password");
        newUser.setEmail("email");

        User createdUser = authService.register(newUser);

        Assertions.assertEquals(null, createdUser);
    }

}
