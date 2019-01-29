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

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
    
    public static final String CAT_PROFILE_HTML_FILE_PATH = "D:\\JavaSoftUniProjects\\JavaWeb\\FDMC\\src\\main\\resources\\views\\cat-profile.html";
    
    private final HtmlReader htmlReader;

    @Inject
    public CatProfileServlet(HtmlReader htmlReader) {
        this.htmlReader = htmlReader;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String catName = req.getQueryString().split("=")[1];
        Cat cat = ((Map<String, Cat>)req.getSession().getAttribute("cats"))
                .get(catName);
        
        String html;
        
        if(cat == null){
            html = String.format("<h1>Cat, with name - %s was not found.</h1></br><a href=\"cats/all\">Back</a>",catName);
        } else {
            html = this.htmlReader
                    .readHtmlFile(CAT_PROFILE_HTML_FILE_PATH)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{catAge}}", cat.getAge().toString());
        }
        
        resp.getWriter().println(html);
    }
}
