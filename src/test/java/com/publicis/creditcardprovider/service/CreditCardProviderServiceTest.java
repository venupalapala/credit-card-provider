package com.publicis.creditcardprovider.service;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import com.publicis.creditcardprovider.dto.CreditCardProviderResponse;
import com.publicis.creditcardprovider.exception.CreditCardProviderException;
import com.publicis.creditcardprovider.model.CreditCard;
import com.publicis.creditcardprovider.repository.CreditCardProviderRepository;
import com.publicis.creditcardprovider.validator.ValidationResult;
import com.publicis.creditcardprovider.validator.ValidatorService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardProviderServiceTest {
    @InjectMocks
    private CreditCardProviderService creditCardProviderService;

    @Mock
    private ValidatorService validatorService;

    @Mock
    private CreditCardProviderRepository creditCardProviderRepository;

    private CreditCard creditCard;

    private List<CreditCard> creditCardDetails;

    private CreditCardProviderRequest creditCardProviderRequest;

    @Before
    public void setUp() {
        creditCardProviderRequest = CreditCardProviderRequest.builder().name("Venu").cardNumber("4111 1111 1111 1111").limit(new BigDecimal(800)).build();
    }

    @Test
    public void shouldReturnNonNullResponse_whenValidRequestIsReceived() throws CreditCardProviderException {
        creditCard = CreditCard.builder().name("Venu").cardNumber("4111 1111 1111 1111").creditLimit(new BigDecimal(800)).balance(new BigDecimal(0)).build();
        when(validatorService.validateAll(any())).thenReturn(ValidationResult.valid());
        when(creditCardProviderRepository.save(any())).thenReturn(creditCard);
        CreditCardProviderResponse response = creditCardProviderService.addCreditCard(creditCardProviderRequest);
        Assert.assertNotNull(response);
    }

    @Test
    public void shouldReturnNonNullResponseList_whenRepositoryHasCreditCards() {
        creditCardDetails = new ArrayList<>();
        creditCard = CreditCard.builder().name("Gopal").cardNumber("4111 1111 1111 1111").creditLimit(new BigDecimal(400)).balance(new BigDecimal(0)).build();
        creditCardDetails.add(creditCard);
        when(creditCardProviderRepository.findAll()).thenReturn(creditCardDetails);
        List<CreditCard> response = creditCardProviderService.getCreditCards();
        Assert.assertNotNull(response);
        Assert.assertEquals(1, response.size());
    }

    @Test
    public void shouldReturnEmptyResponseList_whenRepositoryDoesNotHaveCreditCards() {

        when(creditCardProviderRepository.findAll()).thenReturn(new ArrayList<>());
        List<CreditCard> response = creditCardProviderService.getCreditCards();
        Assert.assertNotNull(response);
        Assert.assertEquals(0, response.size());
    }

    @Test(expected = CreditCardProviderException.class)
    public void shouldThrowException_whenRequestWithInvalidCreditCardNumberIsReceived() throws CreditCardProviderException {
        when(validatorService.validateAll(any())).thenReturn(ValidationResult.inValid("Invalid CreditCardNumber Format"));
        CreditCardProviderResponse response = creditCardProviderService.addCreditCard(creditCardProviderRequest);
        Assert.assertNotNull(response);
    }


}