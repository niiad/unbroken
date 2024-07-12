package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Customer {
    private Integer id;
    private String firstName;
    private String lastName;
    private String contact;
    private String city;
    private String region;
}
