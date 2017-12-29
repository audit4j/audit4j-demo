package org.audit4j.demo.http;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.audit4j.core.annotation.Audit;

/**
 * Servlet implementation class LoginServlet
 */
@Audit
@WebServlet(description = "Login Servlet", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private UserService userService;

    public LoginServlet() {
        userService = new UserService();
    }

    public void init() throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("---------------------------");
       System.out.println(getServletContext().getRealPath("/LoginServlet.class"));
       System.out.println(System.getProperty("java.class.path"));

        // get request parameters for userID and password
        String user = request.getParameter("user");
        String pwd = request.getParameter("pwd");

        log("User=" + user + "::password=" + pwd);

        if (userService.login(user, pwd)) {
            response.sendRedirect("LoginSuccess.jsp");
        } else {
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
            PrintWriter out = response.getWriter();
            out.println("<font color=red>Either user name or password is wrong.</font>");
            rd.include(request, response);

        }

    }

}
