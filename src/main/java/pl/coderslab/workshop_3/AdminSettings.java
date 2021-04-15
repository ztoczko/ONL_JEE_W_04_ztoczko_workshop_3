package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "AdminSettings", value = "/user/adminSettings")
public class AdminSettings extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("admin", (User) request.getSession().getAttribute("user"));
        getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminSettings.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        User admin = (User) request.getSession().getAttribute("user");

        boolean isError = false;

        boolean isEmailEdited = (email == null || email.trim().isEmpty()) ? false : true;
        boolean isPasswordEdited = ((password1 == null || password1.trim().isEmpty()) && (password2 == null || password2.trim().isEmpty())) ? false : true;

        String error = new String();

        if (isEmailEdited && (!email.trim().matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}") || email.length() > 255)) {
            isError = true;
            error += "emailInvalid";
        } else {
            email = email.trim();
        }
        if (isPasswordEdited && (password1 == null || password1.matches(".*\\s.*") || password1.length() < 8)) {
            isError = true;
            error += "passwordInvalid";
        } else {
            if (isPasswordEdited && (password2 == null || !password1.equals(password2))) {
                isError = true;
                error += "passwordsDontMatch";
            }
        }
        if (!isError) {

            String msg = new String();

            if (isEmailEdited) {
                admin.setAdminEmail(email);
            }
            if (isPasswordEdited) {
                admin.setPassword(password1);
            }

            if (UserDAO.updateAdmin(admin)) {
                msg = "editSuccess";  //msg ma decydować o dodatkowej informacji wyświetlanej w pliku widoku
            } else {
                msg = "editFail";
            }

            String fromPage = request.getParameter("fromPage");
            if (fromPage == null || fromPage.isEmpty()) {
                fromPage = "1";
            }
            String search = request.getParameter("search");
            response.sendRedirect("/user/list?page=" + URLEncoder.encode(fromPage) + (search != null ? "&search=" + URLEncoder.encode(search) : "") + "&msg=" + msg);
        } else {
            request.setAttribute("admin", admin);
            request.setAttribute("error", error);     //do value w widoku
            request.setAttribute("email", email);
            request.setAttribute("password1", password1);
            request.setAttribute("password2", password2);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/adminSettings.jsp").forward(request, response);
        }
    }
}
