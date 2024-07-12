package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.TransactionOutputPort;
import com.niiad.unbroken.application.useCases.TransactionUseCase;
import com.niiad.unbroken.domain.entities.local.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionInputPort implements TransactionUseCase {
    private final TransactionOutputPort transactionOutputPort;

    public TransactionInputPort(TransactionOutputPort transactionOutputPort) {
        this.transactionOutputPort = transactionOutputPort;
    }

    @Override
    public Transaction performTransaction(Transaction transaction) {
        if (persistTransaction(transaction)) {
            return transaction;
        } else {
            return null;
        }
    }

    private boolean persistTransaction(Transaction transaction) {
        return transactionOutputPort.persistTransaction(transaction);
    }
}