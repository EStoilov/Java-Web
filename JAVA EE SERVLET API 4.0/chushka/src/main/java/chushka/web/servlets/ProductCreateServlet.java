package chushka.web.servlets;

import chushka.domain.entity.Type;
import chushka.domain.models.service.ProductServiceModel;
import chushka.services.ProductService;
import chushka.util.HtmlReader;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    
    private static final String CREATE_PRODUCT_HTML_FILE_PATH = "D:\\JavaSoftUniProjects\\JavaWeb\\chushka\\src\\main\\resources\\views\\create-product.html";
    
    private final ProductService productService;
    private final HtmlReader htmlReader;

    @Inject
    public ProductCreateServlet(ProductService productService, HtmlReader htmlReader) {
        this.productService = productService;
        this.htmlReader = htmlReader;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuilder options = new StringBuilder();

        Arrays.stream(Type.values()).forEach(type -> {
            options.append(String.format("<option value= \"%s\">%s</option>", type.name(), type.name()))
                    .append(System.lineSeparator());
        });
       
        String htmlFileContent = this.htmlReader
                .readHtmlFile(CREATE_PRODUCT_HTML_FILE_PATH)
                .replace("{{typeOptions}}",options.toString() );
        resp.getWriter().println(htmlFileContent);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceModel productServiceModel = new ProductServiceModel();
        productServiceModel.setName(req.getParameter("name"));
        productServiceModel.setDescription(req.getParameter("description"));
        productServiceModel.setType(req.getParameter("type"));
        
        this.productService.saveProduct(productServiceModel);
        
        resp.sendRedirect("/");
    }
}
