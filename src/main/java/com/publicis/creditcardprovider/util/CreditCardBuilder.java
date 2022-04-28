package com.publicis.creditcardprovider.util;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import com.publicis.creditcardprovider.dto.CreditCardProviderResponse;
import com.publicis.creditcardprovider.model.CreditCard;

import java.math.BigDecimal;

public class CreditCardBuilder {

    public static CreditCard buildRequest(CreditCardProviderRequest creditCardProviderRequest) {
        return CreditCard.builder().name(creditCardProviderRequest.getName()).
                cardNumber(creditCardProviderRequest.getCardNumber()).creditLimit(creditCardProviderRequest.getLimit()).balance(BigDecimal.ZERO).build();
    }

    public static CreditCardProviderResponse buildResponse(CreditCard request) {
        return CreditCardProviderResponse.builder().cardNumber(request.getCardNumber()).name(request.getName()).limit(request.getCreditLimit()).balance(request.getBalance()).build();
    }
}
