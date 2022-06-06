package utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import data.UserDAO;
import data.UserDaoImpl;
import services.UserService;
import services.UserServiceImpl;
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

        UserService userService = new UserServiceImpl(new UserDaoImpl());
        ObjectMapper mapper = new ObjectMapper();

        UserServlet userServlet = new UserServlet(mapper, userService);

        ServletContext context = sce.getServletContext();

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
