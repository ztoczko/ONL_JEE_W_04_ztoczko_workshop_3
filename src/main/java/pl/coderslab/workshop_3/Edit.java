package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "Edit", value = "/user/edit")
public class Edit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        if (id == null || !id.matches("\\d+") || UserDAO.read(Integer.parseInt(id)) == null) {
            String page = request.getParameter("fromPage");
            String search = request.getParameter("search");

            response.sendRedirect("/user/list?page=" + URLEncoder.encode(page) + (search != null ? "&search=" + URLEncoder.encode(search) : "") + "&msg=editIdInvalid");
        } else {
            request.setAttribute("user", UserDAO.read(Integer.parseInt(id)));
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        String id = request.getParameter("id");         //wysłać przez geta z list

        boolean isError = false;
        boolean isIdValid = (id == null || !id.matches("\\d+") || UserDAO.read(Integer.parseInt(id)) == null) ? false : true;
        boolean isNameEdited = (name == null || name.trim().isEmpty()) ? false : true;
        boolean isEmailEdited = (email == null || email.trim().isEmpty()) ? false : true;
        boolean isPasswordEdited = ((password1 == null || password1.trim().isEmpty()) && (password2 == null || password2.trim().isEmpty())) ? false : true;
        User user = isIdValid ? UserDAO.read(Integer.parseInt(id)) : null;

        String error = new String();

        if (isNameEdited && !name.trim().matches("[a-zA-ZĄ-ćęĘŁ-ńÓóŚśŹ-ż][a-zA-ZĄ-ćęĘŁ-ńÓóŚśŹ-ż0-9\\-_]{2,253}[a-zA-ZĄ-ćęĘŁ-ńÓóŚśŹ-ż0-9]")) {
            isError = true;
            error += "name";
        } else {
            name = name.trim();
        }
        if (isEmailEdited && (!email.trim().matches("[_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.([a-zA-Z]{2,}){1}") || email.length() > 255)) {
            isError = true;
            error += "emailInvalid";
        } else {
            email = email.trim();
            if (!User.isEmailUnique(email) && (!isIdValid || !user.getEmail().equalsIgnoreCase(email))) {
                isError = true;
                error += "emailExists";
            }
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
        if (!isError || !isIdValid) {


            String msg = new String();
            if (!isIdValid) {
                msg = "editIdInvalid";
            } else {

                if (isNameEdited) {
                    user.setUsername(name);
                }
                if (isEmailEdited) {
                    user.setEmail(email);
                }
                if (isPasswordEdited) {
                    user.setPassword(password1);
                }

                if (UserDAO.update(user)) {
                    msg = "editSuccess";  //msg ma decydować o dodatkowej informacji wyświetlanej w pliku widoku
                } else {
                    msg = "editFail";
                }
            }
            String fromPage = request.getParameter("fromPage");
            if (fromPage == null || fromPage.isEmpty()) {
                fromPage = "1";
            }
            String search = request.getParameter("search");
            response.sendRedirect("/user/list?page=" + URLEncoder.encode(fromPage) + (search != null ? "&search=" + URLEncoder.encode(search) : "") + "&msg=" + msg);
        } else {
            request.setAttribute("user", user);
            request.setAttribute("error", error);     //do value w widoku
            request.setAttribute("name", name);
            request.setAttribute("email", email);
            request.setAttribute("password1", password1);
            request.setAttribute("password2", password2);
            getServletContext().getRequestDispatcher("/WEB-INF/jsp/edit.jsp").forward(request, response);
        }
    }
}
