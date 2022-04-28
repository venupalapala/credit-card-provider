package com.publicis.creditcardprovider.controller;


import com.publicis.creditcardprovider.dto.CreditCardProviderResponse;
import com.publicis.creditcardprovider.model.CreditCard;
import com.publicis.creditcardprovider.service.CreditCardProviderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class CreditCardProviderControllerMockMvcTest {

    private MockMvc mockMvc;

    @Mock
    private CreditCardProviderService creditCardProviderService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(new CreditCardProviderController(creditCardProviderService)).build();
    }

    @Test
    public void shouldReturnSuccessResponse_whenGetAllEndPointIsCalled() throws Exception {
        List<CreditCard> creditCards = new ArrayList<>();
        creditCards.add(CreditCard.builder().name("venu").cardNumber("4111 1111 1111 1111").creditLimit(new BigDecimal(100)).build());
        creditCards.add(CreditCard.builder().name("gopal").cardNumber("5500 0000 0000 0004").creditLimit(new BigDecimal(200)).build());

        when(creditCardProviderService.getCreditCards()).thenReturn(creditCards);
        this.mockMvc.perform(get("/Get all")).andExpect(status().isOk());
    }

    @Test
    public void shouldReturn201SuccessResponse_whenAddEndPointIsCalledWithValidRequest() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"4111 1111 1111 1111\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isCreated());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithCardNumberAsCharacters() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"abc\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest))
                .andExpect(status().isBadRequest());
         }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithCardNumberGreaterThan19() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"1234 5678 9123 4567 8912\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithCardNumberEmpty() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithInvalidFormat() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"\",");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithNullName() throws Exception {
        String creditCardProviderRequest = String.format("{\"cardNumber\":\"4111 1111 1111 1111\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithNullCardNumber() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithNullLimit() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"4111 1111 1111 1111\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithZeroLimit() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"4111 1111 1111 1111\",\"limit\":\"0\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithEmptyName() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"\",\"cardNumber\":\"4111 1111 1111 1111\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }

    @Test
    public void shouldReturn400FailureResponse_whenAddEndPointIsCalledWithEmptyCardNumber() throws Exception {
        String creditCardProviderRequest = String.format("{\"name\":\"Venu\",\"cardNumber\":\"\",\"limit\":\"200\"}");
        CreditCardProviderResponse creditCardProviderResponse = CreditCardProviderResponse.builder().build();
        when(creditCardProviderService.addCreditCard(any())).thenReturn(creditCardProviderResponse);
        this.mockMvc.perform(post("/Add").contentType(MediaType.APPLICATION_JSON).content(creditCardProviderRequest)).andExpect(status().isBadRequest());
    }
}
