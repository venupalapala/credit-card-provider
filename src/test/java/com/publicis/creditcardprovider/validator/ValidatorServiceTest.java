package com.publicis.creditcardprovider.validator;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class ValidatorServiceTest {

    private ValidatorService validatorService;

    private CreditCardProviderRequest creditCardProviderRequest;

    @Before
    public void setUp(){
        List<Validator> validatorList = Arrays.asList(new CreditCardNumberValidator());
        validatorService = new ValidatorService(validatorList);
    }

    @Test
    public void shouldReturnValidResult_whenCreditCardNumberIsValid(){
        creditCardProviderRequest = CreditCardProviderRequest.builder().name("Venu").cardNumber("4444 3333 2222 1111").limit(new BigDecimal(800)).build();
        ValidationResult validationResult = validatorService.validateAll(creditCardProviderRequest);
        Assert.assertTrue(validationResult.isValid());
    }

    @Test
    public void shouldReturnInValidResult_whenCreditCardNumberIsInValid(){
        creditCardProviderRequest = CreditCardProviderRequest.builder().name("Venu").cardNumber("1234").limit(new BigDecimal(800)).build();
        ValidationResult validationResult = validatorService.validateAll(creditCardProviderRequest);
        Assert.assertFalse(validationResult.isValid());
    }
}