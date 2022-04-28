package com.publicis.creditcardprovider.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class CreditCard {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String cardNumber;
    private BigDecimal balance;
    private BigDecimal creditLimit;

}
