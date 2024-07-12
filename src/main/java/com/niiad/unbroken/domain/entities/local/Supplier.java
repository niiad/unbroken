package com.niiad.unbroken.domain.entities.local;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Supplier {
    private Integer id;
    private String name;
    private String contact;
    private String city;
    private String region;

    public SupplierDisplay toDisplay() {
        return new SupplierDisplay(id, name);
    }
}
