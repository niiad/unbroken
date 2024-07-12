package com.niiad.unbroken.application.outputPorts;

import com.niiad.unbroken.domain.entities.local.CashAccount;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CashAccountOutputPort {
    boolean persistCashAccount(CashAccount cashAccount);

    boolean depositIntoCashAccount(CashAccount cashAccount, Double amount);

    boolean withdrawFromCashAccount(CashAccount cashAccount, Double amount);

    List<CashAccount> fetchAllCashAccounts();
}
