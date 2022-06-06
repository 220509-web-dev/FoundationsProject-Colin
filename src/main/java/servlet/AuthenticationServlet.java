package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.data.User;
import services.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationServlet extends HttpServlet {

    AuthenticationService auth;
    ObjectMapper mapper;

    private AuthenticationServlet() {

    }

    public AuthenticationServlet(ObjectMapper mapper, AuthenticationService auth){
        this.mapper = mapper;
        this.auth = auth;
    }

    //basic impl
    //debug later
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User reqUser = mapper.readValue(req.getInputStream(), User.class);
        String path = req.getServletPath();

        if (path.equals("/auth/login")) {
            User getUser = auth.login(reqUser.getUsername(),reqUser.getPassword());
        } else if (path.equals("/auth/register")) {
            User newUser = auth.register(reqUser);
        } else {
            // invalid endpoint
        }

    }
}
