package com.niiad.unbroken.application.useCases;

import com.niiad.unbroken.domain.entities.local.CashAccount;
import org.springframework.stereotype.Component;

@Component
public interface CashAccountUseCase {
    CashAccount createCashAccount(CashAccount cashAccount);
}
