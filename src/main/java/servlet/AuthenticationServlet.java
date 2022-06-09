package servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.data.User;
import entities.utilities.ResponseObject;
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
        String result;

        if (path.equals("/auth/login")) {
            User getUser = auth.login(reqUser.getUsername(),reqUser.getPassword());
            // set response
            resp.setContentType("application/json");
            if (getUser != null) {
                result = mapper.writeValueAsString(getUser);
            } else {
                ResponseObject ro = new ResponseObject("message:invalid username + password");
                result = mapper.writeValueAsString(ro);
                resp.setStatus(400);
            }
        } else if (path.equals("/auth/register")) {
            User newUser = auth.register(reqUser);
            resp.setContentType("application/json");
            if (newUser != null) {
                result = mapper.writeValueAsString(newUser);
            } else {
                ResponseObject ro = new ResponseObject("username already exists/invalid input");
                result = mapper.writeValueAsString(ro);
                resp.setStatus(400);
            }
        } else {
            // invalid endpoint
            ResponseObject ro = new ResponseObject("error");
            result = mapper.writeValueAsString(ro);
            resp.setStatus(400);
        }

        resp.getWriter().write(result);

    }
}
