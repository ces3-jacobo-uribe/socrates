package co.edu.poli.ces3.socrates.socrates;

import java.io.*;
import java.util.List;

import co.edu.poli.ces3.socrates.socrates.dao.User;
import co.edu.poli.ces3.socrates.socrates.services.UserService;
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    private UserService userService = new UserService();
    private Gson gson = new Gson();

    public void init() {
        System.out.println("Hello Servlet up");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();
        List<User> users = userService.getUsers();

        out.print(gson.toJson(users));
    }

    public void destroy() {
    }
}