package pl.coderslab.workshop_3;

import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "Login", value = "/login")
public class Login extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("user") != null) {
            response.sendRedirect("/user/list");
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String password = request.getParameter("password");
        boolean isLoginSuccessful = true;

        if (name == null || password == null) {
            isLoginSuccessful = false;
        } else {
            User admin = UserDAO.readAdmin(name);
            if (admin == null) {
                isLoginSuccessful = false;
            } else {
                isLoginSuccessful = BCrypt.checkpw(password, admin.getPassword());
            }
        }
        if (isLoginSuccessful) {
            request.getSession().setAttribute("user", UserDAO.readAdmin(name));
            response.sendRedirect("/user/list");
        } else {
            request.setAttribute("error", "error");
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
        }

    }
}
