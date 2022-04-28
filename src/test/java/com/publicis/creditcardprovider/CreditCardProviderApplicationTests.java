package com.publicis.creditcardprovider;

import com.publicis.creditcardprovider.controller.CreditCardProviderController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CreditCardProviderApplicationTests {

    @Autowired
    private CreditCardProviderController creditCardProviderController;

    @Test
    void contextLoads() {
        assertThat(creditCardProviderController).isNotNull();
    }

}
