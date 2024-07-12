package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Supply {
    private Integer id;
    private Integer supplier;
    private String supplierName;
    private Integer product;
    private String productName;
    private String unitPrice;
    private Integer quantity;
    private String timestamp;
}
