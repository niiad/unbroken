package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CashAccount {
    private Integer id;
    private String accountName;
    private String accountNumber;
    private String bank;
    private String accountType;
    private Double balance;

    public CashAccountDisplay toDisplay() {
        return new CashAccountDisplay(id, accountName);
    }
}
