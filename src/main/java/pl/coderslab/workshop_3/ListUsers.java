package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "ListUsers", value = "/user/list")
public class ListUsers extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search"); //parametr search (przekazywać przy usuń i przejściu na stronę)
        String pageStr = request.getParameter("page"); //parametr page (przekazywać przy usuń)

        int resultCount = 0;
        int page = 0;

        resultCount = (search == null || search.trim().isEmpty()) ? UserDAO.getCount() : UserDAO.getCount(search);

        if (pageStr == null || !pageStr.matches("[1-9][0-9]*") || Integer.parseInt(pageStr) > ((resultCount - 1) / 10 + 1)) {
            page = 1;
        } else {
            page = Integer.parseInt(pageStr);
        }
        request.setAttribute("page", page); //zwrot wartości page do widoku
        request.setAttribute("maxpage", (resultCount - 1) / 10 + 1); //zwrot ilości zakładek do widoku

        if (search == null || search.trim().isEmpty()) {
            request.setAttribute("list", UserDAO.findAll((page - 1) * 10, 10));   //zwrot listy user
        } else {
            request.setAttribute("list", UserDAO.findAll(search, (page - 1) * 10, 10)); //zwrot listy user
        }
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String search = request.getParameter("search");
        response.sendRedirect("/user/list" + (search != null && !search.trim().isEmpty() ? "?search=" + URLEncoder.encode(search, "UTF-8") : ""));


    }
}
