package com.niiad.unbroken.application.inputPorts;

import com.niiad.unbroken.application.outputPorts.CashAccountOutputPort;
import com.niiad.unbroken.application.useCases.CashAccountUseCase;
import com.niiad.unbroken.domain.entities.local.CashAccount;
import org.springframework.stereotype.Component;

@Component
public class CashAccountInputPort implements CashAccountUseCase {
    private final CashAccountOutputPort cashAccountOutputPort;

    public CashAccountInputPort(CashAccountOutputPort cashAccountOutputPort) {
        this.cashAccountOutputPort = cashAccountOutputPort;
    }

    @Override
    public CashAccount createCashAccount(CashAccount cashAccount) {
        if (persistCashAccount(cashAccount)) {
            return cashAccount;
        } else {
            return null;
        }
    }

    private boolean persistCashAccount(CashAccount cashAccount) {
        return cashAccountOutputPort.persistCashAccount(cashAccount);
    }

}
