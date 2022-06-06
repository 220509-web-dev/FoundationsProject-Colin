package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.UserDAO;
import data.UserDaoImpl;
import entities.data.User;
import services.UserService;
import services.UserServiceImpl;
import utilities.LogLevel;
import utilities.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class UserServlet extends HttpServlet {

    UserService userService;
    ObjectMapper mapper;

    private UserServlet() {

    }

    public UserServlet(ObjectMapper mapper, UserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @Override
    public void init() throws ServletException {
        Logger.Log(LogLevel.INFO, "User Servlet started");
    }

    // small db no performance issue using select * then filter...
    // if larger db would use query to get result instead of list filter
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // generate user list
        List<User> userList = userService.getUsers();

        // get username param
        String username = req.getParameter("username");

        // get user id param
        // filter based off id
        try {
            int userId = Integer.parseInt(req.getParameter("id"));
            userList = userList.stream().filter(user -> user.getId() == userId).collect(Collectors.toList());

        } catch (NumberFormatException e) {
            Logger.Log(LogLevel.INFO, "null or invalid input", "UserServlet.doGet");
        }

        // filter userList based on username
        if (username != null) {
            userList = userList.stream().filter(user -> user.getUsername().equals(username)).collect(Collectors.toList());
        }

        // set response
        String result = mapper.writeValueAsString(userList);
        resp.setContentType("application/json");
        resp.getWriter().write(result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
}
