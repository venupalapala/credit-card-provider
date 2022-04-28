package com.publicis.creditcardprovider.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardProviderRequest {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @Pattern(regexp = "^[\\d ]{1,19}$")
    private String cardNumber;

    @NotNull
    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal limit;
}
