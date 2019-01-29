package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/all")
public class CatsServlet extends HttpServlet {
    
    private static final String ALL_CATS_HTML_FILE_PATH = "D:\\JavaSoftUniProjects\\JavaWeb\\FDMC\\src\\main\\resources\\views\\all-cats.html";
    
    private final HtmlReader htmlReader;

    @Inject
    public CatsServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, Cat> cats = (Map<String, Cat>) req.getSession().getAttribute("cats");
        String html = this.htmlReader.readHtmlFile(ALL_CATS_HTML_FILE_PATH);
        
        if(cats.isEmpty()){
            html = html.replace("{{content}}", "There are no cats. <a href=\"/cats/create\">Create some!</a>");
        } else {
            StringBuilder content = new StringBuilder();

            for (String catName : cats.keySet()) {
               content.append(String.format("<p>%s</p>",catName));
            }

            html = html.replace("{{content}}", content.toString());
        }
        
        resp.getWriter().println(html);
        
    }
}
