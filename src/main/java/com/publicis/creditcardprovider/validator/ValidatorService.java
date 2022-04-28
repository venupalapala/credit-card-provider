package com.publicis.creditcardprovider.validator;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidatorService {
    private List<Validator> validatorList;

    public ValidatorService(List<Validator> validatorList) {
        this.validatorList = new ArrayList<>(validatorList);
    }

    public ValidationResult validateAll(CreditCardProviderRequest requestBean) {
        for (Validator rule : validatorList) {
            ValidationResult validationResult = rule.validate(requestBean);
            if (!validationResult.isValid()) {
                return validationResult;
            }
        }
        return ValidationResult.valid();
    }
}
