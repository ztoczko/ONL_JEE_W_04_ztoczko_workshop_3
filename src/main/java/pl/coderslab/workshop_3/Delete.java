package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "Delete", value = "/user/delete")
public class Delete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String page = request.getParameter("fromPage");
        String search = request.getParameter("search");

        if (id == null || !id.matches("\\d+") || UserDAO.read(Integer.parseInt(id)) == null) {

            response.sendRedirect("/user/list?page=" + URLEncoder.encode(page) + (search != null ? "&search=" + URLEncoder.encode(search) : "") + "&msg=deleteIdInvalid");
        } else {
            String msg = UserDAO.delete(Integer.parseInt(id)) ? "deleteSuccess" : "deleteFail";
            response.sendRedirect("/user/list?page=" + URLEncoder.encode(page) + (search != null ? "&search=" + URLEncoder.encode(search) : "") + "&msg=" + msg);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
