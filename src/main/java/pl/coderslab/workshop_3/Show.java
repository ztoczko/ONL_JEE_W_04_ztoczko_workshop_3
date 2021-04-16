package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "Show", value = "/user/show")
public class Show extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id == null || !id.matches("\\d+") || UserDAO.read(Integer.parseInt(id)) == null) {
            String page = request.getParameter("fromPage");
            if (page == null || page.isEmpty()) {
                page = "1";
            }
            String search = request.getParameter("search");

            response.sendRedirect("/user/list?page=" + URLEncoder.encode(page) + (search != null ? "&search=" + URLEncoder.encode(search, "UTF-8") : "") + "&msg=showIdInvalid");
        } else {
            request.setAttribute("user", UserDAO.read(Integer.parseInt(id)));
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/show.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
