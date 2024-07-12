package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LocalTax {
    private Integer id;
    private String code;
    private String type;  // VAT, corporate, income
    private String imposedOn; // profits, products, employees etc
    private String rate;
    private String dateIssued;
}
