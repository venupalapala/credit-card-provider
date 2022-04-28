package com.publicis.creditcardprovider.repository;

import com.publicis.creditcardprovider.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardProviderRepository extends JpaRepository<CreditCard, Long> {
}
