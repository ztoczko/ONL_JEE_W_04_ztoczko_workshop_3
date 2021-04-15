package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "FilterAuth", value = "/user/*")
public class FilterAuth implements Filter {
    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException {

        if (((HttpServletRequest) request).getSession().getAttribute("user") == null) {
            ((HttpServletResponse) response).sendRedirect("/login");
        } else {
            chain.doFilter(request, response);
        }

    }
}
