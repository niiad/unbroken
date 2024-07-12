package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.Transaction;
import org.springframework.stereotype.Component;

@Component
public interface TransactionUseCase {
    Transaction performTransaction(Transaction transaction);
}
