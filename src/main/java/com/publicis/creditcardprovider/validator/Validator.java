package com.publicis.creditcardprovider.validator;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;

public interface Validator {
    ValidationResult validate(CreditCardProviderRequest requestBean);
}
