package com.publicis.creditcardprovider.controller;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import com.publicis.creditcardprovider.dto.CreditCardProviderResponse;
import com.publicis.creditcardprovider.dto.ErrorMessage;
import com.publicis.creditcardprovider.exception.CreditCardProviderException;
import com.publicis.creditcardprovider.model.CreditCard;
import com.publicis.creditcardprovider.service.CreditCardProviderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@Slf4j
public class CreditCardProviderController {

    private final CreditCardProviderService creditCardProviderService;

    public CreditCardProviderController(CreditCardProviderService creditCardProviderService) {
        this.creditCardProviderService = creditCardProviderService;
    }

    @ApiOperation(value = "Add Credit Card", nickname = "AddCreditCard", response = CreditCardProviderResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Add Credit Card", response = CreditCardProviderResponse.class),
            @ApiResponse(code = 400, message = "Bad request", response = ErrorMessage.class),
            @ApiResponse(code = 500, message = "Internal Server Error")})
    @PostMapping(value = "/Add",
            produces = {"application/json; charset=utf-8"},
            consumes = {"application/json; charset=utf-8"})
    public ResponseEntity<CreditCardProviderResponse> addCreditCard(@Valid @RequestBody CreditCardProviderRequest creditCardProviderRequest) throws CreditCardProviderException {
        log.info("Request: Add Credit Card");
        CreditCardProviderResponse creditCardProviderResponse = creditCardProviderService.addCreditCard(creditCardProviderRequest);
        return new ResponseEntity<>(creditCardProviderResponse, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Get All Credit Cards", nickname = "getAllCreditCards")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Get All Credit Cards")})
    @GetMapping(value = "/Get all",
            produces = {"application/json; charset=utf-8"})
    public ResponseEntity<List<CreditCard>> getAllCreditCards() {
        log.info("Request: get All Credit Cards");
        List<CreditCard> creditCardList = creditCardProviderService.getCreditCards();
        return new ResponseEntity<>(creditCardList, HttpStatus.OK);
    }

}
