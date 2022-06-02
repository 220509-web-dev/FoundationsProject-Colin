package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.data.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User testUser = new User();
        testUser.setFirstName("Tree");
        testUser.setLastName("Frog");
        testUser.setUsername("TFrog");
        testUser.setPassword("Ribbit!");
        testUser.setEmail("tfrog@ribbit.com");

        System.out.println(testUser);

        String result = mapper.
        resp.setStatus(200);
        resp.setHeader("Context-type", "text/plain");
        resp.getWriter().write("Ribbit!");
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
