package pl.coderslab.workshop_3;

import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        ((HttpServletResponse) response).setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
        ((HttpServletResponse) response).setHeader("Pragma", "no-cache"); // HTTP 1.0.
        ((HttpServletResponse) response).setDateHeader("Expires", 0); // Proxies.

        if (!((HttpServletRequest) request).getRequestURI().matches(".+\\..{2,4}")) {
            response.setContentType("text/html");
        }

        chain.doFilter(request, response);
    }
}
