package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "Add", value = "/user/add")
public class Add extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        getServletContext().getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");

        boolean isError = false;

        String error = new String();

        if (name == null || !name.trim().matches("[a-zA-ZĄ-ćęĘŁ-ńÓóŚśŹ-ż][a-zA-ZĄ-ćęĘŁ-ńÓóŚśŹ-ż0-9\\-_]{2,253}[a-zA-ZĄ-ćęĘŁ-ńÓóŚśŹ-ż0-9]")) {
            isError = true;
            error += "name";
        } else {
            name = name.trim();
        }
        if (email == null || !email.trim().matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}") || email.trim().length() > 255) {
            isError = true;
            error += "emailInvalid";
        } else {
            email = email.trim();
            if (!User.isEmailUnique(email)) {
                isError = true;
                error += "emailExists";
            }
        }
        if (password1 == null || password1.matches(".*\\s.*") || password1.length() < 8) {
            isError = true;
            error += "passwordInvalid";
            System.out.println(password1.matches(".*\\s.*"));
        } else {
            if (password2 == null || !password1.equals(password2)) {
                isError = true;
                error += "passwordsDontMatch";
            }
        }
        if (!isError) {

            User user = new User(email, name, password1);
            String msg = new String();
            if (user.getId() == -1) {
                msg = "addFail";  //msg ma decydować o dodatkowej informacji wyświetlanej w pliku widoku
            } else {
                msg = "addSuccess";
            }
            String fromPage = request.getParameter("fromPage");
            if (fromPage == null || fromPage.isEmpty()) {
                fromPage = "1";
            }
            String search = request.getParameter("search");
            response.sendRedirect("/user/list?page=" + URLEncoder.encode(fromPage) + (search != null ? "&search=" + URLEncoder.encode(search) : "") + "&msg=" + msg);
        } else {
            request.setAttribute("error", error);     //do value w widoku
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("password1", password1);
            request.setAttribute("password2", password2);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/add.jsp").forward(request, response);
        }


    }
}
