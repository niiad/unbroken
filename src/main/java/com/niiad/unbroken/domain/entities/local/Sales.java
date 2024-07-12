package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Sales {
    private Long id;
    private Integer productId;
    private String productName;
    private String timestamp;
    private String unitPrice;
    private String sellingPrice;
    private Integer quantityPurchased;
    private String customerName;
    private String customerContact;
}

