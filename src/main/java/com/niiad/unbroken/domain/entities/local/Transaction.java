package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Transaction {
    private Long id;
    private String sender;
    private String receiver;
    private Double value;
    private String type;
    private String timestamp;
    private String description;
}
