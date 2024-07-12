package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TaxRecord {
    private Integer id;
    private String code;
    private String type;
    private Double amountPaid;
    private String dateOfPayment;
}