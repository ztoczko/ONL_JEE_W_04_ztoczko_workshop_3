package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "FilterEncoding", value = "/*")
public class FilterEncoding implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        if (!((HttpServletRequest) request).getRequestURI().matches(".+\\..{2,4}")) {
            response.setContentType("text/html");
        }

        chain.doFilter(request, response);
    }
}
