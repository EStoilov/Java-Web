package realestate.web.controlers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import realestate.domain.models.view.OfferViewModel;
import realestate.service.OfferService;
import realestate.util.HtmlReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HomeController {
    
    private final OfferService offerService;
    private final HtmlReader htmlReader;
    private final ModelMapper modelMapper;

    @Autowired
    public HomeController(OfferService offerService, HtmlReader htmlReader, ModelMapper modelMapper) {
        this.offerService = offerService;
        this.htmlReader = htmlReader;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() throws IOException {
        return this.prepareView();
    }  
    
    public String prepareView() throws IOException {
        List<OfferViewModel> offers = this.offerService
                .findAllOffers()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferViewModel.class))
                .collect(Collectors.toList());
        
        StringBuilder offersHtml = new StringBuilder();
        
        if (offers.size() == 0){
             offersHtml.append("<div class=\"apartment\" style=\"border: red solid: 1px\">")
                     .append("There aren't any offers")
                     .append("</div>") ;
        } else {
            for (OfferViewModel offer : offers) {
                
                offersHtml.append(" <div class=\"apartment\">")
                        .append("<p>Rent: " + offer.getApartmentRent() + "</p>")
                        .append("<p>Type: " + offer.getApartmentType() + "</p>")
                        .append("<p>Commission: " + offer.getAgencyCommission() + "</p>")
                        .append("</div>");
            }
        }
        return this.htmlReader.readHtmlFile("D:\\JavaSoftUniProjects\\JavaWeb\\Spring Projects\\Realestate\\src\\main\\resources\\static\\index.html")
                .replace("{{offers}}", offersHtml.toString());
    }
    
}
