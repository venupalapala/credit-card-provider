package com.publicis.creditcardprovider.service;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import com.publicis.creditcardprovider.dto.CreditCardProviderResponse;
import com.publicis.creditcardprovider.exception.CreditCardProviderException;
import com.publicis.creditcardprovider.model.CreditCard;
import com.publicis.creditcardprovider.repository.CreditCardProviderRepository;
import com.publicis.creditcardprovider.util.CreditCardBuilder;
import com.publicis.creditcardprovider.validator.ValidationResult;
import com.publicis.creditcardprovider.validator.ValidatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CreditCardProviderService {

    @Autowired
    private CreditCardProviderRepository creditCardProviderRepository;

    @Autowired
    private ValidatorService validatorService;

    public CreditCardProviderResponse addCreditCard(CreditCardProviderRequest creditCardProviderRequest) throws CreditCardProviderException {
        log.info("Entered CreditCardProviderService : AddCreditCard " );
        ValidationResult validationResult = validatorService.validateAll(creditCardProviderRequest);
        if (!validationResult.isValid()) {
            throw new CreditCardProviderException(validationResult.getValidateMsg());
        }
        CreditCard creditCard = CreditCardBuilder.buildRequest(creditCardProviderRequest);
        CreditCard creditCardResponse = creditCardProviderRepository.save(creditCard);
        log.info("Exited CreditCardProviderService : AddCreditCard " );
        return CreditCardBuilder.buildResponse(creditCardResponse);
    }

    public List<CreditCard> getCreditCards() {
        log.info("Entered CreditCardProviderService : getCreditCards " );
        return creditCardProviderRepository.findAll();
    }
}
