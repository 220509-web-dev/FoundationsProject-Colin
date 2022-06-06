package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.UserDAO;
import data.UserDaoImpl;
import services.AuthenticationService;
import services.UserService;
import services.UserServiceImpl;
import servlet.AuthenticationServlet;
import servlet.UserServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRegistration;

public class ContextLoaderListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //ServletContextListener.super.contextInitialized(sce);
        System.out.println("The servlet context was created");
        Logger.Log(LogLevel.INFO, "The servlet context was created");

        // DAO
        UserDAO userDAO = new UserDaoImpl();

        // Services
        UserService userService = new UserServiceImpl(userDAO);
        AuthenticationService authService = new AuthenticationService(userDAO);

        // util
        ObjectMapper mapper = new ObjectMapper();

        // servlets
        UserServlet userServlet = new UserServlet(mapper, userService);
        AuthenticationServlet authServlet = new AuthenticationServlet(mapper, authService);

        ServletContext context = sce.getServletContext();

        context.addServlet("AuthenticationServlet", authServlet).addMapping("/auth/*");

        ServletRegistration.Dynamic registeredServlet = context.addServlet("UserServlet", userServlet);
        registeredServlet.setInitParameter("username","username-value");
        registeredServlet.addMapping("/user/*");

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        //ServletContextListener.super.contextDestroyed(sce);
        System.out.println("The servlet context was destroyed");
        Logger.Log(LogLevel.INFO, "The servlet context was destroyed");
    }
}
