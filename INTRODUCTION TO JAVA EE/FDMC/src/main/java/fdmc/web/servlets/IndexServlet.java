package fdmc.web.servlets;

import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet("/")
public class IndexServlet extends HttpServlet {
    public static final String INDEX_HTML_PATH = "D:\\JavaSoftUniProjects\\JavaWeb\\FDMC\\src\\main\\resources\\views\\index.html";

    private final HtmlReader reader;

    @Inject
    public IndexServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter writer = resp.getWriter();
        writer.println(this.reader.readHtmlFile(INDEX_HTML_PATH));
    }
}
