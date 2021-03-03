package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * 输出 “Hello,World” Controller
 */
@Path("/hello")
public class HelloWorldController implements PageController {

    UserService userService;

    public HelloWorldController() {
        userService = new UserServiceImpl();
    }

    @GET
    @POST
    @Path("/world") // /hello/world -> HelloWorldController
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "index.jsp";
    }

    @GET
    @Path("/login")
    public String toLogin(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "login-form.jsp";
    }

    @POST
    @Path("/loginPost")
    public String loginPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        User user = userService.queryUserByNameAndPassword(request.getParameter("email"),request.getParameter("password"));
        user.getEmail(); //will fail if not exist
        return "login-form.jsp";
    }

    @GET
    @Path("/register")
    public String toRegister(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        return "register-form.jsp";
    }

    @POST
    @Path("/registerPost")
    public String registerPost(HttpServletRequest request, HttpServletResponse response) throws Throwable {
        User user = new User();
        user.setName(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setEmail(request.getParameter("email"));
        user.setPhoneNumber(request.getParameter("phoneNumber"));
        userService.register(user);
        return "login-form.jsp";
    }

}
