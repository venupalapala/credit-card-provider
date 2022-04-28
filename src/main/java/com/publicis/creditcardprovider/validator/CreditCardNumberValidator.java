package com.publicis.creditcardprovider.validator;

import com.publicis.creditcardprovider.dto.CreditCardProviderRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CreditCardNumberValidator implements Validator {

    @Override
    public ValidationResult validate(CreditCardProviderRequest requestBean) {
        log.info("Validating Credit Card Number");
        String cardNumber = requestBean.getCardNumber();
        cardNumber = cardNumber.replaceAll("\\s+","");
        int[] creditCardAsNumber = new int[cardNumber.length()];

        for (int i = 0; i < cardNumber.length(); i++) {
            creditCardAsNumber[i] = Integer.parseInt(cardNumber.substring(i, i + 1));
        }
        // Starting from the right, double each other digit, if greater than 9 do 'mod 10 plus 1' to the remainder
        for (int i = creditCardAsNumber.length-2; i >= 0; i = i - 2) {
            int tempValue = creditCardAsNumber[i];
            tempValue = tempValue * 2;
            if (tempValue > 9) {
                tempValue = tempValue % 10 + 1;
            }
            creditCardAsNumber[i] = tempValue;
        }

        //Add up all ten digits
        int sum = 0;
        for (int i = 0; i < creditCardAsNumber.length; i++) {
            sum += creditCardAsNumber[i];
        }
        //If Number is multiple of 10, its valid
        if (creditCardAsNumber.length > 0 && sum % 10 == 0) {
            log.info("Valid Credit Card Number");
            return ValidationResult.valid();
        } else {
            log.error("Invalid CreditCard Number- Not Compatible");
            return ValidationResult.inValid("Invalid CreditCard Number- Not Compatible");
        }
    }
}
