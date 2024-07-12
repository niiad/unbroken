package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.Transaction;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface TransactionOutputPort {
    boolean persistTransaction(Transaction transaction);

    List<Transaction> fetchAllTransactions();
}
