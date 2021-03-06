package samples.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet入门
 *
 * 1. Servlet的生命周期
 *
 * @author Zi Lai Ye
 * @date 2017/4/7
 */
public class HelloWorldServlet extends HttpServlet {

    public static final Logger logger = LoggerFactory.getLogger(HelloWorldServlet.class);

    @Override
    public void init() throws ServletException {
        logger.info("Servlet:{} init method invoked.", this.getClass());
    }

    @Override
    public void destroy() {
        logger.info("Servlet:{} destroy method invoked", this.getClass());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Servlet:{} service method invoked.", this.getClass());
        super.service(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        try {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>HelloWorld servlet</title");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>Hello World.</h2>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    public String getServletInfo() {
        return "HelloWorldServlet";
    }
}
