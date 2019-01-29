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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {
    
    private static final String CREATE_CAT_HTML_FILE_PATH = "D:\\JavaSoftUniProjects\\JavaWeb\\FDMC\\src\\main\\resources\\views\\create-cat.html";
    
    private final HtmlReader reader;

    @Inject
    public CatCreateServlet(HtmlReader reader) {
        this.reader = reader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(this.reader.readHtmlFile(CREATE_CAT_HTML_FILE_PATH));
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameCat = req.getParameter("name");
        String breedCat = req.getParameter("breed");
        String colorCat= req.getParameter("color");
        int age = Integer.parseInt(req.getParameter("age"));
        
        Cat cat = new Cat(nameCat, breedCat, colorCat, age);
        
        if(req.getSession().getAttribute("cats") == null){
            req.getSession().setAttribute("cats", new LinkedHashMap<String, Cat>());
        }
        
        ((Map<String, Cat>)req.getSession().getAttribute("cats"))
                .putIfAbsent(nameCat, cat);
        
        resp.sendRedirect(String.format("/cats/profile?catName=%s",nameCat));
    }
}
