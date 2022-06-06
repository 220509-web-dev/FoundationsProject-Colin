package app;

import data.UserDaoImpl;
import entities.data.User;
import services.UserService;
import services.UserServiceImpl;
import utilities.ConnectionUtil;
import utilities.LogLevel;
import utilities.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    //static UserService userService = new UserServiceImpl(new UserDaoImpl());

    public static void main(String[] args) {

        UserService userService = new UserServiceImpl(new UserDaoImpl());
        List<User> userList = userService.getUsers();
        System.out.println(userList.get(0));
        //System.exit(0);
    }

}
