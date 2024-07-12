package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Revenue {
    private Long id;
    private String amountReceived;
    private String category;    // from sales, etc
    private String description;
    private String timestamp;
}
