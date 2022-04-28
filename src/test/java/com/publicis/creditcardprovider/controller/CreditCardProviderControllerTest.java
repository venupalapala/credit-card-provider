package com.publicis.creditcardprovider.controller;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import com.publicis.creditcardprovider.dto.CreditCardProviderResponse;
import com.publicis.creditcardprovider.exception.CreditCardProviderException;
import com.publicis.creditcardprovider.model.CreditCard;
import com.publicis.creditcardprovider.service.CreditCardProviderService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardProviderControllerTest {

    @InjectMocks
    private CreditCardProviderController creditCardProviderController;
    @Mock
    private CreditCardProviderService creditCardProviderService;

    private CreditCardProviderRequest creditCardProviderRequest;

    private CreditCardProviderResponse creditCardProviderResponse;

    private List<CreditCard> creditCards;

    @Before
    public void setUp() {
        creditCardProviderRequest = CreditCardProviderRequest.builder().name("venu").cardNumber("4444 3333 2222 1111").limit(new BigDecimal(900)).build();
    }

    @Test
    public void shouldReturn201Response_whenValidRequestIsSent() throws CreditCardProviderException {
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        ResponseEntity<CreditCardProviderResponse> response = creditCardProviderController.addCreditCard(creditCardProviderRequest);
        Assert.assertTrue(response.getStatusCode()== HttpStatus.CREATED);
        Assert.assertNotNull(response);
    }

    @Test
    public void shouldReturn200ResponseWithEmptyList_whenNoCreditCardsArePresent() {
        creditCards = new ArrayList<>();
        when(creditCardProviderService.getCreditCards()).thenReturn(creditCards);
        ResponseEntity<List<CreditCard>> response = creditCardProviderController.getAllCreditCards();
        Assert.assertTrue(response.getStatusCode()== HttpStatus.OK);
        Assert.assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void shouldReturn20OResponseWithTwoCreditCards_whenTwoCreditCardsArePresent() {
        creditCards = new ArrayList<>();
        creditCards.add(CreditCard.builder().name("venu").cardNumber("4444 3333 2222 1111").creditLimit(new BigDecimal(100)).build());
        creditCards.add(CreditCard.builder().name("gopal").cardNumber("1111 2222 3333 4444").creditLimit(new BigDecimal(200)).build());
        when(creditCardProviderService.getCreditCards()).thenReturn(creditCards);
        ResponseEntity<List<CreditCard>> response = creditCardProviderController.getAllCreditCards();
        Assert.assertTrue(response.getStatusCode()== HttpStatus.OK);
        Assert.assertEquals(2, response.getBody().size());
    }

}