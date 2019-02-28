package realestate.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import realestate.domain.entities.Offer;
import realestate.domain.models.binding.OfferFindBindingModel;
import realestate.domain.models.service.OfferServiceModel;
import realestate.repository.OfferRepository;
import realestate.util.Constants;

import javax.validation.Validator;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {
    
    private final OfferRepository offerRepository;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, Validator validator, ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerOffer(OfferServiceModel offerServiceModel) {
        if(this.validator.validate(offerServiceModel).size() != 0){
            throw new IllegalArgumentException(Constants.MESSAGE_VALIDATION_ERROR);
        }
        
        this.offerRepository.saveAndFlush(this.modelMapper.map(offerServiceModel, Offer.class));
        
    }

    @Override
    public List<OfferServiceModel> findAllOffers() {
        return this.offerRepository
                .findAll()
                .stream()
                .map(o -> this.modelMapper.map(o, OfferServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public void findOffer(OfferFindBindingModel offerFindBindingModel) {
        if (this.validator.validate(offerFindBindingModel).size() != 0) {
            throw new IllegalArgumentException("Invalid data");
        }
        Offer offer = this.findAllOffers()
                .stream()
                .filter(o -> o.getApartmentType().equalsIgnoreCase(offerFindBindingModel.getFamilyApartmentType()) &&
                        o.getApartmentRent().add(o.getApartmentRent().multiply(o.getAgencyCommission().divide(new BigDecimal(100), RoundingMode.CEILING)))
                                .compareTo(offerFindBindingModel.getFamilyBudget()) < 0)
                .findFirst()
                .map(o -> this.modelMapper.map(o, Offer.class))
                .orElse(null);
        if (offer == null) {
            throw new IllegalArgumentException("Invalid data");
        }
        this.offerRepository.delete(offer);
    
    }
}
