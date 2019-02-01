package chushka.web.servlets;

import chushka.domain.models.view.AllProductViewModel;
import chushka.services.ProductService;
import chushka.util.HtmlReader;
import chushka.util.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/")
public class IndexServlet extends HttpServlet {
    
    private static final String INDEX_HTML_FILE_PATH = 
            "D:\\JavaSoftUniProjects\\JavaWeb\\chushka\\src\\main\\resources\\views\\index.html";
    
    private final HtmlReader htmlReader;
    private final ProductService productService;
    private final ModelMapper modelMapper;

    @Inject
    public IndexServlet(HtmlReader htmlReader, ProductService productService, ModelMapper modelMapper) {
        this.htmlReader = htmlReader;
        this.productService = productService;
        this.modelMapper = modelMapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        String htmlFileContent = this.htmlReader
                .readHtmlFile(INDEX_HTML_FILE_PATH)
                .replace("{{listProducts}}", this.formatAllProducts());
        resp.getWriter().println(htmlFileContent);
    }
    
    private String formatAllProducts(){

        List<AllProductViewModel> allProducts = this.productService
                .findAllProducts()
                .stream()
                .map(productServiceModel -> this.modelMapper.map(productServiceModel, AllProductViewModel.class))
                .collect(Collectors.toList());
        
        StringBuilder listProduct = new StringBuilder();
        
        allProducts.forEach(product -> {
            listProduct.append(String.format("<li><a href=\"/products/details?name=%s\">%s</li>",product.getName(), product.getName()))
            .append(System.lineSeparator());
        });
        return listProduct.toString().trim();
    }
}
